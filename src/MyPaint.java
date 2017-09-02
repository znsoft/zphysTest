import zPhys.Constraint;
import zPhys.zCell;
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
        double wb =  (g.getClipBounds().getWidth() / field.x);
        double hb =  (g.getClipBounds().getHeight() / field.y);

        g.clearRect(0,0,(int)g.getClipBounds().getWidth(),(int)g.getClipBounds().getHeight());

        g.setColor(Color.ORANGE);
        Point point = getMousePosition();
        if(point !=null){
        double xm = point.getX();
        double ym = getMousePosition().getY();
        if(xm<g.getClipBounds().getWidth()&&ym<g.getClipBounds().getHeight()&&xm>0&&ym>0)
        {
            g.fillRect((int)(xm), (int)(ym), (int)10, (int)10);
            field.Click(xm/wb,ym/hb,true);
        }}


  /*      for (int y = 0; y <= field.y; y++)
            for (int x = 0; x <= field.x; x++) {
                zCell e = field.get(x, y);
                if (e == null) continue;

                if(e.inner.size()==0)continue;
                g.setColor(Color.LIGHT_GRAY);
                    g.drawRect((int)(x * wb), (int)(y * hb), (int)wb, (int)hb);

            }*/


        for (zCell cell:field.touchedCells             ) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect((int)(cell.x * wb), (int)(cell.y * hb), (int)wb, (int)hb);

        }

        for (Constraint c : Constraint.AllConstraints
                ) {
            g.setColor(Color.RED);
            g.drawLine((int)(c.unit1.position.getX() * wb),(int)(c.unit1.position.getY() * hb), (int)(c.unit2.position.getX() * wb),(int)(c.unit2.position.getY() * hb));
        }
        }


    }
