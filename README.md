# Java Warehouse Manager (CLI)

Progetto in Java da riga di comando realizzato per gestire le giacenze di un piccolo magazzino. 
L'obiettivo principale di questo esercizio è applicare i concetti base della programmazione ad oggetti (OOP) e gestire la persistenza dei dati tramite file di testo locali (.txt), senza l'uso di librerie esterne o database.

## Funzionalità
- Mostra elenco prodotti: Stampa a schermo la lista degli articoli presenti in magazzino con ID, nome, quantità e prezzo.
- Aggiungi prodotto: Inserisce un nuovo articolo generando un ID progressivo automatico.
- Aggiorna quantità: Modifica la giacenza di un prodotto esistente selezionandolo tramite ID.
- Elimina prodotto: Rimuove un articolo dal magazzino.
- Salva e carica dati: All'avvio il programma legge i dati dal file magazzino.txt e ad ogni modifica aggiorna automaticamente il file su disco.

## Concetti Java applicati
- Programmazione ad Oggetti (OOP): Uso di classi, incapsulamento (getter/setter) e separazione della logica di gestione dal menu utente.
- Java Collections: Utilizzo di ArrayList per manipolare i dati in memoria.
- File I/O: Lettura e scrittura su file di testo tramite Scanner, File, FileWriter e PrintWriter.
- Gestione input ed eccezioni: Validazione dei dati inseriti da console e gestione degli errori con blocchi try-catch.

## Come eseguirlo
1. Clona la repository:
   git clone https://github.com/Zafkeil01/Java-Warehouse-Manager-CLI.git
2. Apri il progetto su IntelliJ IDEA o qualsiasi IDE Java.
3. Esegui il file Main.java.
