public abstract class AbstractCommand implements Command {
    protected static UndoManager manager;
    protected static Model model;

    public static void setModel(Model model) {
        AbstractCommand.model = model;
    }

    public static void setUndoManager(UndoManager undoManager) {
        AbstractCommand.manager = undoManager;
    }

    @Override
    public boolean end() {
        return true;
    }
} 