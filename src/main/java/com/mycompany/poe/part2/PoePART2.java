/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe.part2;
import javax.swing.JOptionPane;
import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author RC_Student_Lab
 */
public class PoePART2 {

 private static boolean exit;
    private static int totalMessages = 0;
    private static int Messagecount;
    private static int messageCounter = 0;
    static final JSONArray messageStorage = new JSONArray();

    public static void main(String[] args) {
        if (!login("administrator", "5678")) {
            return;
        }

        while (!exit) {
            String[] options = {"Convey a message", "View Previous Messages", "EXIT'S"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Choose an option:", "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            switch (choice) {
                case 0: // Send Message
                    sendMessage();
                    break;

                case 1: // Show Recently Sent Messages
                    showRecentlySentMessages();
                    break;

                case 2: // Quit
                    exit = true;
                    JOptionPane.showMessageDialog(null, "Exiting the program. Goodbye!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option.");
                    break;
            }
        }

        JOptionPane.showMessageDialog(null, "Total messages sent: " + totalMessages);
        saveMessagesToJSON();
    }

    static boolean login(String administrator, String password) {
        String userName = JOptionPane.showInputDialog("Enter Username");
        String passWord = JOptionPane.showInputDialog("Enter Password");

        if ("administrator".equals(userName) && "5678".equals(passWord)) {
            JOptionPane.showMessageDialog(null, "Login successful. Welcome!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login failed.");
            return false;
        }
    }

    static void sendMessage() {
        long messageId = 10000000000L + new Random().nextInt(900000000);
        messageCounter++;

        String recipient = JOptionPane.showInputDialog("Enter recipient number (+CCXXXXXXXXXX):");
        recipient = checkRecipient(recipient);
        if (recipient == null) return;

        String message = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
        if (message == null || message.length() > 250) {
            JOptionPane.showMessageDialog(null, "Please enter a message of less than 250 characters.");
            return;
        }

        String[] words = message.trim().split("\\s+");
        String hash = String.format("%02d:%d:%s:%s",
                Long.valueOf(Long.toString(messageId).substring(0, 2)),
                messageCounter,
                words[0].toUpperCase(),
                words.length > 1 ? words[words.length - 1].toUpperCase() : "");

        String[] actions = {"Send", "Disregard", "Store"};
        int action = JOptionPane.showOptionDialog(null,
                "Choose what to do with this message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, actions, actions[0]);

        if (action == 1) {
            JOptionPane.showMessageDialog(null, "Message disregarded.");
            return;
        }

        JSONObject jsonMessage = new JSONObject();
        jsonMessage.put("MessageID", messageId);
        jsonMessage.put("MessageHash", hash);
        jsonMessage.put("Recipient", recipient);
        jsonMessage.put("Message", message);

        if (action == 2) {
            messageStorage.add(jsonMessage);
            JOptionPane.showMessageDialog(null, "Message stored.");
        } else {
            totalMessages++;
            JOptionPane.showMessageDialog(null,
                    """
                    Message Sent!
                    Message ID: """ + messageId + "\n" +
                            "Message Hash: " + hash + "\n" +
                            "Recipient: " + recipient + "\n" +
                            "Message: " + message);
        }
    }

    static void saveMessagesToJSON() {
        try (FileWriter file = new FileWriter("storedMessages.json")) {
            file.write(messageStorage.toJSONString());
            file.flush();
            System.out.println("Stored messages saved to storedMessages.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String checkRecipient(String recipient) {
       
        if (recipient == null || !recipient.matches("\\+\\d{9,12}")) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number. Must include international code and be 10–13 digits total.");
            return null;
        }
        return recipient;
    }

    static void showRecentlySentMessages() {
        if (messageStorage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Coming Soon..");
            return;
        }

        StringBuilder output = new StringBuilder("Stored Messages:\n\n");
        for (Object obj : messageStorage) {
            JSONObject msg = (JSONObject) obj;
            output.append("ID: ").append(msg.get("MessageID")).append("\n")
                    .append("Recipient: ").append(msg.get("Recipient")).append("\n")
                    .append("Message: ").append(msg.get("Message")).append("\n")
                    .append("---\n");
        }
        JOptionPane.showMessageDialog(null, output.toString());
    }
}