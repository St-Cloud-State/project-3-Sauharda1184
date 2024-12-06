import java.io.IOException;

public class SaveCommand extends AbstractCommand {
  private String fileName;
  public SaveCommand(String fileName) {
    this.fileName = fileName;
  }
  public void execute() throws IOException {
    model.save(fileName);
  }
  public  boolean undo() {
    return false;
  }
  public  boolean redo() {
    return false;
  }
}
