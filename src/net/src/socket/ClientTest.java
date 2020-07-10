package net.src.socket;

import java.io.IOException;
import net.src.io.ListWritable;
import net.src.io.Utils;

public class ClientTest {
  public static void main(String[] args) {
    SimpleSocketClient client = new SimpleSocketClient("localhost", 8888);
    try {
      client.connectToServer();
    } catch (IOException e) {
      e.printStackTrace();
    }
    ListWritable w = new ListWritable();
    w.add(4);
    w.add(5);
    w.add(6);

    try {
      client.sendByteArray(Utils.serialize(w));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
