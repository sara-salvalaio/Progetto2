
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Faro> fariList = new ArrayList<>();
    public static final int PORT = 1050; // porta al di fuori del range 1-1024 !

    public static void main(String[] args) throws IOException {
            LettoreCSV lettore = new LettoreCSV("mappa-dei-fari-in-italia.csv", ";");
            fariList = lettore.leggiFari();

            // Avvia il server TCP dopo aver caricato i dati
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                System.out.println("Server in ascolto su porta " + PORT);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    new ThreadSocket(clientSocket).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }