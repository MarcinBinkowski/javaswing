package org.example.Helpers;

public class MessageHelper {

    public static final String GAME_STATE;
    public static final String GAME_STATE_PLAYER;
    public static final String START_GAME;


    private String type;
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    static {
        GAME_STATE = "GAME_STATE";
        GAME_STATE_PLAYER = "GAME_STATE_PLAYER";
        START_GAME = "START_GAME";
    }

    public MessageHelper(String type, String message) {
        this.type = type;
        this.message = message;
    }
    public MessageHelper(String serializedMessage) {
        String[] parts = serializedMessage.split(";");
        this.type = parts[0];
        this.message = parts[1];
    }

    public String serialize() {
        return getType() + ";" + getMessage();
    }

    public static MessageHelper deserialize(String serializedMessage) {
        String[] parts = serializedMessage.split(";");
        return new MessageHelper(parts[0], parts[1]);
    }

}
