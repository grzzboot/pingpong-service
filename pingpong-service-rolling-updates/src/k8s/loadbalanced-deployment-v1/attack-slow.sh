echo "GET http://35.246.219.31:8080/ping?name=test&sleepSeconds=5" | vegeta attack -keepalive=false -duration=1m -rate=1 | tee results.bin | vegeta report
  vegeta report -type=json results.bin > metrics.json
  cat results.bin | vegeta plot > plot.html
  cat results.bin | vegeta report -type="hist[0,100ms,200ms,300ms]"