# crypto-calculator

Project with API for listing cryptocurrency exchange rates and calculating value of exchanged currency.

Runing the project:

```
COIN_API_KEY=THIS-IS-SAMPLE-KEY ./gradlew -Dorg.gradle.java.home=$JAVA17_HOME clean build bootRun
```

Example requests:

```
curl 'localhost:8080/currencies/BTC?filter[]=USDT&filter[]=ETH'
curl -XPOST 'localhost:8080/currencies/exchange' -H 'Content-Type:application/json' -d '{"from": "BTC", "to": ["USDT", "ETH"], "amount": 121}'
```
