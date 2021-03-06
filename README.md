# ReadMe progetto concessioni fluviali
Progetto **Fluvial-deals**, sviluppato da Alessio Saccuti e Lorenzo Del Rossi. Lo scopo di questo lavoro è quello di offrire un servizio web con cui interagire per ottenere informazioni riguardanti le concessione fluviali del Tevere. I dati utilizzati sono stati ottenuti da [dati.gov](https://www.dati.gov.it/api/3/action/package_show?id=a1dee418-ddd7-40c6-ad6c-7b35aa31f61a), scaricati e adattati per poi manipolarli secondo i principi dell'**OOP**. Le operazioni eseguibili sono quelle di visualizzazione dei dati, filtraggio e calcolo di statistiche. 


I dati vengono convertiti da un file CSV in una collezione di dati. Essendo il file non corretto in tutte le sue righe si è optato per le seguenti regole di accettazione, con relativo messaggio di eliminazione in base al motivo:

1) Caso in cui la riga, suddivisa per marcatori, non abbia il numero di segmenti aspettato. In console viene mostrato: `La riga N del file CSV per lunghezza diversa da quella aspettata`. 
2) Caso in cui manchino degli elementi necessari:
>L'attributo durata non presenta un dato in formato numerico (ad esempio la stringa "6 anni" è accettabile mentre la stringa "sei anni" no).

>L'attributo ID comune non è alfanumerico.

>L'attributo ID comune non è presente oppure ha una lunghezza errata.

>La ragione sociale o nome e cognome sono assenti. In caso di presenza di solo nome e cognome si ricostruisce la ragione   sociale come somma dei due.

>Mancano tutti e tre gli elementi: superficie, specchio d'acqua e durata.

   In questo caso viene mostrato in console: `La riga N del file CSV per elemento mancante`. 

La durata della concessione è espressa in giorni invece che anni per una maggior precisione nei conti statistici.

---
# Files
Sono presenti in repository le seguenti cartelle:

 1. **DemanioFluviale**: contiene un progetto nel quale sono presenti le classi per il download e lo stoccaggio dei dati.
 2. **DemanioSpring**: prendendo i dati dal progetto precedenti li usa per costruire un progetto di tipo web.
 3. **External Files**: contiene i Jars delle librerie utilizzate.
 4. **JPGs**: contiene delle immagini utili per questo markdown.
 5. **SVGs**: svg dei diagrammi uml.
 6. **docs**: contiene tutta la documentazione javadoc.
---

## Jars utilizzati

Sono stati usati i seguenti Jars:

- [Jsoup](https://jsoup.org/).
- [Apache common I/O](https://commons.apache.org/proper/commons-io/). 
- [Apache common lang](https://commons.apache.org/proper/commons-lang/).
- [Json](https://mvnrepository.com/artifact/org.json/json).

Nella cartella descritta precedentemente troverete i file già scaricati, nei progetti sono usate le dipendenze Maven.

---

## Come eseguire il Run
Come ambiente di sviluppo è stato utilizzato [Eclipse](https://www.eclipse.org/downloads/packages/release/mars/r/eclipse-ide-java-developers). Per il corretto lancio dell'applicazione:

> Eseguire il download della repository

> In Eclipse andare in **File -> Open Projects from File System -> Directory** oppure in **File -> Import -> General -> Existing project into workspace** per caricare i due progetti. Ad operazione completata eclipse richiederà del tempo per scaricare tutte le dipendenze Maven. 

> Nel caso in cui si avesse una versione JRE differente da quella del progetto scaricato (verranno mostrati dei warning a riguardo) bisogna cliccare con il tasto destro su un progetto e: 
> 1) andare in **Build Path ->  Configure Build Path**
> 2) eliminare la versione di JRE presente
> 3) cliccare su **Add Library** per poi cliccare su **JRE System Library**, potrete scegliere la versione più aggiornata o quella del vostro sistema.
---

## JavaDoc
I file javadoc generati mediante Eclipse sono tutti contenuti nella cartella **docs**, l'api è visualizzabile in [Api Real Estate](https://superdiodo.github.io/Real-Estate/).

---

## Funzionamento
![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/UseCase.JPG)

![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/Class.JPG)

Per diagrammi UML più dettagliati si può fare riferimento ai file SVG seguenti:
- [Diagramma delle classi con Setters e Getters](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagramSG.svg).
- [Diagramma delle classi senza Setters e Getters](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagramNOSG.svg).
- [Diagramma delle classi semplificato](https://github.com/SuperDiodo/Real-Estate/blob/master/SVGs/ClassDiagram.svg).

---
## Filtri

|          Filtro          | Descrizione | Attributi applicabili |                  JSON del filtro (esempio)                  |
| :----------------------: | :---------: | :-------------------: | :---------------------------------------------------------: |
|    Greater than (gt)     |  Cerca i valori del campo scelto (**field**) maggiori della soglia **lower**.           |       Numerici        |         {"type":"gt","field":"durata","lower":5000}         |
| Greater than equal (gte) |     Cerca i valori del campo scelto (**field**) maggiori o uguali della soglia **lower**.     |       Numerici        |        {"type":"gte","field":"durata","lower":5000}         |
|     Lower than (lt)      |   Cerca i valori del campo scelto inferiori della soglia **upper** nel campo **field**.        |       Numerici        |         {"type":"lt","field":"durata","upper":1000}         |
|  Lower than equal (lte)   |    Cerca i valori del campo scelto (**field**) inferiori o uguali della soglia **upper**.         |       Numerici        |        {"type":"lte","field":"durata","upper":1000}         |
|       Between (btne)      |    Cerca i valori del campo scelto (**fields**)nei valori compresi tra la soglia **lower** e **upper**.        |       Numerici        |  {"type":"btne","fields":"supwater","upper":300,"lower":100}  |
|  Between and equal (bt)  |     Cerca i valori del campo scelto (**fields**) nei valori compresi o uguali tra la soglia **lower** e **upper**.        |       Numerici        | {"type":"bt","fields":"supwater","upper":300,"lower":100}  |
|        Not (not)         |       Cerca i valori del campo scelto (**field**) nei valori diversi dal valore **value**.      |       Numerici        |         {"type":"not", "fields":"supwater","value": 0}         |
|       Only (only)        |      Cerca i valori del campo scelto (**field**) uguali al valore **value**.       |       Numerici        |         {"type":"only","field":"durata","value":5000}         |
|         In (in)          |       Cerca i valori del campo scelto (**field**) nei valori uguali ad almeno un valore nell'array  **value**.      |       Numerici        |        {"type":"in", "fields": "supwater","value":%5B 0,180 %5D}  |
|      Not in  (nin)       |        Cerca i valori del campo scelto (**field**) diversi dai valori contenuti nell'array  **value**.     |       Numerici        |        {"type":"nin", "fields": "supwater","value":%5B 0,180 %5D}       |
|         Or (or)          |   Cerca le stringhe del campo scelto (**fields**) uguali ad almeno un valore nell'array **cities**.          |       Stringhe        |        {"type":"or", "fields": "comune","cities":%5B "roma","fiumicino" %5D}       |
|        And (and)         |    Cerca le stringhe del campo scelto (**fields**) uguali a tutti gli elementi dell'array **cities**. Per rendere più completo il filtro è stato inserito il field **search** tramite il quale, dato come vettore **cities** del tipo [città, numero]  il filtro cercherà le concessioni nella città richiesta e della durata richiesta (quindi una ricerca della concessione per città e durata).         |       Stringhe (nel field search Stringa + numero)       | {"type":"and","fields":"search","cities":%5B"roma",6935%5D} |

---
## Statistiche
| Statistiche numeriche | Risultato |
| :-------------------: | :-------: |
|      Sommatoria       |   `int`   |
|        Mimimo         |   `int`   |
|        Massimo        |   `int`   |
|         Media         | `double`  |
|  Deviazione standard  | `double`  |
|  Count                | `int`     |
```json
Applicabile a sup, supwater, durata. Esempio chiamata: /stats?field=durata
```



| Statistiche stringhe |      Risultato       |
| :------------------: | :------------------: |
|      Occorrenze      | `Map<String,Object>` |

```json
Applicabile a nome, cognome, comune, RagSoc, IDCom, denominazione. Esempio chiamata: /stats?field=IDCom
```

---

## HTTP responses
|   Codice Riposta    |        Chiamate possibili        |                         Significato                          |
| :-----------------: | :--------------------------: | :----------------------------------------------------------: |
|     `200 - OK`      | `meta, data,  filtering, stats`  | L'operazione è andata a buon fine, il risultato prodotto è quello aspettato. |
| `400 - BAD REQUEST` | `filtering, stats` | Il filtro o il field immesso non è stato implementato oppure è incorretto. |
| `501 - NOT IMPLEMENTED`  | `filtering, stats`  |      Field o filtro non incluso nei casi implementati.       |

---

## Tests
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
    "Field": "durata",
    "Deviazione STD": 2856.0277852687764,
    "Massimo": 6935,
    "Media": 4727,
    "Minimo": 0,
    "Sommatoria": 831959,
    "Count": 176
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

I filtri implementati sono 12 ed a titolo di esempio ne mostriamo qualcuno:
*  Con la query string **http://localhost:8080/filtering?filter={"type":"and","fields":"search","cities":%5B"roma",6935%5D}** cerchiamo in particolare i records che hanno come attributo **comune** la città di Roma ed una durata di concessione di 6935 giorni (l'esempio ne mostra una parte).
```json
    [
    {
        "nome": "MAREVIVO SERVIZI",
        "cognome": "S.R.L.",
        "comune": "ROMA",
        "den": "LUNGOTEVERE ARNALDO DA BRESCIA - SCALO DE PINEDO",
        "superficie": 0,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "MAREVIVO SERVIZI S.R.L.",
        "idcom": "H501"
    },
    {
        "nome": "ANGELO",
        "cognome": "PREZIOSI AMM. UNICO",
        "comune": "ROMA",
        "den": "RIVA DESTRA FIUME TEVERE-A MONTE DI PONTE RISORGIMENTO LGTV OBERDAN N\ufffd2",
        "superficie": 284,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "GILDA S.R.L.",
        "idcom": "H501"
    },
    {
        "nome": "Giuseppina (rappresentante legale)",
        "cognome": "Sansovini",
        "comune": "Roma",
        "den": "GOLENA SINISTRA FIUME TEVERE - LOC. LUNGOTEVERE DANTE, 277. ZONA VALCO S. PAOLO (FG. 839 P.LLA 5, 6, 7)",
        "superficie": 0,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "ASSOCIAZIONE SPORTIVA SPORT LIBERO",
        "idcom": "H501"
    },
    {
        "nome": "SERGIO",
        "cognome": "SCHISANI",
        "comune": "ROMA",
        "den": "FIUME TEVERE SPONDA DESTRA, NEL TRATTO COMPRESO TRA PONTE RISORGIMENTO E PONTE MATTEOTTI -L.TEVERE DELLE ARMI, 44",
        "superficie": 0,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "TiberINA srl unipersonale",
        "idcom": "H501"
    },
    {
        "nome": "FABIO E STEFANO",
        "cognome": "MANFREDI",
        "comune": "ROMA",
        "den": "SPONDA DESTRA FIUME TEVERE COMUNE DI ROMA - A VALLE DI PONTE DELL'INDUSTRIA",
        "superficie": 211,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "CARROZERIA F.LLI MANFREDI SNC",
        "idcom": "H501"
    }
   ]
```
* Con la query string **http://localhost:8080/filtering?filter={"type":"bt","fields":"supwater","upper":300,"lower":100}** cerchiamo in particolare i records che hanno come attributo **supwater** un valore compreso tra 100 e 300 (l'esempio ne mostra una parte).
```json
 [
 {
        "nome": "CRISTINA",
        "cognome": "DI MEO",
        "comune": "TERRACINA",
        "den": "FIUME SISTO IN LOCALITA' FOCE SISTO, LATO SX E DX A VALLE DEL PONTE DELLA STRADA PROVINCIALE PER BADINO FOGLIO 131 PARTICELLA 57/P 67/P",
        "superficie": 103,
        "supwater": 258,
        "durata": 2190,
        "ragSoc": "DITTA INDIVIDUALE",
        "idcom": "L120"
    },
    {
        "nome": "SERGIO",
        "cognome": "CESTRA",
        "comune": "TERRACINA",
        "den": "SPONDA GOLENALE DESTRA FIUME SISTO LOC. FOCE SISTO",
        "superficie": 40,
        "supwater": 167,
        "durata": 6935,
        "ragSoc": "DITTA INDIVIDUALE",
        "idcom": "L120"
    },
    {
        "nome": "MAURIZIO",
        "cognome": "PALAZZI",
        "comune": "ROMA",
        "den": "SPONDA DESTRA FIUME TEVERE - A VALLE DI PONTE DUCA D'AOSTA",
        "superficie": 0,
        "supwater": 190,
        "durata": 6935,
        "ragSoc": "ASSOCIAZIONE SPORTIVA CANOA PALAZZI SRL",
        "idcom": "H501"
    },
    {
        "nome": "CLUB NAUTICO GARIGLIANO",
        "cognome": "ASSOCIAZIONE",
        "comune": "minturno",
        "den": "SPONDA DESTRA FIUME GARIGLIANO A RIDOSSO DELLA S.S. PECENNONE NEL TRATTO COMPRESO TRA IL Km 1,100 ED IL Km 1,250",
        "superficie": 92,
        "supwater": 300,
        "durata": 6935,
        "ragSoc": "CLUB NAUTICO GARIGLIANO ASSOCIAZIONE",
        "idcom": "F224"
    },
    {
        "nome": "Pietro",
        "cognome": "Rosati",
        "comune": "Trevignano Romano",
        "den": "I Renai",
        "superficie": 262,
        "supwater": 300,
        "durata": 6935,
        "ragSoc": "IL SOTTOVENTO S.A.S.",
        "idcom": "L401"
    }
   ]
```


*   Con la query string **http://localhost:8080/filtering?filter={"type":"or","fields":"comune","cities":%5B"roma","fiumicino"%5D}** cerchiamo in particolare i records che hanno come attributo **comune** la città di Roma o la città di Fiumicino (l'esempio ne mostra una parte).
``` json
 [
 {
        "nome": "",
        "cognome": "",
        "comune": "FIUMICINO",
        "den": "SPECCHIO ACQUEO SX FIUME TEVERE - VIA DELLA SCAFA 119/A",
        "superficie": 520,
        "supwater": 520,
        "durata": 2190,
        "ragSoc": "EL GALEON S.R.L.",
        "idcom": "M297"
    },
    {
        "nome": "CIRCOLO CANOTTIERI ROMA",
        "cognome": "CIRCOLO",
        "comune": "Roma",
        "den": "SPONDA SX FIUME TEVERE TRATTO COMPRESO TRA PONTE DUCA D'AOSTA E PONTE RISORGIMENTO",
        "superficie": 0,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "CIRCOLO CANOTTIERI ROMA CIRCOLO",
        "idcom": "H501"
    },
    {
        "nome": "Renzo",
        "cognome": "Carlucci (rapp. legale)",
        "comune": "FIUMICINO",
        "den": "FIUME TEVERE - CANALE NAVIGABILE - A NORD DI PONTE DUE GIUGNO",
        "superficie": 0,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "CONSTELLATION NAUTICA S.R.L.",
        "idcom": "M297"
    },
    {
        "nome": "MAURIZIO",
        "cognome": "PALAZZI",
        "comune": "ROMA",
        "den": "SPONDA DESTRA FIUME TEVERE - A VALLE DI PONTE DUCA D'AOSTA",
        "superficie": 0,
        "supwater": 190,
        "durata": 6935,
        "ragSoc": "ASSOCIAZIONE SPORTIVA CANOA PALAZZI SRL",
        "idcom": "H501"
    },
    {
        "nome": "CANTIERE NAUTICO ALBULA",
        "cognome": "SRL",
        "comune": "Fiumicino",
        "den": "DX CANALE NAVIGABILE A MONTE PONTE 2 GIUGNO VIA UGO BAISTROCCHI",
        "superficie": 3190,
        "supwater": 1550,
        "durata": 2190,
        "ragSoc": "CANTIERE NAUTICO ALBULA SRL",
        "idcom": "M297"
    },
    {
        "nome": "MA.GI.",
        "cognome": "SRL",
        "comune": "ROMA",
        "den": "SPONDA SINISTRA F. TEVERE - LUNG.TEVERE S.PAOLO A MONTE DI PONTE MARCONI",
        "superficie": 20350,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "MA.GI. SRL",
        "idcom": "H501"
    },
    {
        "nome": "MASSIMO",
        "cognome": "DE BENEDICTIS",
        "comune": "Roma",
        "den": "SPONDA SISNISTRA FIUME TEVERE - LUNGOTEVERE THAON DI REVEL - BANCHINA TRA PONTE MILVIO E PONTE DUCA D'AOSTA",
        "superficie": 4910,
        "supwater": 0,
        "durata": 0,
        "ragSoc": "L'Antico Ponte S.r.l.",
        "idcom": "H501"
    }
   ] 
```

Altre chiamate possibili sono quelle per mostrare i dati, in diverse forme: 

* Metadati: **http://localhost:8080/metadata**.
```json
[
    {
        "alias": "nome",
        "sourceField": "nome",
        "type": "String"
    },
    {
        "alias": "cognome",
        "sourceField": "cognome",
        "type": "String"
    },
    {
        "alias": "RagSoc",
        "sourceField": "Ragione Sociale",
        "type": "String"
    },
    {
        "alias": "IDCom",
        "sourceField": "ID_Comune",
        "type": "String"
    },
    {
        "alias": "comune",
        "sourceField": "Comune del bene oggetto di Concessione",
        "type": "String"
    },
    {
        "alias": "den",
        "sourceField": "Denominazione_Luogo",
        "type": "String"
    },
    {
        "alias": "superficie",
        "sourceField": "superficie",
        "type": "Integer"
    },
    {
        "alias": "supwater",
        "sourceField": "superficie specchio acqua",
        "type": "Integer"
    },
    {
        "alias": "durata",
        "sourceField": "Durata concessione",
        "type": "Integer"
    }
]
```

* Dati in formato JSON (l'esempio ne mostra una parte): **http://localhost:8080/data**.
```json
    [
    {
        "nome": "MARCO",
        "cognome": "CIANCHETTA",
        "comune": "Roma",
        "den": "DX TEVERE LOC. L.TEVERE MARESCIALLO DIAZ",
        "superficie": 0,
        "supwater": 0,
        "durata": 2190,
        "ragSoc": "MARCO CIANCHETTA",
        "idcom": "H501"
    },
    {
        "nome": "SALVATORE",
        "cognome": "CHIODO",
        "comune": "ROMA",
        "den": "SPONDA DX FIUME TEVERE LOC. TOR DI QUINTO N. 64",
        "superficie": 1025,
        "supwater": 0,
        "durata": 2190,
        "ragSoc": "SALVATORE CHIODO",
        "idcom": "H501"
    },
    {
        "nome": "LAMPEDUSA",
        "cognome": "S.R.L.",
        "comune": "FIUMICINO (RM)",
        "den": "ISOLA SACRA - VIA MONTE CENGIO - FIUMICINO (RM)",
        "superficie": 0,
        "supwater": 0,
        "durata": 6935,
        "ragSoc": "LAMPEDUSA S.R.L.",
        "idcom": "M297"
    }
    ]
```

* Dati in formato tabella HTML: **http://localhost:8080/show.html**. Mostra l'attuale selezione di dati che si sta utilizzando, dopo l'uso di *filtering* vengono mostrati solo i risultati ottenuti. Per il ripristino della collezione basta chiamare *data*. ![](https://github.com/SuperDiodo/Real-Estate/blob/master/JPGs/table.JPG)
