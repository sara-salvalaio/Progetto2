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
        String[] parts = request.split(" ");
        if (parts.length == 0) return "ERROR: Comando vuoto";

        switch (parts[0]) {
            case "SEARCH_COMUNE":
                if (parts.length < 2) return "ERROR: Comune mancante";
                return searchByComune(request.substring("SEARCH_COMUNE ".length()));

            case "SEARCH_POSITION":
                if (parts.length != 3) return "ERROR: Formato posizione errato";
                try {
                    double lon = Double.parseDouble(parts[1]);
                    double lat = Double.parseDouble(parts[2]);
                    return searchByPosition(lon, lat);
                } catch (NumberFormatException e) {
                    return "ERROR: Coordinate non valide";
                }

            default:
                return "ERROR: Comando non riconosciuto";
        }
    }

    private String searchByComune(String comune) {
        StringBuilder result = new StringBuilder();
        for (Faro faro : Server.fariList) {
            if (faro.getComune().equalsIgnoreCase(comune)) {
                result.append(faro).append("\n");
            }
        }
        return result.length() > 0 ? result.toString() : "Nessun faro trovato nel comune: " + comune;
    }

    private String searchByPosition(double lon, double lat) {
        for (Faro faro : Server.fariList) {
            if (Math.abs(faro.getLongitudine() - lon) < 0.01 &&
                    Math.abs(faro.getLatitudine() - lat) < 0.01) {
                return faro.toString();
            }
        }
        return "Nessun faro trovato vicino a queste coordinate.";
    }
}