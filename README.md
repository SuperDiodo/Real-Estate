# ReadMe progetto concessioni fluviali
Il progetto è stato soprannominato "Fluvial-deals", sviluppato da Alessio Saccuti e Lorenzo Del Rossi.


# Files
Sono presenti in repository tre cartelle:

 1. **DemanioFluviale**: contiene un progetto nel quale sono presenti le classi per il download e lo stoccaggio dei dati.
 2. **DemanioSpring**: prendendo i dati dal progetto precedenti li usa per costruire un progetto di tipo web.
 3. **External Files**: contiene i Jars delle librerie utilizzate.

## Jars utilizzati
- [Jsoup](https://jsoup.org/)
- [Apache common I/O](https://commons.apache.org/proper/commons-io/) 
- [Apache common lang](https://commons.apache.org/proper/commons-lang/)
- [Json](https://mvnrepository.com/artifact/org.json/json)

## Come eseguire il Run
Passando come String (URL) un data-set è possibile scaricarlo e salvarlo in un CSV, grazie a chiamate di tipo REST è possibile interagire con la collezione come illustrato nella pagina di benvenuto dell'applicazione: https://"host"/. Per il corretto lancio dell'applicazione:

	 - Import di DemanioFluviale in DemanioSpring
	 - Import dei Jars nei progetti
	 - L'uso di connessione internet è consigliabile

## JavaDoc
I file javadoc generati mediante Eclipse sono tutti contenuti nella cartella **docs**, l'api è visualizzabile in [Api Real Estate](https://superdiodo.github.io/Real-Estate/)

## Funzionamento
![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/UseCase.JPG)

![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/Class.JPG)

Per diagrammi UML più dettagliati si può fare riferimento ai file SVG seguenti:
- [Diagramma delle classi con Setters e Getters](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagramSG.svg)
- [Diagramma delle classi senza Setters e Getters](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagramNOSG.svg)
- [Diagramma delle classi semplificato](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagram.svg)

## Tests
Possono essere eseguiti vari test dell'applicazione, i più interessanti e particolari sono quelli relativi all'uso di filtri e calcolo di statistiche. In genere le chiamate sono composte così:

	- Filtraggio: http://localhost:8080/filtering?"JSON FILTER".
	- Statistiche: http://localhost:8080/filtering?"ATTRIBUTO SU CUI CALCOLARE LE STATS".
	- Statistiche su selezione: http://localhost:8080/stats?"ATTRIBUTO SU CUI CALCOLARE LE STATS" & "JSON FILTER".

Di seguito sono riportate delle chiamate specifiche con relativi risultati.

* Calcolo statistiche di un attributo (stringa): **http://localhost:8080/stats?field=IDCom**.

```json
{
    "M297": 31,
    "L120": 19,
    "H282": 2,
    "H076": 1,
    "F272": 1,
    "F880": 1,
    "I892": 3,
    "L401": 5,
    "I992": 1,
    "C116": 11,
    "B114": 2,
    "H444": 1,
    "F224": 4,
    "H501": 81,
    "H534": 1,
    "B663": 1,
    "A297": 5,
    "D561": 1,
    "F419": 2,
    "I838": 1,
    "L182": 1,
    "h501": 1
}
```
* Calcolo statistiche di un attributo (numerico): **http://localhost:8080/stats?field=durata**.

```json
{
    "Deviazione STD": 2856.0277852687764,
    "Massimo": 6935,
    "Media": 4727,
    "Minimo": 0,
    "Sommatoria": 831959
}
```

* Calcolo statistiche di un attributo su una selezione: **http://localhost:8080/stats?field=IDCom&filter={"type":"gt","field":"durata","lower":5000}**.

```json
{
    "M297": 24,
    "L120": 11,
    "F272": 1,
    "I892": 2,
    "L401": 3,
    "I992": 1,
    "C116": 2,
    "B114": 1,
    "F224": 4,
    "H501": 53,
    "D561": 1,
    "F419": 2,
    "I838": 1,
    "L182": 1,
    "h501": 1
}
```
