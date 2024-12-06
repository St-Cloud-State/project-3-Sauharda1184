public interface Command {
    void execute() throws Exception;
    boolean undo();
    boolean redo();
    boolean end();
}
