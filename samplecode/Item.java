import java.awt.*;
import java.io.*;

public interface Item extends Serializable {
    boolean includes(Point point);
    void render(UIContext uiContext);
    void translate(int deltaX, int deltaY);
}
