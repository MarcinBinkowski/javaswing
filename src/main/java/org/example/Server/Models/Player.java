package org.example.Server.Models;

public class Player {
    private String position;
    private ServerClient client;

    public Player(ServerClient client) {
        this.client = client;
    }

    public ServerClient getClient() {
        return client;
    }
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    public void sendGameState(String gameStateString) {
        client.getWriter().println(gameStateString);
    }
    public void sentMessage(String message) {
        client.getWriter().println(message);
    }
}