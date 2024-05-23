package Negy.Utils;

import Negy.Mappings.ThePlayer;

public class ClientUtils {
    public static void displayChatMessage(String message) {
        ThePlayer.addChatMessage(message);
    }
}
