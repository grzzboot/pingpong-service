package com.grzzboot.service.pingpong.resource.service.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.grzzboot.service.pingpong.resource.service.repository.config.BucketConfig;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class PingPongRepository {

	private static final String CLOUD_STORAGE_URI = "https://storage.googleapis.com";

	private final ResourceLoader resourceLoader;
	Storage publicStorage;
	Storage privateStorage;
	private final BucketConfig bucketConfig;

	@Autowired
	public PingPongRepository(final ResourceLoader resourceLoader, final BucketConfig bucketConfig) {
		this.resourceLoader = resourceLoader;
		this.bucketConfig = bucketConfig;
	}

	public Optional<String> getPublicDocumentUrl(String documentName) {
		Blob blob = getBlob(documentName, true);
		return blob == null ? Optional.empty()
				: Optional.of(CLOUD_STORAGE_URI + "/" + bucketConfig.getPublicStorage().getName() + "/" + documentName);
	}

	public void createPublicDocument(MultipartFile file) throws IOException {
		createDocument(file, true);
	}

	public Optional<String> getPrivateDocumentUrl(String documentName) {
		Blob blob = getBlob(documentName, false);
		return blob == null ? Optional.empty()
				: Optional.of(getSignedUrl(blob, bucketConfig.getPrivateStorage().getDefaultDuration()));
	}

	public void createPrivateDocument(MultipartFile file) throws IOException {
		createDocument(file, false);
	}

	private void createDocument(MultipartFile file, boolean publicDocument) throws IOException {
		String filename = file.getOriginalFilename();
		BlobInfo blobInfo = getBlobInfo(filename, publicDocument);
		privateStorage.create(blobInfo, file.getBytes());
		log.info("Wrote {}", filename);
	}

	private BlobInfo getBlobInfo(String filename, boolean publicDocument) {
		String bucketName = publicDocument ? bucketConfig.getPublicStorage().getName()
				: bucketConfig.getPrivateStorage().getName();
		return BlobInfo.newBuilder(bucketName, filename).setContentType(MediaType.APPLICATION_PDF_VALUE).build();
	}

	private Blob getBlob(String documentName, boolean publicDocument) {
		String bucketName = publicDocument ? bucketConfig.getPublicStorage().getName()
				: bucketConfig.getPrivateStorage().getName();
		Bucket bucket = publicDocument ? publicStorage.get(bucketName) : privateStorage.get(bucketName);
		String blobName = BlobId.of(bucketName, documentName).getName();
		return bucket.get(blobName, Storage.BlobGetOption.fields(Storage.BlobField.CONTENT_TYPE));
	}

	private String getSignedUrl(Blob blob, long duration) {
		return blob.signUrl(duration, TimeUnit.SECONDS).toExternalForm();
	}

	@PostConstruct
	private void initPublicStorage() throws IOException {
		String serviceAccountFile = bucketConfig.getServiceAccountFile();
		try (InputStream serviceAccountStream = resourceLoader.getResource(serviceAccountFile).getInputStream()) {
			String bucketName = bucketConfig.getPublicStorage().getName();
			GoogleCredentials googleCredentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
			publicStorage = StorageOptions.newBuilder().setCredentials(googleCredentials).build().getService();
			log.info("Initialized publicStorage using credentials from {}. Target bucket is {}", serviceAccountFile,
					bucketName);
		}
	}

	@PostConstruct
	private void initPrivateStorage() throws IOException {
		String serviceAccountFile = bucketConfig.getServiceAccountFile();
		try (InputStream serviceAccountStream = resourceLoader.getResource(serviceAccountFile).getInputStream()) {
			String bucketName = bucketConfig.getPrivateStorage().getName();
			GoogleCredentials googleCredentials = ServiceAccountCredentials.fromStream(serviceAccountStream);
			privateStorage = StorageOptions.newBuilder().setCredentials(googleCredentials).build().getService();
			log.info("Initialized privateStorage using credentials from {}. Target bucket is {}", serviceAccountFile,
					bucketName);
		}
	}

}
