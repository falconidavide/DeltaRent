# **Project Plan**
In questo documento viene descritto in modo dettagliato il modello di sviluppo del progetto **FakeRent**; prenderne visione prima di analizzare il progetto è necessario.

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
**FakeRent** è una piattaforma per il noleggio auto per aziende e privati, la piattaforma offre una sezione personale con i veicoli preferiti, i veicoli noleggiati e i veicoli attualmente in utilizzo. La pagina offre una sezione con un elenco per permettere la scelta del veicolo attraverso dei filtri (es. colore, tipo carrozzeria, potenza del motore, tipologia della trazione, ecc...), si avrà inoltre una pagina iniziale in cui fare l'accesso o registrare il proprio account (nome, cognome, email, data di nascita, eventale partita iva) al software. 

Ogni utente **privato** potrà noleggiare a proprio nome al massimo 2 veicoli,
ogni utente registrato con **partita iva** invece potrà noleggiare quanti veicoli desideri, ogni veicolo avrà un massimo di kilometri percorribili annualmente nella tariffa base che potranno essere modificati in fase di scelta del veicolo con una tariffa aggiuntiva.

&nbsp;
## 2. Modello di Processo
Per implementare il software il team assumerà un'organizzazione RAD (Rapid Application Development), in tal modo ci sarà piu libertà di decisione per ogni membro del gruppo che potrà dedicarsi ai propri task in completa autonomia, in questo modo, e come dice la sigla lo sviluppo del software sarà più veloce. I requisiti verranno definiti in un documento in base alla tecnica MoSCoW.

- ### Fasi di progettazione
  Seguendo il modello RAD il progetto verrà sviluppato in 5 fasi diverse:
    - **1. Analisi preliminaria e design** 
      In questa fase si definiscono i requisiti del progetto con l'intero team discutendo requisiti grafici, del cliente, caratteristiche, tempistiche, budget e rischi.
    - **2. Creazione di prototipi** 
      Successivamente alla prima fase inizierà la fase di coding in cui si creeranno prototipi che verranno poi presentati al gruppo.
    - **3. Feedback**
      Non possedendo un pubblico a cui esporre i prototipi da testare il team giudicherà il proprio operato in modo autonomo di volta in volta fino a trovare un prototipo utilizzabile e definitivo.
    - **4. Testing**
      Il testing del prodotto finale avverrà sottoponendo il progetto al giudizio di altri gruppi o utenti esterni, le modifiche che avverranno in questa fase saranno modifiche legate all'User Interface e all'esperienza dell'utente.
    - **5. Presentazione dell'applicazione**
      In questa fase finale il progetto ormai terminato verrà presentato al pubblico che verrà opportunamente istruito all'utilizzo.
      
&nbsp;
## 3. Organizzazione del progetto
L'organizzazioine RAD permetterà ai membri del team di sviluppare in completa autonomia le proprie task velocizzando lo sviluppo del progetto, settimanalmente avverranno videochiamate in cui ogni membro del gruppo aggiornerà il resto dei componenti del team sul proprio operato, il team non si avvale di un'organizzazione piramidale o gerarchica, ogni membro rende conto agli altri membri accettando consigli sul proprio lavoro e offrendone altri sul lavoro altrui.

Il lavoro del team verrà organizzato sulla base delle caratteristiche definite dai requisiti del progetto nella prima fase. I requisiti saranno decisi come già detto in precedenza sulla base della tecnica MoSCoW e saranno suddivisi in 4 macrogruppi:
- **Must have**
    Comprende i requisiti essenziali del progetto (pagina di log-in, menù principale...)
- **Should have**
    I requisiti definiti in questa sezione saranno molto consigliati ma non obbligatori serviranno a rendere il progetto migliore dal punto di vista di utilità e completezza
- **Could have**
    I requisiti **could have** serviranno a rendere migliore l'esperienza dell'utente, saranno requisiti evitabili in caso di altre necessità dovute a tempistiche ed eventuali ritardi che potrebbero occorrere
- **Wont have**
    Per ultimi verranno selezionati i requisiti che non verranno implementati all'interno del progetto, potrebbero essere fuzionalità presenti in progetti simili ma inutili allo scopo del progetto di questo gruppo

I requisiti verranno definiti tra i membri del gruppo non avendo clienti con cui andrebbero discussi per personalizzare al meglio il software.
