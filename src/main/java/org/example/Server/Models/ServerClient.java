package org.example.Server.Models;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerClient {

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;


    public Socket getSocket() {
        return socket;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public ServerClient(Socket socket, PrintWriter writer, BufferedReader reader) {
        this.socket = socket;
        this.writer = writer;
        this.reader = reader;
    }

}