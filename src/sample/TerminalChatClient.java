package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TerminalChatClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 59001);
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            while(true) {
                Scanner scanner = new Scanner(socket.getInputStream());
                var line = scanner.nextLine();
                String username;
                String message;
                if(line.startsWith("SUBMITNAME")) {
                    System.out.println("Please enter name: ");
                    username = scanner.nextLine();
                    outputStream.writeUTF(username);
                    outputStream.flush();
                }
                else if(line.startsWith("NAMEACCEPTED")) {
                    System.out.println("You have entered the chat. Write /quit to exit.");
                }
                else if(line.startsWith("MESSAGE")) {
                    message = scanner.nextLine();
                    outputStream.writeUTF(message);
                    outputStream.flush();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
