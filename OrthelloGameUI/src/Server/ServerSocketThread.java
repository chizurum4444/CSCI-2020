package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketThread extends Thread {
    protected ObjectInputStream objectInputStream = null;
    protected DataOutputStream objectOutputStream = null;
    protected ServerSocket serverSocket = null;
    protected Socket clientSocket = null;
    private int socketServerPORT;
    private int board[];

    public ServerSocketThread(int port, int[] board) {
        socketServerPORT = port;
        this.board = board;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(socketServerPORT);
            System.out.println("I'm waiting here: " + serverSocket.getLocalPort());

            clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getInetAddress());

            processCommand();

            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new DataOutputStream(clientSocket.getOutputStream());

            serverSocket.close();
            clientSocket.close();
        } catch (IOException ex) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendCommand(String s) throws Exception {
        objectOutputStream.writeUTF("SUPA COOL MOVES EH");
        objectOutputStream.flush();

        objectOutputStream.close();
        objectInputStream.close();
    }

    private void processCommand() throws Exception {
        String command = "";
        board[0] = 3;
        board[1] = 4;

        if (command.equalsIgnoreCase("1")) {
            System.out.println("Command 1 Processed");
            processCommand();
        } else if (command.equalsIgnoreCase("2")) {
            System.out.println("Command 2 Processed");
            processCommand();
        } else if (command.equalsIgnoreCase("3")) {
            System.out.println("Command 3 Processed");
            processCommand();
        } else {
            System.out.println("Ending Server!");
            clientSocket.close();
            serverSocket.close();
        }
    }
}
