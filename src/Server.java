
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Faro> fariList = new ArrayList<>();
    public static final int PORT = 1050; // porta al di fuori del range 1-1024 !

    public static void main(String[] args) throws IOException {
        loadCSV("mappa-dei-fari-in-italia.csv");

        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("server in ascolto su porta " + PORT);

            while(true){
                Socket clientSocket = serverSocket.accept();
                new ThreadSocket(clientSocket).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void loadCSV (String s) {
        try(BufferedReader br = new BufferedReader(new FileReader(s))){
            String line;
            br.readLine();

            while((line = br.readLine()) != null){
                String[] values = line.split(", ", -1);

                if(values.length >= 5){
                    try{
                        Faro faro = new Faro(
                                values[0],
                                values[1],
                                values[2],
                                Double.parseDouble(values[3]),
                                Double.parseDouble(values[4]),
                                Integer.parseInt(values[5]),
                                values[6],
                                values[7]
                        );
                        fariList.add(faro);
                    }catch(NumberFormatException NOE){
                        System.out.println("errore nella conversione " + line);
                    }
                }
            }
            System.out.println("CSV caricato con " + fariList.size() + "fari");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
