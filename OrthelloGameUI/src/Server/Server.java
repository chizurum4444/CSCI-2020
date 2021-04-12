package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected Socket player1Socket           = null;
    protected Socket player2Socket           = null;
    protected ServerSocket serverSocket     = null;
    protected ClientConnectionHandler[] threads    = null;
    protected int numClients                = 0;
    public static int SERVER_PORT = 25565;
    public static int MAX_CLIENTS = 100;

    protected DataInputStream player1Input = null;
    protected DataOutputStream player1Output = null;

    protected DataInputStream player2Input = null;
    protected DataOutputStream player2Output = null;

    public Server() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("---------------------------");
            System.out.println("Chat Server Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: "+SERVER_PORT);
            threads = new ClientConnectionHandler[MAX_CLIENTS];

            player1Socket = serverSocket.accept();
            System.out.println("Player 1 Connected");
            player1Input = new DataInputStream(player1Socket.getInputStream());
            player1Output = new DataOutputStream(player1Socket.getOutputStream());
            System.out.println(player1Input.readUTF());

            player2Socket = serverSocket.accept();
            System.out.println("Player 1 Connected");
            player2Input = new DataInputStream(player2Socket.getInputStream());
            player2Output = new DataOutputStream(player2Socket.getOutputStream());
            System.out.println(player2Input.readUTF());
        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public void start() throws Exception{
        player1Socket.close();
        player2Socket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws Exception {
        Server app = new Server();
        app.start();
    }
}
