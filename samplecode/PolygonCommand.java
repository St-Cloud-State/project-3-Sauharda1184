import java.awt.*;
import java.util.ArrayList;

public class PolygonCommand extends AbstractCommand {
    private final Polygon polygon;
    private ArrayList<Point> points;

    public PolygonCommand() {
        polygon = new Polygon();
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
        polygon.addPoint(point);
    }

    public void execute() {
        model.addItem(polygon);
    }

    public boolean undo() {
        model.removeItem(polygon);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }
} 