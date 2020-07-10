package net.src.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListWritable implements Writable {
  private List<Integer> buff;

  public ListWritable() {
    buff = new ArrayList<Integer>();
  }

  public Integer size() {
    return buff.size();
  }

  public Integer get(Integer i) {
    return buff.get(i);
  }

  public void add(int value) {
    buff.add(value);
  }

  @Override
  public void read(DataInput in) throws IOException {
    // TODO Auto-generated method stub
    int sizeOfData = 0;
    try {
      sizeOfData = in.readInt();
      if (sizeOfData > 0) {
        buff = new ArrayList<>();
      }
      for (int i = 0; i < sizeOfData; i++) {
        buff.add(in.readInt());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("read complete: " + buff.toString());
  }

  @Override
  public void write(DataOutput out) throws IOException {
    // TODO Auto-generated method stub
    out.writeInt(buff.size());
    for (Integer num : buff) {
      out.writeInt(num);
    }
  }

  @Override
  public String toString() {
    return buff.toString();
  }
}
