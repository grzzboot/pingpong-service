#!/bin/bash

static_ip=$(gcloud compute addresses list | grep pingpong-static-ip | grep -oE "\b([0-9]{1,3}\.){3}[0-9]{1,3}\b")
host="dev.pingpong.com"

if [ -z "${static_ip}" ]; then
    echo "Could not determine static IP. Please add the IP manually to your /etc/hosts file."
    exit 1
fi

echo -e "Static IP is ${static_ip}"
echo -e "Updating /etc/hosts - you may need to provide your password..."
if grep -q $host /etc/hosts; then
    sudo sed -i '' "/$host/ s/.*/$static_ip $host/g" /etc/hosts
else
    sudo -- sh -c "echo '\n\n${static_ip} ${host}' >> /etc/hosts"
fi
echo -e "Done! You can now access your ingress using http://${host}"
