import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Interfaccia extends JFrame {
    private JTextField commandField;
    private JButton sendButton;
    private JTextArea responseArea;

    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    public Interfaccia() {
        setTitle("Fari Client - GUI");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Comando input + bottone
        JPanel inputPanel = new JPanel();
        commandField = new JTextField(40);
        sendButton = new JButton("Invia");
        inputPanel.add(commandField);
        inputPanel.add(sendButton);

        // Output area
        responseArea = new JTextArea();
        responseArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(responseArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Azione bottone
        sendButton.addActionListener(e -> sendCommand());

        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket("localhost", 5000);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            responseArea.append("Connesso al server.\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Errore di connessione al server.", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void sendCommand() {
        String command = commandField.getText();
        if (command.isEmpty()) return;

        out.println(command);
        commandField.setText("");

        try {
            String responseLine;
            responseArea.append("Comando inviato: " + command + "\n");
            while ((responseLine = in.readLine()) != null) {
                responseArea.append(responseLine + "\n");
                if (!in.ready()) break;
            }
        } catch (IOException e) {
            responseArea.append("Errore durante la lettura della risposta.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfaccia().setVisible(true));
    }
}