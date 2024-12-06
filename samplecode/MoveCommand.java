import java.util.Vector;

public class MoveCommand extends Command {
    private Vector<Item> items;
    private int deltaX;
    private int deltaY;

    public MoveCommand(Vector<Item> items, int deltaX, int deltaY) {
        this.items = items;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public void execute() {
        for (Item item : items) {
            item.translate(deltaX, deltaY);
        }
        model.setChanged();
    }

    public boolean undo() {
        for (Item item : items) {
            item.translate(-deltaX, -deltaY);
        }
        model.setChanged();
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }
} 