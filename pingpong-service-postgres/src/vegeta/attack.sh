echo "GET http://localhost:8080/ping?name=Andreas&expensive=true" | vegeta attack -duration=10m -rate=1 | tee results.bin | vegeta report
  vegeta report -type=json results.bin > metrics.json
  cat results.bin | vegeta plot > plot.html
  cat results.bin | vegeta report -type="hist[0,100ms,200ms,300ms]"