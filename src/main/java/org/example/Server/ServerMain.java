package org.example.Server;


import org.example.Server.Workers.GameServer;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        System.out.println("Server started");
        new GameServer().acceptConnections();
    }
}