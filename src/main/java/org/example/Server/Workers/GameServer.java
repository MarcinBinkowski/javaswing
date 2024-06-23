package org.example.Server.Workers;

import org.example.Server.Models.GameState;
import org.example.Server.Models.Player;
import org.example.Server.Models.ServerClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class GameServer {

    private static final int SERVER_PORT = 2002;
    private final Queue<Player> playersQueue = new LinkedList<>();
    private final ServerSocket serverSocket;

    public GameServer() throws IOException {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException ex) {
            System.out.println("Error while creating the server");
            throw ex;
        }
    }

    public void acceptConnections() throws IOException {
        while (true) {
            Socket socket = serverSocket.accept();
            ServerClient client = new ServerClient(
                    socket,
                    new PrintWriter(socket.getOutputStream(), true),
                    new BufferedReader(new InputStreamReader(socket.getInputStream()))
            );
            Player player = new Player(client);
            playersQueue.add(player);
            player.sentMessage("Connected to server");
            if (playersQueue.size() >= 2) {
                Player player1 = playersQueue.remove();
                Player player2 = playersQueue.remove();
                player1.setPosition("left");
                player2.setPosition("right");
                new GameWorker(player1, player2, new GameState()).start();
            }
        }
    }
}
