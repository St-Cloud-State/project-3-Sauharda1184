import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PolygonButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
    private UndoManager undoManager;

    public PolygonButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
        super("Polygon");
        this.undoManager = undoManager;
        addActionListener(this);
        view = jFrame;
        drawingPanel = jPanel;
        mouseHandler = new MouseHandler();
    }

    public void actionPerformed(ActionEvent event) {
        view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private PolygonCommand polygonCommand;
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                if (pointCount == 0) {
                    polygonCommand = new PolygonCommand();
                    undoManager.beginCommand(polygonCommand);
                }
                polygonCommand.addPoint(View.mapPoint(event.getPoint()));
                pointCount++;
            } else if (SwingUtilities.isRightMouseButton(event) && pointCount > 2) {
                polygonCommand.execute();
                pointCount = 0;
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(polygonCommand);
            }
        }
    }
} 