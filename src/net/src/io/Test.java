package net.src.io;

import java.io.IOException;
import net.src.socket.SimpleSocketClient;
import net.src.socket.SimpleSocketServer;

public class Test {

  public static void main(String[] args) {
    try {
      socketServerTest();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void socketServerTest() throws IOException {
    SimpleSocketServer server = new SimpleSocketServer(8888);
    SimpleSocketClient client = new SimpleSocketClient("localhost", 8888);
    new Thread(() -> {
      try {
        server.start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        ListWritable list = new ListWritable();
        list.add(1);
        list.add(2);
        list.add(3);

        if (client.connectToServer()) {
          for (int i = 0; i < 10; i++) {
            client.sendByteArray(Utils.serialize(list));
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          client.disconnect();
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

  }

  public static void serializationTest() {
    ListWritable listWritable = new ListWritable();
    listWritable.add(1);
    listWritable.add(2);
    listWritable.add(3);
    listWritable.add(4);

    byte[] bytes = Utils.serialize(listWritable);
    ListWritable l = new ListWritable();

    Utils.deserialize(bytes, l);
    for (int i = 0; i < l.size(); i++) {
      System.out.println(l.get(i));
    }
  }

}
