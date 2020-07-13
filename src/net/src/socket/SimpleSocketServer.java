package net.src.socket;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import net.src.io.ListWritable;
import net.src.io.Writable;

public class SimpleSocketServer {
  private Integer port;
  private ServerSocket serverSocket;
  private Socket socket;
  private InputStream input;
  private OutputStream output;
  public boolean isRunning;
  private boolean isReading;

  public SimpleSocketServer(Integer port) throws IOException {
    this.port = port;
  }

  public void start(String readingType) throws IOException {
    System.out.println("Server has been started at port:" + port);
    isRunning = true;
    isReading = true;
    serverSocket = new ServerSocket(port);
    while (isRunning) {
      socket = serverSocket.accept();
      input = socket.getInputStream();
      output = socket.getOutputStream();

      if(readingType.equals("text")){
        receiveMessage();
      } else if(readingType.equals("writable")) {
        receiveWritable();
      }
    }
  }

  public void receiveWritable() throws IOException {
    DataInput in = new DataInputStream(input);
    Writable listWritable = new ListWritable();
    listWritable.read(in);

    PrintWriter writer = new PrintWriter(output, true);
    System.out.println("Client: " + listWritable.toString());
    writer.println(listWritable.toString());
    writer.flush();
  }

  public void receiveMessage() throws IOException {
    String message = "";
    PrintWriter writer = new PrintWriter(output, true);
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    do {
      message = reader.readLine();
      System.out.println("Client: " + message);
      writer.println("server received: " + message);
      writer.flush();
    }
    while (isReading);
  }

  public void stop() throws IOException {
    input.close();
    output.close();
    socket.close();
    serverSocket.close();
    System.out.println("server stopped");
  }
}
