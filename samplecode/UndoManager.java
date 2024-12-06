import java.util.*;
public class UndoManager {
  private Stack<Command> undoStack;
  private Stack<Command> redoStack;
  private Command currentCommand;
  public UndoManager() {
    undoStack = new Stack<Command>();
    redoStack = new Stack<Command>();
  }
  public void beginCommand(Command command) {
    currentCommand = command;
  }
  public void endCommand(Command command) {
    try {
      if (currentCommand.end()) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
      }
    } catch (Exception e) {
      System.err.println("Error executing command: " + e.getMessage());
    }
  }
  public boolean undo() {
    if (undoStack.empty()) {
      return false;
    }
    Command command = undoStack.pop();
    command.undo();
    redoStack.push(command);
    return true;
  }
  public boolean redo() {
    if (redoStack.empty()) {
      return false;
    }
    Command command = redoStack.pop();
    try {
      command.execute();
      undoStack.push(command);
      return true;
    } catch (Exception e) {
      System.err.println("Error redoing command: " + e.getMessage());
      return false;
    }
  }
  public void cancelCommand() {
    currentCommand = null;
  }
}