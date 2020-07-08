package java.src.io;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public interface Writable {

  // Deserialize the fields of this object from in.
  public void read(DataInput in) throws IOException;

  // Serialize the fields of this object to out.
  public void write(DataOutput out) throws IOException;
  
}
