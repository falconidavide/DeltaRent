# **Gestione del progetto**

# Indice
- [Indice](#indice)
- [1. Gestione del progetto](#1-gestione-del-progetto)
- [2. Design](#2-design)
- [3. Testing](#3-testing)
- [4. Manutenzione](#4-manutenzione)

&nbsp;
## 1. Gestione del progetto

### Modello di processo

Viene adottaro il modello di processo iterativo RAD (Rapid Application Development), caratterizzato da un approccio flessibile e orientato alla velocità, in cui il team SWAT (Skilled With Advanced Tools) svolge un ruolo fondamentale per garantire l'efficienza e la qualità del lavoro. Il processo si suddivide in quattro fasi principali:

1. **Pianificazione dei requisiti:** durante questa fase, vengono raccolti e analizzati i requisiti del progetto attraverso un'interazione continua con gli stakeholder. L'approccio iterativo consente di adattare i requisiti alle esigenze emergenti durante il ciclo di sviluppo.

2. **Progettazione dell'applicazione:** il team sviluppa prototipi rapidi per ottenere feedback immediato. Questa fase include attività di modellazione del sistema, design dell'architettura, e definizione delle interfacce utente, mantenendo un approccio iterativo e flessibile. La collaborazione tra i membri del team SWAT è essenziale per accelerare la realizzazione dei prototipi.

3. **Costruzione:** gli sviluppatori lavorano su iterazioni incrementali, sviluppando componenti modulari e integrandoli progressivamente nel sistema finale. Il feedback continuo guida il processo, garantendo che il prodotto soddisfi le aspettative dei clienti.

4. **Taglio (cutover):** questa fase rappresenta il rilascio del prodotto finale. Include test di validazione, formazione degli utenti, e il deployment del sistema nell'ambiente operativo.

### Differenze rispetto a quanto previsto nel Project 

Rispetto al Project Plan iniziale, il progetto si è sviluppato conformemente alle previsioni, non ci sono quindi differenze significative da evidenziare rispetto a quanto pianificato.

### Strumenti usati

Durante lo sviluppo del progetto, sono stati utilizzati i seguenti strumenti per garantire un’organizzazione efficace e un monitoraggio costante:

- **GitHub:** per la gestione del codice sorgente, il controllo delle versioni e la collaborazione tra i membri del team.
- **Discord:** la Piattaforma di chat Discord è stata utilizzata per mantenere l'intero team sempre aggiornato sull'andamento dello sviluppo.
- **WhatsApp:** l'applicazione di messaggistica istantanea WhatsApp è stata utilizzata in modo tale da tenere al corrente di ogni novità riguardante il progetto tutto il team.

### Gestione delle persone

La struttura organizzativa si basa su una struttura flat, in cui i membri hanno contribuito trasversalmente a diverse attività, adattandosi in base alle esigenze emergenti. Questo approccio ha favorito una maggiore flessibilità e una rapida risposta ai cambiamenti, con un focus sulla collaborazione e sull'apprendimento continuo. Le attività principali sono state assegnate in modo dinamico, attraverso discussioni di gruppo e meeting regolari. Le decisioni sono state prese collettivamente, evitando ruoli rigidi e favorendo un ambiente di lavoro inclusivo, in cui ogni membro ha avuto l'opportunità di contribuire in vari ambiti.

&nbsp;
## 2. Design

L'architettura del progetto DeltaRent è stata concepita seguendo un approccio modulare per garantire la manutenibilità e la scalabilità del sistema. Il design è suddiviso nei seguenti componenti principali:

1. **Front-end**
    - Il front-end è progettato per essere user-friendly, con un layout responsive e compatibile con diversi dispositivi.
    - Viene utilizzato il framework Java Swing, la libreria ufficiale per la realizzazione di interfacce grafiche in Java.
    - Vengono utilizzate icone facilmente riconoscibili, per facilitare la navigazione.
    - Le pagine principali includono un panello di login/registrazione per utenti privati ed aziendali, una dashboard personale con lo storico dei noleggi, una pagina con i dettagli del profilo ed un sistema di ricerca avanzata per selezionare veicoli in base a filtri, come tipo e disponibilità.
2. **Back-end**
    - Il back-end è sviluppato in Java, che gestisce la logica di registrazione, autenticazione, gestione dei noleggi e calcolo dei costi.
    - Per la gestione dei dati, viene utilizzato un database relazioname SQLite, per archiviare informazioni sugli utenti, veicoli e sulle prenotazioni.
    - La struttua del database è progettata per ottimizzare le query, e per garantire l'integrità e la sicurezza dei dati.
3. **Diagrammi UML**
    - Sono stati realizzati i seguenti diagrammi per rappresentare visivamente la struttura ed il funzionamento del sistema, in modo da migliorare la manutenibilità del sistema: use case diagram, class diagram, state machine diagram, sequence diagram, communication diagram/timing diagram, activity diagram e component diagram

&nbsp;
## 3. Testing
- Test per la validità dei dati di registrazione per un utente
- Test per la validità della email
- Test riguardante i requisiti di sicurezza per la password (minimo 8 caratteri, almeno una lettera maiuscola ed una minuscola, almeno un numero ed un carattere speciale)
- Test riguardanti la validità della Partita IVA
- Test per confalidare la data di nascita

&nbsp;
## 4. Manutenzione

Nel corso della fase di manutenzione del progetto, sono stati eseguiti diversi interventi di refactoring, mirati a migliorare la qualità del codice e la comprensibilità complessiva del sistema. In particolare, in diverse occasioni sono state rinominate le variabili, in modo da riflettere in modo più chiaro il loro scopo ed il loro utilizzo all'interno del codice. Anche i metodi hanno subito un processo di rinominazione per fornire una descrizione più significativa delle funzionalità. Sono inoltre stati rimossi diversi import non utlizzati. La spaziatura e la formattazione in generale è stata resa uniforme e conforme allo standard previsto nel Project Plan.

Questi cambiamenti sono stati adottati per ridurre le ambiguità, migliorare la leggibilità del codice e facilitare la comprensione e l'estensibilità del progetto da parte di membri del team che non hanno necessariamente lavorato su certe parti del codice, oppure in futuri nuovi membri del team. L'intervento si allinea ai principi di programmazine pulita, contribuendo ad un sistema più manutenibile e robusto.
