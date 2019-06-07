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

Per diagrammi UML più dettagliati si può fare riferimenti ai file SVG seguenti:

-[Diagramma delle classi]
<!--stackedit_data:
eyJoaXN0b3J5IjpbLTkzODE2ODE4MCwxMDI1MzU3NzM0LDgzMD
Q2NTY5NywzMTM5NTIxNDUsLTE4MTc2NTU1ODksLTE2MDIzNzcx
ODcsOTM5MzczMjQyLDE5MjU3MTMzODJdfQ==
-->