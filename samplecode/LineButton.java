import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LineButton extends JButton implements ActionListener {
    protected JPanel drawingPanel;
    protected View view;
    private MouseHandler mouseHandler;
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
        drawingPanel.addMouseListener(mouseHandler);
    }

    private class MouseHandler extends MouseAdapter {
        private LineCommand lineCommand;
        private int pointCount = 0;

        public void mouseClicked(MouseEvent event) {
            if (pointCount == 0) {
                lineCommand = new LineCommand();
                undoManager.beginCommand(lineCommand);
                lineCommand.setLinePoint(View.mapPoint(event.getPoint()));
                pointCount++;
            } else if (pointCount == 1) {
                lineCommand.setLinePoint(View.mapPoint(event.getPoint()));
                lineCommand.execute();
                pointCount = 0;
                drawingPanel.removeMouseListener(this);
                view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                undoManager.endCommand(lineCommand);
            }
        }
    }
}