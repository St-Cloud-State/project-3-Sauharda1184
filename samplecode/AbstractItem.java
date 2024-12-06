import java.awt.*;

public abstract class AbstractItem implements Item {
    protected double distance(Point point1, Point point2) {
        double xDifference = point1.getX() - point2.getX();
        double yDifference = point1.getY() - point2.getY();
        return Math.sqrt(xDifference * xDifference + yDifference * yDifference);
    }
} 