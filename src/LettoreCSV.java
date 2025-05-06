import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LettoreCSV {
    private final String percorsoFile;
    private final String delimitatore;

    public LettoreCSV(String percorsoFile, String delimitatore) {
        this.percorsoFile = percorsoFile;
        this.delimitatore = delimitatore;
    }

    public List<Faro> leggiFari() {
        List<Faro> fari = new ArrayList<>();
        File file = new File(percorsoFile);

        if (!file.exists()) {
            System.out.println(" File non trovato: " + file.getAbsolutePath());
            return fari;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea = br.readLine(); // Salta intestazione
            if (linea == null) {
                System.out.println(" Il file è vuoto.");
                return fari;
            }

            while ((linea = br.readLine()) != null) {
                String[] valori = linea.split(";", -1);  // Usa il punto e virgola come delimitatore

                // Debug: Mostra ogni riga letta
                System.out.println("Riga letta: " + linea);
                System.out.println("Colonne trovate: " + valori.length);

                // Verifica che la riga contenga abbastanza colonne (almeno 9)
                if (valori.length < 9) {
                    System.out.println("️ Riga incompleta, ma verrà provata lo stesso: " + linea);
                }

                try {
                    // Prova a creare un oggetto Faro anche con informazioni parziali
                    Faro faro = new Faro(
                            valori[2],                           // nome
                            valori[1],                           // data e ora inserimento
                            valori[0],                           // comune
                            !valori[7].isEmpty() ? Double.parseDouble(valori[7]) : 0.0,  // longitudine
                            !valori[8].isEmpty() ? Double.parseDouble(valori[8]) : 0.0,  // latitudine
                            !valori[4].isEmpty() ? Integer.parseInt(valori[4]) : 0,     // anno inserimento
                            valori[3],                           // provincia
                            valori[5]                            // regione
                    );
                    fari.add(faro);
                } catch (NumberFormatException e) {
                    System.out.println(" Errore durante il parsing numerico: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println(" Errore durante la lettura del file:");
            e.printStackTrace();
        }

        System.out.println(" CSV caricato con " + fari.size() + " fari.");
        return fari;
    }
}