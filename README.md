# Kort om programmet
Backend med liten frontend som løser kodeoppgave med Oslo bysykkel.

## Løsning
- Skrevet i kotlin
- Spring boot applikasjonsrammeverk
- Engelsk kodestandard. 
- Dokumentasjon på norsk
- Visuell løsning for sluttbruker er norsk

## Google maps
Løsning tas opp i google maps. 

Api nøkkel som er brukt tilhører Henrik Solberg og er gyldig i 1 måned

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

