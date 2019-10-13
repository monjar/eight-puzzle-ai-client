import algorithms.UserInputAlgorithm;
import game.Solver;
import networking.NetworkHandler;


import java.io.DataOutputStream;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        NetworkHandler networkHandler = new NetworkHandler();
        String serverMessage = "";
        String sizeString = getMapSize(networkHandler.getDos());
        int size =Integer.valueOf(sizeString);
        Solver solver = new Solver(size, new UserInputAlgorithm());
        serverMessage = networkHandler.initializeGame(sizeString);
        startGame(networkHandler, serverMessage, solver);

    }

    private static void startGame(NetworkHandler networkHandler, String serverMessage, Solver solver) {
        while (!serverMessage.equals("Finished!")) {
            System.out.print(serverMessage);
            serverMessage = networkHandler.tryGetMoveFromClient(serverMessage, solver);
        }
        System.out.println(serverMessage);
    }


    private static String getMapSize(DataOutputStream dos) {
        System.out.println("Please enter a single integer as map size:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
