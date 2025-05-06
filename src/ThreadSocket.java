import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadSocket extends Thread{
    private Socket socket;

    public ThreadSocket(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                String response = handleRequest(inputLine);
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String handleRequest(String request) {
        // Separiamo la richiesta in base al carattere spazio per il tipo di comando
        String[] parts = request.split(" ", 2);

        // Verifica se il comando è vuoto
        if (parts.length == 0) {
            return "ERROR: Comando vuoto";
        }

        // In base al primo elemento (tipo di comando), selezioniamo cosa fare
        switch (parts[0]) {
            case "SEARCH_COMUNE":
                if (parts.length < 3 || parts[1].isEmpty()) {
                    return "ERROR: Comune mancante";
                }
                return searchByComune(parts[1]);

            case "SEARCH_POSITION":
                if (parts.length != 6) {
                    return "ERROR: Formato posizione errato";
                }
                try {
                    double lon = Double.parseDouble(parts[1]);
                    double lat = Double.parseDouble(parts[2]);
                    return searchByPosition(lon, lat);
                } catch (NumberFormatException e) {
                    return "ERROR: Coordinate non valide";
                }

            case "SEARCH_PROVINCIA":
                if (parts.length < 2 || parts[1].isEmpty()) {
                    return "ERROR: Provincia mancante";
                }
                return searchByProvincia(parts[1]);

            case "SEARCH_REGIONE":
                if (parts.length < 1 || parts[1].isEmpty()) {
                    return "ERROR: Regione mancante";
                }
                return searchByRegione(parts[1]);

            case "SEARCH_ANNO":
                if (parts.length < 4 || parts[1].isEmpty()) {
                    return "ERROR: Anno mancante";
                }
                return searchByAnno(parts[1]);

                default:
                return "ERROR: Comando non riconosciuto";

        }



    }

    private String searchByComune(String comune) {
        StringBuilder result = new StringBuilder();
        for (Faro faro : Server.fariList) {
            if (faro.getComune().equalsIgnoreCase(comune)) { // Confronto case-insensitive
                result.append(faro).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "Nessun faro trovato nel comune: " + comune;
    }



    private String searchByPosition(double lon, double lat) {
        for (Faro faro : Server.fariList) {
            if (Math.abs(faro.getLongitudine() - lon) < 0.1 &&  // Maggiore tolleranza
                    Math.abs(faro.getLatitudine() - lat) < 0.1) {   // Maggiore tolleranza
                return faro.toString();
            }
        }
        return "Nessun faro trovato vicino a queste coordinate.";
    }

    private String searchByProvincia(String provincia) {
        StringBuilder result = new StringBuilder();
        for (Faro faro : Server.fariList) {
            if (faro.getComune().equalsIgnoreCase(provincia)) { // Confronto case-insensitive
                result.append(faro).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "Nessun faro trovato nella provincia: " + provincia;
    }

    private String searchByRegione(String regione) {
        StringBuilder result = new StringBuilder();
        for (Faro faro : Server.fariList) {
            if (faro.getRegione().equalsIgnoreCase(regione)) {
                result.append(faro).append("\n");

            }
        }
        return result.length() > 0 ? result.toString() : "Nessun faro presente nella regione: " + regione;
    }
    private String searchByAnno(String anno) {
        StringBuilder result = new StringBuilder();
        for (Faro faro : Server.fariList) {
            result.append(faro).append("\n");
        }
        return result.length() > 0 ? result.toString() : "Nessun faro presente nella anno: " + anno;
    }

}