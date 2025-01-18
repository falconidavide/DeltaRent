
## 1. Introduzione

**1.1 Obiettivo:** l'obiettivo del progetto DeltaRent è sviluppare una piattaforma intuitiva per il noleggio di automobili, accessibile sia a privati che ad aziende, che consenta di gestire account, prenotazioni, e pagamenti in modo efficiente.

**1.2 Scopo:** lo scopo del progetto è fornire un sistema moderno, scalabile e intuitivo per il noleggio di veicoli, migliorando la gestione interna e l'esperienza utente, attraverso l'integrazione di funzionalità avanzate come la gestione dello storico dei noleggi e un sistema di ricerca basato su filtri.

**1.3 Definizioni, acronimi e abbreviazioni:**

- **RAD:** Rapid Application Development, metodologia adottata per organizzare il team
- **MoSCoW:**:tecnica per la classificazione dei requisiti (Must have, Should have, Could have, Won't have)
- **UML:** Unified Modeling Language, utilizzato per la documentazione
- **IDE:** Ambiente di sviluppo integrato (Eclipse IDE)
- **Issue**: Task o problematica da gestire in GitHub
- **GUI:** Graphical User Interface, il layout visivo e i controlli che consentono all'utente di interagire col software
- **PP:** Project Plan
- **DB:** DataBase, la base di dati che utilizza il programma

**1.4 Riferimenti:**

- PP del progetto DeltaRent
- Documentazione UML
- Linee guida Java Oracle
- Standard ISO/IEC 9126
- Modello di sviluppo RAD

**1.5 Panoramica:** il documento descrive i requisiti del sistema DeltaRent, le sue funzionalità principali, le caratteristiche degli utenti, i vincoli progettuali e le dipendenze, fornendo dettagli per la progettazione e implementazione.

## 2. Descrizione generale

**2.1 Prospettiva del prodotto:** il prodotto finale sarà un'interfaccia applicativa in grado di permettere agli utenti di gestire il proprio profilo, di visualizzare le automobili disponibili al noleggio. La piattaforma offre una sezione personale per l'utente che mostra i veicoli preferiti e i veicoli attualmente noleggiati dall'utente stesso. L'interfaccia offre inoltre all'utente una sezione per permettere di cercare un veicolo attraverso dei filtri, come il colore o il tipo di carrozzeria. Gli utenti, che possono essere privati o azienda, potranno accedere oppure, se non hanno già creato un account, registrarsi fornendo le informazioni necessarie in base al tipo di utenza. 

**2.2 Funzioni del prodotto:** Le principali funzioni del prodotto saranno inerenti al noleggio auto, l'utente potrà programmare l'inizio di un noleggio piuttosto che terminarne un'altro, potrà selezionare dei veicoli da aggiungere alla lista dei veicoli preferiti, l'utente potrà inoltre visualizzare una prospettiva della rata mensile che pagherà in base ai servizi complementari del noleggio e aggiungendo il periodo del noleggio potrà visualizzare il prezzo finale per il noleggio. Viene controllata la sicurezza della password durante la fase di registrazione.

**2.3 Caratteristiche dell'utente:** L'utente sarà una persona fisica che opererà sulla poattaforma per proprio conto o per conto dell'azienda per cui lavora, nel caso di privato le informazioni rischieste saranno il proprio nome, cognome, indirizzo email e la data di nascita, nel caso di un'azienda privata l'operatore dovrà inserire la pec registrata dell'azienda, la partita iva e il nome dell'azienda.

**2.4 Vincoli:** Ogni utente privato può noleggiare attraverso il proprio account solamente automobili, e al massimo di 1 veicolo alla volta, mentre gli utenti registrati come aziendali potranno noleggiare solamente i furgoni.
All'inizio del noleggio, se l'auto è assegnata ad un utente privato verrà registrato l'orario di inizio del noleggio e il veicolo verrà contrassegnato come occupato, l'importo da pagare varia in base al numero di ore di noleggio che è scelto dall'utente in fase di prenotazione. Il costo del noleggio varia in base ai veicoli.

**2.5 Presupposti e dipendenze:** 

## 3. Requisiti specifici

**3.1 Requisiti dell'interfaccia esterna:**

<div style="margin-left: 32px;">

**3.1.1 Interfacce utente:** il programma è stato testato per supportare diverse risoluzioni differenti. Sono state implementate diverse schermate per effettuare le diverse funzioni del programma, e sono tutte state studiate in modo da essere intuitive per gli utenti. Le schermate sono progettate seguendo i principi di usabilità definiti dalle normative ISO/IEC 9126 per garantire un'esperienza utente ottimale.

**3.1.2 Interfacce hardware:** il programma è orientato all'utilizzo sullo schermo di un computer.

**3.1.3 Interfacce software:** il sistema si integra con un sistema di database.

**3.1.4 Interfacce di comunicazione:** non applicabile in questo caso.

</div>

**3.2 Richieste funzionali:**

<div style="margin-left: 32px;">

**3.2.1 Classe DataBase:** 

<div style="margin-left: 32px;">

**3.2.1.1 Requisito funzionale 1.1:** 

**3.2.1.2 Requisito funzionale 1.2:** 

...

</div>

**3.2.2 Classe utente 2:**


<div style="margin-left: 32px;">

**3.2.2.1 ...:** 

</div>

</div>

**3.3 Requisiti di prestazione:**

**3.4 Vincoli di progettazione:**

**3.5 Attributi del sistema software:**

**3.6 Altri requisiti:**
