echo "GET http://<YOUR-IP>:8080/ping?expensive=true" | vegeta attack -duration=10m -rate=4 | tee results.bin | vegeta report
  vegeta report -type=json results.bin > metrics.json
  cat results.bin | vegeta plot > plot.html
  cat results.bin | vegeta report -type="hist[0,100ms,200ms,300ms]"