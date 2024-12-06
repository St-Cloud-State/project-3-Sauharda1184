import java.io.IOException;

public class OpenCommand extends AbstractCommand {
  private String fileName;
  public OpenCommand(String fileName) {
    this.fileName = fileName;
  }
  public void execute() throws IOException, ClassNotFoundException {
    AbstractCommand.model.retrieve(fileName);
  }
  public  boolean undo() {
    return false;
  }
  public  boolean redo() {
    return false;
  }
}
