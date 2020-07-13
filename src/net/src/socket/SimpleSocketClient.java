package net.src.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleSocketClient {
  private String hostName;
  private Integer port;
  private Socket socket;
  private InputStream input;
  private OutputStream output;
  private boolean isReading;

  public SimpleSocketClient(String hostname, Integer port) {
    this.hostName = hostname;
    this.port = port;
  }

  public boolean connectToServer() throws IOException {
    System.out.println("Connecting to Server " + hostName + ":" + port);
    socket = new Socket(hostName, port);
    output = socket.getOutputStream();
    input = socket.getInputStream();
    isReading = true;
    new Thread(() -> {
    }).start();

    return socket.isConnected();
  }

  public void sendByteArray(byte[] bytes) throws IOException {
    DataOutputStream out = new DataOutputStream(output);
    out.write(bytes);
    out.flush();
  }

  public void disconnect() throws IOException {
    output.close();
    input.close();
    socket.close();
  }

  public void receiveMessage() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    String message = reader.readLine();
    System.out.println("Server: " + message);
  }

  public void sendTextMessage(String message) {
    System.out.println("sending Message: " + message);
    PrintWriter writer = new PrintWriter(output, true);
    writer.println(message);
    writer.flush();
  }
}
