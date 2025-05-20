# ProgettoSistemi

Progetto per la ricerca dei fari in tutta Italia.
Questo progetto dispone di 3 classi principali e 2 classi secondarie:

# Client
Il client contiene la porta utilizzata 1050 e l'indirizzo utilizzato 127.0.0.1. .
Successivamente troviamo edei metodi per l'invio e la scrittura dei dati per il server, e tutti i messaggi che possiamo scriver eper farci inviare i dati.
Infine troviamo due cicli che servono rispettivamnete per chiudere la connessione e per la visualizzaizone dei dati tramite il server.

# Server 
Il server è caratterizzato da una struttura dati che è l'arrayList che contiene tutti gli oggetti di tipo Faro e la porta 1050 uguale a quella del Client. 
Poi il metodo Main contiene il file da dove verranno scaricati gli oggetti il metodo accept() che serve per l'accettazione della porta del server e il metodo Start() che serve per la creazione di un nuovo Thread per ogni richiesta.
Il secondo metodo che abbiamo è loadCSV, come dice il nome serve per scaricare il file e attribuire un valore a ogni oggetto scaricato.

# ThreadSocket
Il primo metodo che troviamo è run.
Ogni volta che il thread viene avviato con il metodo start(), entra in un ciclo dove legge i dati inviati dal client, e invia una risposta al client. 
Il ciclo continua fino a quando il client non chiude la connessione.
troviamo poi un menù dove possiamo scergliere come cercare il Faro (in base alla posizione e all'anno di inserimento). successivamnete tutti i metodi che servono per la ricerca in base alla richiesta

# Lettore CSV
Serve per la lettura dei dati dal File CSV

# Parametri CSV 
Contiene tutti i metodi SET e GET e il costruttore.
