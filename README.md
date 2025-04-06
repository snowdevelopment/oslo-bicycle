# Kort om programmet
Backend med liten frontend som løser kodeoppgave med Oslo bysykkel.

## Løsning
- Skrevet i kotlin
- Spring boot applikasjonsrammeverk
- Engelsk kodestandard. 
- Dokumentasjon på norsk
- Visuell løsning for sluttbruker er norsk

## Google maps
Løsning bruker google maps. 

Lag/hent api nøkkel beskrevet her: https://developers.google.com/maps/documentation/javascript/get-api-key

Åpne fila MapHtmlGenerator.kt og søk og erstatt API_KEY med din egen nøkkel.

## Kjøring av programmet
Klone repository fra https://github.com/snowdevelopment/oslo-bicycle

### Fra kommandolinje
Kjør lokalt med: `mvn spring-boot:run`

### Fra IntelliJ
Run BicycleApplication.kt

### Docker
```
mvn clean install
docker build -t bicycle .
docker run -p 8080:8080 bicycle
```
### Åpne nettleser og gå til
```
http://localhost:8080/oslo-bysykkel
```

