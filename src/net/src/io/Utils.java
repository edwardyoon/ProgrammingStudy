package net.src.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

public class Utils {
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
}
