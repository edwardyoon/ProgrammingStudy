package net.src.socket;

import java.io.IOException;
import java.util.Scanner;
import net.src.io.ListWritable;
import net.src.io.Utils;

public class ClientTest {
  private static SimpleSocketClient client;
  public static void main(String[] args) {
    client = new SimpleSocketClient("localhost", 8888);
    try {
      client.connectToServer();
//      sendTextFromConsole(client);
      sendWritable();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private static void sendTextFromConsole() throws IOException {
    while (true) {
      Scanner sc = new Scanner(System.in);
      System.out.print("type your text: ");
      String message = sc.nextLine();
      client.sendTextMessage(message);
      client.receiveMessage();
    }
  }

  private static void sendWritable() throws IOException {
    ListWritable w = new ListWritable();
    w.add(4);
    w.add(5);
    w.add(6);
    client.sendByteArray(Utils.serialize(w));
    client.sendByteArray(Utils.serialize(w));
    client.sendByteArray(Utils.serialize(w));
    client.receiveMessage();
  }
}
