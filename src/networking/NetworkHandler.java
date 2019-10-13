package networking;

import game.Solver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkHandler {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public NetworkHandler() {
        try {
            this.socket = new Socket("localhost", 8963);
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getMoveFromClient(String serverMessage, Solver solver) throws IOException {
        solver.fillGrid(serverMessage);
        this.dos.writeUTF(solver.solve());
        serverMessage = this.dis.readUTF();
        return serverMessage;
    }

    public String tryGetMoveFromClient(String serverMessage, Solver solver){
        try {
            return this.getMoveFromClient(serverMessage, solver);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public String initializeGame(String sizeString)   {
        try {
            this.getDos().writeUTF(sizeString);
            return this.getDis().readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }
}
