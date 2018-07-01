# Ploniex Api

## Table of contents
* [Requirements](#requirements)
* [Instuctions](#instructions)
* [Examples](#examples)
    * [Public Api Example](#public-api-example)
    * [Trading Api Example](#trading-api-example)


## Requirements
1. Scala >= 2.12
2. Akka
3. Akka Http
4. Kebs --> https://github.com/theiterators/kebs

## build.sbt

```scala
libraryDependencies += "com.github.pavlomi" %% "poloniex" % "v.0.0.1"
```

## Examples

### Public Api Example

```scala
val config: Config                      = ConfigFactory.load()
implicit val actorSystem: ActorSystem        = ActorSystem("partner", config)
implicit val materializer: ActorMaterializer = ActorMaterializer()
implicit val executor: ExecutionContext      = actorSystem.dispatcher
val publicApi: PoloniexPublicApi  = new PoloniexPublicApi()
  
val res: Future[Either[PoloniexErrorResponse, ReturnTicketResponse]] = publicApi.returnTicket()
```

### Trading Api Example

```scala
val apiKey = PoloniexAPIKey("some api key")
val secret = PoloniexSecret("some secret")
val config: Config                      = ConfigFactory.load()
implicit val actorSystem: ActorSystem        = ActorSystem("partner", config)
implicit val materializer: ActorMaterializer = ActorMaterializer()
implicit val executor: ExecutionContext      = actorSystem.dispatcher
val tradingApi: PoloniexPublicApi  = new PoloniexTradingApi(apiKey, secret)
  
val res: Future[Either[PoloniexErrorResponse, ReturnBalanceResponse]] = tradingApi.returnBalances()
```
