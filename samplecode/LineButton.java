import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LineButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private LineCommand lineCommand;
  private UndoManager undoManager;
  public LineButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Line");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  private class MouseHandler extends MouseAdapter {
    private int pointCount = 0;
    private PolygonCommand polygonCommand;

    public void mouseClicked(MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event)) {
            if (pointCount == 0) {
                polygonCommand = new PolygonCommand();
                polygonCommand.addPoint(View.mapPoint(event.getPoint()));
                undoManager.beginCommand(polygonCommand);
                pointCount++;
            } else {
                polygonCommand.addPoint(View.mapPoint(event.getPoint()));
                pointCount++;
            }
        } else if (SwingUtilities.isRightMouseButton(event) && pointCount > 1) {
            polygonCommand.execute();
            pointCount = 0; // Reset for next polygon
            drawingPanel.removeMouseListener(this);
            view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            undoManager.endCommand(polygonCommand);
        }
    }
  }
}