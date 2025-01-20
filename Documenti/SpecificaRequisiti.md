
## 1. Introduzione

**1.1 Obiettivo:** l'obiettivo del progetto DeltaRent è sviluppare una piattaforma intuitiva per il noleggio di automobili, accessibile sia a privati che ad aziende, che consenta di gestire account, e prenotazioni in modo efficiente.

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

**2.1 Prospettiva del prodotto:** il prodotto finale sarà un'interfaccia applicativa in grado di permettere agli utenti di gestire il proprio profilo, di visualizzare le automobili disponibili al noleggio. La piattaforma offre una sezione personale per l'utente che mostra lo storico dei veicoli noleggiati dall'utente stesso. L'interfaccia offre inoltre all'utente una sezione per permettere di cercare un veicolo attraverso dei filtri, come il modello di auto o il tipo di alimentazione. Gli utenti, che possono essere privati o azienda, potranno accedere oppure, se non hanno già creato un account, registrarsi fornendo le informazioni necessarie in base al tipo di utenza. 

**2.2 Funzioni del prodotto:** le principali funzioni del prodotto saranno inerenti al noleggio auto, come il fatto che l'utente potrà scegliere al momento della prenotazione l'orario di inizio e di termine del noleggio, oppure che l'utente, una volta effettuato il login, può cercare un veicolo da noleggiare in base a diversi filtri. Viene controllata la sicurezza della password durante la fase di registrazione (deve essere lunga almeno 8 caratteri, deve contenere almeno una lettera maiuscola ed una minuscola, almeno un numero ed un carattere speciale).

**2.3 Caratteristiche dell'utente:** l'utente sarà una persona fisica che opererà sulla piattaforma per proprio conto o per conto dell'azienda per cui lavora, nel caso di privato le informazioni rischieste saranno il proprio nome, cognome, indirizzo email e la data di nascita, nel caso di un'azienda privata l'operatore dovrà inserire la pec registrata dell'azienda, la partita iva e il nome dell'azienda.

**2.4 Vincoli:** ogni utente privato può noleggiare attraverso il proprio account solamente automobili, e al massimo di 1 veicolo alla volta, mentre gli utenti registrati come aziendali potranno noleggiare solamente i furgoni. Per ogni noleggio, l'auto è assegnata ad un utente privato, e verranno registrati gli orari di inizio e termine del noleggio, l'importo da pagare varia in base al numero di ore di noleggio, scelto dall'utente. Il costo del noleggio varia in base ai veicoli.

**2.5 Presupposti e dipendenze:** la piattaforma sarà utilizzata principalmente su dispositivi desktop con un sistema operativo moderno. Gli utenti aziendali devono disporre delle informazioni necessarie per la registrazione (PEC, Partita IVA, nome azienda). Il programma verrà integrato con un sistema di pagamento online per gestire le transazioni relative ai noleggi. Ogni membro del team deve avere a disposizione un dispositivo sul quale sviluppare il progetto, e sul quale ci sarà installato Discord e WhatsApp per la comunicazione. La piattaforma non sarà inizialmente ottimizzata per i dispositivi mobili. I test saranno condotti principalmente su sistemi operativi Windows e macOS.

## 3. Requisiti specifici

**3.1 Requisiti dell'interfaccia esterna:**

<div style="margin-left: 32px;">

**3.1.1 Interfacce utente:** il programma è stato testato per supportare diverse risoluzioni differenti. Sono state implementate diverse schermate per effettuare le diverse funzioni del programma, e sono tutte state studiate in modo da essere intuitive per gli utenti. Le schermate sono progettate seguendo i principi di usabilità definiti dalle normative ISO/IEC 9126 per garantire un'esperienza utente ottimale.

**3.1.2 Interfacce hardware:** il programma è progettato per essere utilizzato principalmente su schermi desktop, supportando le risoluzioni più comuni per questi dispositivi.

**3.1.3 Interfacce software:** il sistema si integra con un database SQlite per la gestione delle informazioni relative agli utenti, ai veicoli e ai noleggi. Le transazioni online saranno gestite tramite un'integrazione con un sistema di pagamento sicuro.

**3.1.4 Interfacce di comunicazione:** non applicabile in questo caso.

</div>

**3.2 Richieste funzionali:**

<div style="margin-left: 32px;">

**3.2.1 Classe DataBase:**

<div style="margin-left: 32px;">

**3.2.1.1 Gestione utenti:** gli utenti si devono poter registrare solamente in seguito alla validazione delle informazioni richieste. La password verrà controllata, in modo da rispettare dei requisiti di sicurezza.

**3.2.1.2 Gestione veicoli:** i veicoli non possono essere prenotati da altri utenti negli orari in cui sono già stati prenotati da altri utenti.

</div>

**3.2.2 Classe utente:**

<div style="margin-left: 32px;">

**3.2.2.1 Gestione account:** creazione e modifica del profilo personale, visualizzazione dello storico noleggi

**3.2.2.2 Interazione con il sistema:** selezione dei veicoli e gestione di una lista dedicata; calcolo del costo finale del noleggio in base al veicolo e al periodo di prenotazione selezionato.

</div>

**3.2.3 Classe noleggio:**

<div style="margin-left: 32px;">

**3.2.3.1 Prenotazione:** creazione e gestione di una prenotazione in base ad una specifica data ed orario.

**3.2.3.2 Chiusura noleggio:** il veicolo prenotato è disponibile nuovamente per il noleggio in automatico, in base all'orario di termine della prenotazione indicato.

</div>

</div>

**3.3 Requisiti di prestazione:** il sistema deve garantire tempi di risposta brevi per tutte le operazioni, inclusa la ricerca di veicoli e la prenotazione. Il database deve supoprtare un carico di diversi utenti simultanei. Il sistema deve essere in grado di gestire un alto numero di veicoli.

**3.4 Vincoli di progettazione:** il sistema deve essere sviluppato in linguaggio di programmazinoe Java, e deve utilizzare il framework Swing per la gestione dell'interfaccia grafica. Il DataBase relazionale deve essere implementato utilizzando SQlite. Il sistema deve seguire le linee guida di codifica di Oracle per garantire coerenza e leggibilità del codice.

**3.5 Attributi del sistema software:**

- **Affidabilità:** il sistema deve essere progettato per prevenire crash o malfunzionamenti.
- **Usabilità** l'interfaccia deve essere semplice, intuitiva e conforme agli standard di usabilità ISO/IEC 9126.
- **Manutenibilità:** il codice deve essere documentato e strutturato per facilitare futuri aggiornamenti o modifiche.
- **Sicurezza:** tutte i dati sono gestiti in modo da mantenerne la segretezza, per evitare che utenti non autorizzati possano visualizzare le informazioni di altri utenti.

**3.6 Altri requisiti:** il software deve essere compatibile con vesrioni recenti di sistemi operativi Windows e macOS. Il sistema deve essere progettato per supportare future espansioni, come l'aggiunta di nuove caratteristiche dei veicoli o altre funzionalità.
