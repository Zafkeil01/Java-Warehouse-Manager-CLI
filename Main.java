import java.io.*;
import java.util.*;

// Classe Modello: Rappresenta il singolo prodotto
class Prodotto {
    private int id;
    private String nome;
    private int quantita;
    private double prezzo;

    public Prodotto(int id, String nome, int quantita, double prezzo) {
        this.id = id;
        this.nome = nome;
        this.quantita = quantita;
        this.prezzo = prezzo;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public int getQuantita() { return quantita; }
    public double getPrezzo() { return prezzo; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    // Converte l'oggetto in formato testo formattato per il file .txt
    public String toFileFormat() {
        return id + "," + nome + "," + quantita + "," + prezzo;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Nome: %-15s | Qta: %-5d | Prezzo: €%.2f", id, nome, quantita, prezzo);
    }
}

// Classe Gestore: Gestisce la logica di business e la persistenza su file
class GestoreMagazzino {
    private List<Prodotto> prodotti = new ArrayList<>();
    private final String FILE_PATH = "magazzino.txt";
    private int prossimoId = 1;

    public GestoreMagazzino() {
        caricaDaFile();
    }

    public void aggiungiProdotto(String nome, int quantita, double prezzo) {
        Prodotto p = new Prodotto(prossimoId++, nome, quantita, prezzo);
        prodotti.add(p);
        salvaSuFile();
        System.out.println("Prodotto aggiunto con successo!");
    }

    public void mostraProdotti() {
        if (prodotti.isEmpty()) {
            System.out.println("Il magazzino è attualmente vuoto.");
            return;
        }
        System.out.println("\n--- ELENCO PRODOTTI ---");
        for (Prodotto p : prodotti) {
            System.out.println(p);
        }
    }

    public void aggiornaQuantita(int id, int nuovaQuantita) {
        for (Prodotto p : prodotti) {
            if (p.getId() == id) {
                p.setQuantita(nuovaQuantita);
                salvaSuFile();
                System.out.println("Quantità aggiornata con successo!");
                return;
            }
        }
        System.out.println("Prodotto non trovato.");
    }

    public void eliminaProdotto(int id) {
        boolean rimosso = prodotti.removeIf(p -> p.getId() == id);
        if (rimosso) {
            salvaSuFile();
            System.out.println("Prodotto eliminato con successo!");
        } else {
            System.out.println("Prodotto non trovato.");
        }
    }

    // Salva l'intera lista dei prodotti nel file magazzino.txt
    private void salvaSuFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Prodotto p : prodotti) {
                writer.println(p.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Errore nel salvataggio su file: " + e.getMessage());
        }
    }

    // Carica i dati dal file magazzino.txt se già presente su disco
    private void caricaDaFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] parti = linea.split(",");
                if (parti.length == 4) {
                    int id = Integer.parseInt(parti[0]);
                    String nome = parti[1];
                    int qta = Integer.parseInt(parti[2]);
                    double prezzo = Double.parseDouble(parti[3]);

                    prodotti.add(new Prodotto(id, nome, qta, prezzo));
                    if (id >= prossimoId) {
                        prossimoId = id + 1;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Errore nel caricamento del file: " + e.getMessage());
        }
    }
}

// Classe Principale con Metodo Main
public class Main {
    public static void main(String[] args) {
        GestoreMagazzino gestore = new GestoreMagazzino();
        Scanner scanner = new Scanner(System.in);
        boolean inEsecuzione = true;

        while (inEsecuzione) {
            System.out.println("\n=== GESTIONALE MAGAZZINO (JAVA CLI) ===");
            System.out.println("1. Mostra tutti i prodotti");
            System.out.println("2. Aggiungi nuovo prodotto");
            System.out.println("3. Aggiorna quantità prodotto");
            System.out.println("4. Elimina prodotto");
            System.out.println("5. Esci");
            System.out.print("Scegli un'opzione: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
                continue;
            }

            int scelta = scanner.nextInt();
            scanner.nextLine();

            switch (scelta) {
                case 1 -> gestore.mostraProdotti();
                case 2 -> {
                    System.out.print("Nome prodotto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Quantità: ");
                    int qta = scanner.nextInt();
                    System.out.print("Prezzo (€): ");
                    double prezzo = scanner.nextDouble();
                    gestore.aggiungiProdotto(nome, qta, prezzo);
                }
                case 3 -> {
                    System.out.print("ID del prodotto da aggiornare: ");
                    int id = scanner.nextInt();
                    System.out.print("Nuova quantità: ");
                    int qta = scanner.nextInt();
                    gestore.aggiornaQuantita(id, qta);
                }
                case 4 -> {
                    System.out.print("ID del prodotto da eliminare: ");
                    int id = scanner.nextInt();
                    gestore.eliminaProdotto(id);
                }
                case 5 -> {
                    inEsecuzione = false;
                    System.out.println("Applicazione chiusa. I dati sono stati salvati su magazzino.txt!");
                }
                default -> System.out.println("Opzione non valida.");
            }
        }
        scanner.close();
    }
}