package net.src.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class Test {
  
  public static byte[] serialize(Writable w) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DataOutput output = new DataOutputStream(out);
    try {
      w.write(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return out.toByteArray();
  }

  public static void deserialize(byte[] bytes, Writable obj) {
    DataInputStream in = new DataInputStream(new ByteArrayInputStream(bytes));
    try {
      obj.read(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ListWritable listWritable = new ListWritable();
    listWritable.add(1);
    listWritable.add(2);
    listWritable.add(3);
    listWritable.add(999);

    byte[] bytes = serialize(listWritable);
    ListWritable l = new ListWritable();

    deserialize(bytes, l);
    for (int i = 0; i < l.size(); i++) {
      System.out.println(l.get(i));
    }
  }
}
