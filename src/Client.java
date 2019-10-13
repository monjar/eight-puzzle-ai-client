import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8963);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String serverMessage = "";
            dos.writeUTF("3");
            Solver solver = new TestSolver( 3);
            while (!serverMessage.equals("Finished!")){
                serverMessage = dis.readUTF();
                solver.fillGrid(serverMessage);
                System.out.print(serverMessage);
                dos.writeUTF(solver.makeMove());
            }
            System.out.println(dis.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
