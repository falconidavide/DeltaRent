# **Project Plan**
In questo documento viene descritto in modo dettagliato il modello di sviluppo del progetto **DeltaRent**; prenderne visione prima di analizzare il progetto è necessario.

# Indice
- [**Project Plan**](#project-plan)
  - [Indice](#indice)
  - [1. Introduzione](#1-introduzione)
  - [2. Modello di Processo](#2-modello-di-processo)
  - [3. Organizzazione del progetto](#3-organizzazione-del-progetto)
  - [4. Standard, linee guida, procedure](#4-standard-linee-guida-procedure)
  - [5. Attività di gestione](#5-attività-di-gestione)
  - [6. Rischi](#6-rischi)
  - [7. Personale](#7-personale)
  - [8. Metodi e Tecniche](#8-metodi-e-tecniche)
  - [9. Garanzia di Qualità](#9-garanzia-di-qualità)
  - [10. Pacchetti di Lavoro](#10-pacchetti-di-lavoro)
  - [11. Risorse](#11-risorse)
  - [12. Budget](#12-budget)
  - [13. Cambiamenti](#13-cambiamenti)
  - [14. Consegna](#14-consegna)

&nbsp;
## 1. Introduzione
**DeltaRent** è una piattaforma per il noleggio auto per aziende e privati, la piattaforma offre una sezione personale con i veicoli preferiti, i veicoli noleggiati e i veicoli attualmente in utilizzo. La pagina offre una sezione con un elenco per permettere la scelta del veicolo attraverso dei filtri (es. colore, tipo carrozzeria, potenza del motore, tipologia della trazione, ecc...), si avrà inoltre una pagina iniziale in cui fare l'accesso o registrare il proprio account (nome, cognome, email, data di nascita, eventale partita iva) al software. 

Ogni utente **privato** potrà noleggiare a proprio nome al massimo 2 veicoli, ogni utente registrato con **partita iva** invece potrà noleggiare quanti veicoli desideri, ogni veicolo avrà un massimo di kilometri percorribili annualmente nella tariffa base che potranno essere modificati in fase di scelta del veicolo con una tariffa aggiuntiva.

&nbsp;
## 2. Modello di Processo
Per implementare il software il team assumerà un'organizzazione RAD (Rapid Application Development), in tal modo ci sarà piu libertà di decisione per ogni membro del gruppo che potrà dedicarsi ai propri task in completa autonomia, in questo modo, e come dice la sigla lo sviluppo del software sarà più veloce. I requisiti verranno definiti in un documento in base alla tecnica MoSCoW.

### Fasi di progettazione
Seguendo il modello RAD il progetto verrà sviluppato in 5 fasi diverse:
  - **1. Analisi preliminaria e design**:
  in questa fase si definiscono i requisiti del progetto con l'intero team discutendo requisiti grafici, del cliente, caratteristiche, tempistiche, budget e rischi.
  - **2. Creazione di prototipi**:
  successivamente alla prima fase inizierà la fase di coding in cui si creeranno prototipi che verranno poi presentati al gruppo.
  - **3. Feedback**:
    non possedendo un pubblico a cui esporre i prototipi da testare il team giudicherà il proprio operato in modo autonomo di volta in volta fino a trovare un prototipo utilizzabile e definitivo.
  - **4. Testing**:
    il testing del prodotto finale avverrà sottoponendo il progetto al giudizio di altri gruppi o utenti esterni, le modifiche che avverranno in questa fase saranno modifiche legate all'User Interface e all'esperienza dell'utente.
  - **5. Presentazione dell'applicazione**:
    in questa fase finale il progetto ormai terminato verrà presentato al pubblico che verrà opportunamente istruito all'utilizzo.
      
&nbsp;
## 3. Organizzazione del progetto
L'organizzazioine RAD permetterà ai membri del team di sviluppare in completa autonomia le proprie task velocizzando lo sviluppo del progetto, settimanalmente avverranno videochiamate in cui ogni membro del gruppo aggiornerà il resto dei componenti del team sul proprio operato, il team non si avvale di un'organizzazione piramidale o gerarchica, ogni membro rende conto agli altri membri accettando consigli sul proprio lavoro e offrendone altri sul lavoro altrui.

Il lavoro del team verrà organizzato sulla base delle caratteristiche definite dai requisiti del progetto nella prima fase. I requisiti saranno decisi come già detto in precedenza sulla base della tecnica MoSCoW e saranno suddivisi in 4 macrogruppi:
- **Must have**:
  comprende i requisiti essenziali del progetto (pagina di log-in, menù principale...)
- **Should have**:
  i requisiti definiti in questa sezione saranno molto consigliati ma non obbligatori serviranno a rendere il progetto migliore dal punto di vista di utilità e completezza
- **Could have**:
  i requisiti **could have** serviranno a rendere migliore l'esperienza dell'utente, saranno requisiti evitabili in caso di altre necessità dovute a tempistiche ed eventuali ritardi che potrebbero occorrere
- **Wont have**:
  per ultimi verranno selezionati i requisiti che non verranno implementati all'interno del progetto, potrebbero essere fuzionalità presenti in progetti simili ma inutili allo scopo del progetto di questo gruppo

I requisiti verranno definiti tra i membri del gruppo non avendo clienti con cui andrebbero discussi per personalizzare al meglio il software.

&nbsp;
## 4. Standard, linee guida, procedure

  - **Standard di codifica**:
  il progetto seguirà le convenzioni di programmazione per il linguaggio Java definite da Oracle, per assicurare un'alta leggibilità e manutenibilità del codice. Questa convenzione sarà condivisa tra i membri del team, per mantenere coerenza ed avere un alto standard di qualità.
  
  - **Documentazione**:
  la documentazione seguirà gli standard UML (Unified Modeling Language), per rappresentare graficamente ed in maniera intuitiva il funzionamento del progetto. Saranno prodotti diversi diagrammi: use case diagram, class diagram, state machine diagram, sequence diagram, communication diagram/timing diagram, activity diagram e component diagram. Questi diagrammi verranno consegnati una volta terminata la progettazione del progetto ed una volta ultimati, e verranno aggiornati con ogni modifica del sistema.

  - **Qualità del software**:
  il progetto seguirà i seguenti quattro punti chiave delle normative ISO/IEC 9126 per garantire un prodotto di alta qualità:
    - Affidabilità: il sistema sarà progettato per ridurre al minimo i malfunzionamenti e per garantire un funzionamento stabile anche in condizioni estreme, come di carico elevato.
    - Usabilità: particolare attenzione sarà dedicata alla facilità d'uso dell'interfaccia utente, con un design intuitivo, accessibile e intuitivo per gli utenti finali.
    - Performance: verranno ottimizzati i tempi di risposta e la gestione delle risorse, per garantire un'esperienza fluida, anche sui dispositivi più datati o meno performanti.
    - Manutenibilità: il codice sarà scritto in modo tale da poter essere facilmente modificato, anche a lunga distanza dalla data di rilascio del programma e da nuovi sviluppatori che non hanno familiarità con il codice di questo programma.
  
  - **Strumenti utilizzati**:
  Verranno utilizzati diversi tools per far sì che tutto il team possa scrivere codice in modo efficiente e privo di errori. In particolare, verrà utilizzato GitHub, SonarQube, Papyrus ed Eclipse IDE.

&nbsp;
## 5. Attività di gestione

Per garantire un ambiente di lavoro il più efficiente possibile il team farà utilizzo di alcuni tool integrati in github e non:

  - **Issue**:
  Le Issue garantiranno al team un elenco delle task da compiere con possibilità di aggiungere commenti che serviranno ad avvisare gli altri membri del gruppo sull'operato svolto su una certa issue.
  
  - **Kanban Board**:
  La Kanban Board permettrà ai membri di aver sotto controllo la situazione generale del progetto, si potrà vedere quali task hanno più priorità, in che fase si trova una certa task, avere una visuale completa della roadmap con scadenze dei vari compiti.
  
  - **Discord**:
  La Piattaforma di chat Discord verrà principalmente utilizzata per fare videocall di aggiornamento sull' andamento del progetto, o per pianificare attività da aggiungere alla roadmap, le chiamate avverranno con frequenza variabile in base agli impegni dei vari membri del gruppo.

&nbsp;
## 6. Rischi

Di seguito vengono identificati i principali rischi e le relative contromisure per mitigarne l'impatto:

  - **Carenza di informazioni critiche**:
  la mancanza di dati o di chiarezza sui requisiti potrebbe portare ad errori durante lo sviluppo. Per questo, si utilizzano diverse tecniche di raccolta e revisione dei requisiti.

  - **Problemi tecnici**:
  malfunzionamenti critici potrebbero emergere in fase di sviluppo o di testing. Per questo motivo, verranno adottati test automatizzati, come SonarQube.

  - **Integrazione di strumenti esterni**:
  incompatibilità o malfunzionamenti potrebbero ostacolare lo sviluppo del software. Per contrastare questi problemi, il team ha familiarità con gli strumenti utilizzati.

  - **Ritardi nella consegna**:
  il team potrebbe riscontrare ritardi non calcolati e non riuscire a consegnare il progetto in tempo, quindi il team di sviluppo lavora utilizzando strumenti come le issue di GitHub per tenere traccia del progresso e mantenere margini di ritardo per eventuali problemi.

  - **Comunicazione inefficace nel team**:
  la mancata comunicazione o incomprensione tra i membri del team potrebbero generare errori nel progetto o ritardi, per questo il team ha programmato aggiornamenti regolari tramite Discord.

&nbsp;
## 7. Personale
Il team è composto da 3 membri con uguali compiti e capacità:

  - Falconi Davide 
  - Patelli Marco
  - Birolini Andrea

La struttura del team è basata sulla struttura SWAT (Skilled With Advanced Tools) non ci sarà perciò nessun tipo di differenziazione.

&nbsp;
## 8. Metodi e Tecniche

In questa sezione vengono illustrati i metodi e le tecniche adottati durante le fasi dell'ingegneria dei requisiti, della progettazione, dell'implementazione e del testing.

  - **Ingegneria dei requisiti**:
    - **Tecnica MoSCoW**: verrà utilizzata per la classificazione dei requisiti in Must have, Should have, Could have e Won't have. Questa suddivisione garantirà una chiara comprensione delle priorità e una gestione ottimale delle risorse.
    - **Raccolta dei requisiti**: i requisiti saranno raccolti nella specifica dei requisiti, dove verranno strutturati e aggiornati periodicamente con revisioni condivise dal team.
  - **Progettazione**: Verranno creati i seguenti diagrammi UML per rappresentare il progetto in maniera semplice ed intuitiva: use case diagram, class diagram, state machine diagram, sequence diagram, communication diagram/timing diagram, activity diagram e component diagram.

  - **Implementazione**:
    -   **Strumenti di sviluppo**: verrà utilizzato Eclipse IDE per lo sviluppo del codice e GitHub per la gestione del repository e del versionamento.
    - **Standard di codifica**: il codice seguirà le convenzioni Java definite da Oracle per assicurare leggibilità, manutenibilità e un'alta qualità del progetto in tutti i suoi aspetti.
    - **Controllo della configurazione**: il sistema di versionamento Git sarà adottato per tracciare ogni modifica al codice. Ogni feature sarà sviluppata da uno sviluppatore del nostro team, e verrà poi caricata sulla repository GitHub del progetto.

  - **Testing**: i test saranno eseguiti su dispositivi con differenti specifiche hardware e software per garantire la compatibilità ed il corretto funzionamento su diverse piattaforme. Verrà utilizzato SonarQube come strumento di analisi statica, e testing manuale da parte del team per verificare il funzionamento del software.

&nbsp;
## 9. Garanzia di Qualità

Per garantire che il software sviluppato soddisfi i requisiti di qualità dichiarati, il progetto DeltaRent adotterà un piano di assicurazione della qualità articolato nei seguenti punti:

  - **Organizzazione della qualità**:
    - **Responsabilità condivisa:** L'intero team è responsabile della qualità del progetto. Non essendoci ruoli gerarchici, ciascun membro è tenuto a verificare la conformità del proprio lavoro agli standard stabiliti e a collaborare per identificare e risolvere eventuali problemi riscontrati.
    - **Coordinamento:** Le sessioni di aggiornamento sulla piattaforma Discord e la continua comunicazione tramite WhatsApp, permetteranno di monitorare e migliorare continuamente la qualità del progetto.

  - **Procedure per la garanzia di qualità:**
    - **Revisione del codice:** Il codice sviluppato sarà sottoposto a revisioni da parte di tutti i membri del team, con feedback basati sugli standard di codifica definiti da Oracle.
    - **Testing:** Strumenti come SonarQube saranno utilizzati per identificare vulnerabilità ed errori commessi nella scrittura del codice. Ogni release del software sarà sottoposta a verifiche manuali e test di usabilità per garantire che risponda ai requisiti definiti e offra un’esperienza ottimale per l'utente.

  - **Standard di qualità adottati:**
    - **Normative ISO/IEC 9126:** Il software sarà sviluppato seguendo i principi di affidabilità, usabilità, performance e manutenibilità, come descritto nel capitolo 4.
    - **Documentazione UML:** Diagrammi aggiornati (use case, class diagram, sequence diagram, ecc.) saranno prodotti e mantenuti come supporto alla verifica della qualità durante tutto il ciclo di vita del progetto.
  - **Metriche di qualità:** verrà tenuto conto dei seguenti fattori di qualità durante la creazione del progetto: correttezza, affidabilità, efficienza, integrità, usabilità, manutenibilità, testatbilità, flessibilità, portabilità, riutilizzabilità e interoperabilità.

  - **Gestione dei problemi di qualità:** eventuali anomalie rilevate durante le revisioni o i test saranno documentate tramite issue su GitHub e assegnate al membro del team responsabile, che se ne occuperà della risoluzione.

&nbsp;
## 10. Pacchetti di Lavoro
Il lavoro verrà suddiviso e gestito tramite kanban board e issue, nella kanban board sono presenti le task da compiere che verranno prese in carico da un membro, egli si occuperà di spostare la task nella colonna di avanzamento della kanban board e di aggiungere data di inizio e data di termine. Verrà aperta inoltre una issue appena un task viene preso in carico.

&nbsp;
## 11. Risorse



&nbsp;
## 12. Budget



&nbsp;
## 13. Cambiamenti 
I cambiamenti minori e legati all'user experience avverranno nella fase di testing del progetto e verranno discussi con gli utenti per rendere l'interfaccia più facilmente utilizzabile. I cambiamenti di maggior dimensione avveranno tramite commit su github con relativa issue che informerà tutti i membri del gruppo del cambiamento avvenuto al codice, verrà quindi aperta una issue che verrà poi commentata e discussa da tutti i membri prima di attuare la modifica. I membri potranno consigliare delle modifiche al codice scritto da altri membri sempre tramite issue.

&nbsp;
## 14. Consegna
La consegna avverrà in due fasi, 1 mese prima dell'esame ci sarà la consegna del project plan mentre il progetto verrà consegnato il giorno dell'esame.



