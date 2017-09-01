import zPhys.zField;

import java.awt.*;

public class MyPaint extends javax.swing.JPanel {
    zField field;
    public MyPaint(zField field) {
      this.field = field;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        DrawField(g);
    }


    public void DrawField(Graphics g) {
/*
        int wb = (int) (g.getClipBounds().getWidth() / field.cells);
        int hb = (int) (g.getClipBounds().getHeight() / field.cells);


        for (int y = 0; y <= field.cells; y++)
            for (int x = 0; x <= field.cells; x++) {
                ElectricCell e = field.GetCell(x, y);
                if (e == null) continue;
                //if(e.getLastObj()!=null)continue;

                g.setColor(Color.RED);
                if(e.path>0)g.fillRect(x * wb, y * hb, wb, hb);


                g.setColor(Color.BLACK);
                if (e.getLastObj() != null) {
                    g.drawRect(x * wb, y * hb, wb, hb);
                    continue;

                }

                if (StrictMath.abs(e.electricity) < 1.0D) continue;

                int r = e.electricity < 0 ? 255 : (int) StrictMath.abs(e.electricity);
                int gr = (int) StrictMath.abs(100 - e.electricity);
                int b = (int) StrictMath.abs(e.electricity * 30);
                g.setColor(new Color(r % 255, gr % 255, b % 255));

                g.fillRect(x * wb, y * hb, wb, hb);

            }



        Point2D slf = new Point2D(self.getX(), self.getY());
        for(int i = LIALGOLEN;i>0;i--){
            slf = field.GetNextPoint(slf,1);
            if(slf==null)break;
            g.setColor(Color.RED);
            g.fillRect((int)slf.getX()/field.cellSize * wb, (int)slf.getY()/field.cellSize * hb, wb, hb);
*/
        }


    }
