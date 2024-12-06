import java.awt.*;
import java.io.*;
public abstract class Item implements Serializable {
  //protected static 
  //public static void setUIContext(UIContext uiContext) {
  //  Item.uiContext = uiContext;
  //}
  public abstract boolean includes(Point point);
  protected double distance(Point point1, Point point2) {
    double xDifference = point1.getX() - point2.getX();
    double yDifference = point1.getY() - point2.getY();
    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
  }
  public  abstract void render(UIContext uiContext);
    //uiContext.draw(this);
  //}
  public abstract void translate(int deltaX, int deltaY);
}
