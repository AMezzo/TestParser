package tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FileGeneratorTool implements Iterator<String> {
  private List<String> lines;
  private int index;

  public abstract void regenerateSourceFile();

  public FileGeneratorTool(Path filePath) {
    this.index = 0;

    try {
      this.lines = Files.readAllLines(filePath).stream()
          .filter(line -> line.trim().length() != 0 && !line.trim().startsWith("#"))
          .collect(Collectors.toList());
    } catch (IOException e) {
      ToolHelpers.failExecution(e);
    }
  }

  @Override
  public boolean hasNext() {
    return this.index < this.lines.size();
  }

  @Override
  public String next() {
    if (!hasNext()) {
      return "";
    }

    return this.lines.get(this.index++);
  }
}
