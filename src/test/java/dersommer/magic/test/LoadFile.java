package dersommer.magic.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;

public interface LoadFile {

  default String loadFile(final String location) {
    try (InputStream input = this.getClass().getResourceAsStream(location); ByteArrayOutputStream output = new ByteArrayOutputStream()) {
      byte b[] = new byte[1024];
      int i = 0;
      while ((i = input.read(b)) > 0) {
        output.write(b, 0, i);
      }
      output.flush();
      return output.toString();
    } catch (IOException e) {
      Assertions.fail(String.format("Could not load %s", location));
    }
    return "";
  }

}