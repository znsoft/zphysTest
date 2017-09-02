/**
 * Created by es.zheludkov on 01.09.2017.
 */
import zPhys.zField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class zForm {
    private static final int WINDOWSIZE = 400;
    public JFrame frame;

    public void InitLocalVisualWindow(zField field) {
        javax.swing.JPanel paintTester = new MyPaint(field);
        frame = new javax.swing.JFrame();

        int cells = WINDOWSIZE;
        frame.setSize(cells, cells);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(paintTester);
        // frame.pack();
        frame.setVisible(true);
    }

    public void update(){


        frame.repaint(100, 0, 0, WINDOWSIZE, WINDOWSIZE);
        frame.invalidate();

    }


}







