package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientConnectionHandler extends Thread{
    protected Socket socket = null;
    protected DataInputStream dataInputStream = null;
    protected DataOutputStream dataOutputStream = null;
    protected String dir = null;

    public ClientConnectionHandler(Socket socket, String dir){
        super();
        this.socket = socket;
        this.dir = dir;

        //Starts an IO data stream to read files
        try{
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        // initialize interaction
        processCommand();

        try {
            socket.close();
            interrupt();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void processCommand(){
        String command = null;

        try{
            int fileNameLength = dataInputStream.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);
            command = new String(fileNameBytes);
        } catch(IOException e) {
            e.printStackTrace();
            System.out.println("Error reading command");
        }

        if(command.equalsIgnoreCase("UPLOAD")) {

        } else if(command.equalsIgnoreCase("DOWNLOAD")) {

        } else if(command.equalsIgnoreCase("DIR")) {
        }
    }
}
