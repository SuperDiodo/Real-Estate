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

## Funzionamento

![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/UseCase.JPG)

![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/Class.JPG)

Per diagrammi UML più dettagliati si può fare riferimento ai file SVG seguenti:
- [Diagramma delle classi con Setters e Getters](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagramSG.svg)
- [Diagramma delle classi senza Setters e Getters](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagramNOSG.svg)
- [Diagramma delle classi semplificato](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagram.svg)

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTIwNDEzNzUwMiwyNjE3MTY1MzksMTYxMz
kwOTQwMSwxMDI1MzU3NzM0LDgzMDQ2NTY5NywzMTM5NTIxNDUs
LTE4MTc2NTU1ODksLTE2MDIzNzcxODcsOTM5MzczMjQyLDE5Mj
U3MTMzODJdfQ==
-->


## JavaDoc

I file javadoc generati mediante Eclipse sono tutti contenuti nella cartella **docs**, l'api è visualizzabile in [Api Real Estate](https://superdiodo.github.io/Real-Estate/)

## Tests

Chiamata per il calcolo delle occorrenze di ogni comune: **http://localhost:8080/stats?field=comune**.

```json
{
   "DI COLA": 1,
   "DI SOZIO": 1,
   "PALAZZI": 2,
   "LIBERATORE": 1,
   "LUCIDI": 1,
   "FRANCESE": 1,
   "CORSETTI": 1,
   "MANFREDI": 1,
   "PREZIOSI AMM. UNICO": 1,
   "SCHISANI": 1,
   "LENZINI": 1,
   "RICCIARDI": 1,
   "PAGLIA": 1,
   "ANDRIZZI": 1,
   "Rosati": 1,
   "PETRICONE": 1,
   "DESIDERI": 1,
   "CARLUCCI": 1,
   "AREA LAVORI PUBBLICI E MANUTENZIONE URBANA": 1,
   "DI STEFANO": 1,
   "Piero": 1,
   "ASSOCIAZIONE SPORTIVA DILETTANTISTICA": 3,
   "BELARDI": 1,
   "GIUSEPPE": 1,
   "BRESSAN": 1,
   "ROMANELLI": 1,
   "Del Pio": 1,
   "Calvigioni": 1,
   "ULISSI": 1,
   "Moretti": 1,
   "ADORNI": 1,
   "LUCIDI - GASPERINI": 1,
   "SPANAKIS": 1,
   "ROBERTI": 1,
   "MAIOLATI": 1,
   "CAROLINI": 1,
   "Circolo Magistrati della Corte dei Conti": 1,
   "S.R.L. ex S.A.S. di Lorenzo Agostinelli & C.": 1,
   "ATTANASIO": 1,
   "O.N.L.U.S.": 1,
   "HOWLAND": 1,
   "DE BENEDICTIS": 1,
   "S.R.L.": 6,
   "CIANCHETTA": 1,
   "BUDAI (PRESIDENTE)": 1,
   "BULDINI": 1,
   "ENTE RELIGIOSO": 1,
   "ALBATROS  SRL": 1,
   "BETERA - RIGHI": 1,
   "DI PIETRO": 1,
   "RICCI (RAPPRESENTANTE LEGALE)": 1,
   "DITTA INDIVIDUALE": 1,
   "CANNIZZARO": 1,
   "KITMACHER": 1,
   "CHIODO": 1,
   "SATTA - RAPPRESENTANTE LEGALE": 1,
   "STILLITANO": 2,
   "DI MEO": 1,
   "CONTE": 1,
   "Formaggi Andrea": 1,
   "S.A.S.": 2,
   "CAPPABIANCA": 1,
   "COLICCHI -  LEGALE RAPPRESENTANTE": 1,
   "SERGI": 1,
   "PALOMBI": 1,
   "Petricone": 1,
   "Presciutti": 1,
   "STELLA MARINA": 1,
   "SIGNORE": 1,
   "Carlucci (rapp. legale)": 1,
   "FEDELE": 1,
   "Porcelli": 1,
   "PALMIGIANI": 1,
   "RICCIARDI (RAP. LEGALE)": 1,
   "SABATINI": 1,
   "CACCIOTTI": 1,
   "ALBUCCI (LEGALE RAPPRESENTANTE)": 1,
   "SOC. COOP. A R.L.": 1,
   "ANDREUZZI": 1,
   "Costagliola (R.L.)": 1,
   "MORONI": 1,
   "D'AMMIZIO": 1,
   "FRANCESCA": 1,
   "BISTONI": 1,
   "Buono": 1,
   "DELTA ITALIA s.r.l.": 1,
   "VENDITELLI": 1,
   "Giuliani": 1,
   "CONTICCHIO": 1,
   "Sansovini": 1,
   "SRL": 3,
   "ASSOCIAZIONE": 3,
   "SFORZINI": 1,
   "GUADAGNO": 1,
   "ONLUS": 1,
   "S & M  sas": 1,
   "POCHINI  RAP. LEGALE": 1,
   "ASSOCIAZIONE CULTURALE": 1,
   "FOTI": 1,
   "FIA": 1,
   "Bovo amm.re unico": 1,
   "Verticchio": 1,
   "S.N.C.": 1,
   "CIRILLO": 1,
   "Capparella": 1,
   "Buonfiglio": 1,
   "CIRCOLO": 2,
   "Marcucci": 1,
   "BARONE (LEGALE RAPPRESENTANTE)": 1,
   "SAVIANI": 1,
   "Baiocco": 1,
   "CESTRA": 2,
   "BALDORI": 1,
   "VERTICCHIO": 1,
   "SABADYSH": 1
}
```
