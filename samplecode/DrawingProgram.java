public class DrawingProgram {
  public static void main(String[] args){
    Model model = new Model();
    UndoManager undoManager = new UndoManager();
    View view = new View();
    
    // Set up dependencies in correct order
    Model.setView(view);
    View.setModel(model);
    View.setUndoManager(undoManager);
    AbstractCommand.setUndoManager(undoManager);
    AbstractCommand.setModel(model);
    
    // Initialize the view last, after all dependencies are set
    view.initialize();
    view.setVisible(true);
  }
}