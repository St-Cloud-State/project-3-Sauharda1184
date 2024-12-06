import java.awt.*;
import java.util.ArrayList;

public class Polygon extends Item {
    private ArrayList<Point> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public boolean includes(Point point) {
        // Implement point-in-polygon logic if needed
        return false; // Placeholder
    }

    public void render(UIContext uiContext) {
        if (points.size() > 1) {
            for (int i = 0; i < points.size() - 1; i++) {
                uiContext.drawLine(points.get(i), points.get(i + 1));
            }
            // Draw line from last point to first point to close the polygon
            uiContext.drawLine(points.get(points.size() - 1), points.get(0));
        }
    }

    public ArrayList<Point> getPoints() {
        return points;
    }
} 