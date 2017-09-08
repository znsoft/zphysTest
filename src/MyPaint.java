import zPhys.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyPaint extends javax.swing.JPanel implements MouseListener {
    zField field;
    double wb,hb;
    public MyPaint(zField field) {
      this.field = field;
        this.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        DrawField(g);

    }


    public void DrawField(Graphics g) {
        wb =  (g.getClipBounds().getWidth() / field.x);
        hb =  (g.getClipBounds().getHeight() / field.y);

        g.clearRect(0,0,(int)g.getClipBounds().getWidth(),(int)g.getClipBounds().getHeight());

        g.setColor(Color.ORANGE);
        Point point = getMousePosition();
        if(point !=null){
            double xm = point.getX();
            double ym = point.getY();

            if(xm<g.getClipBounds().getWidth()&&ym<g.getClipBounds().getHeight()&&xm>0&&ym>0)
            {
                g.fillRect((int)(xm), (int)(ym), (int)10, (int)10);
            }else{zField.taked = null;}



        }

/*
        for (int y = 0; y <= field.y; y++)
            for (int x = 0; x <= field.x; x++) {
                zCell e = field.get(x, y);
                if (e == null) continue;

                if(e.inner.size()==0)continue;
                g.setColor(Color.CYAN);
                 //   g.drawRect((int)(x * wb), (int)(y * hb), (int)wb, (int)hb);

            }

*/
        for (zCell cell:field.touchedCells             ) {
            g.setColor(Color.LIGHT_GRAY);
            g.drawRect((int)(cell.x * wb), (int)(cell.y * hb), (int)wb, (int)hb);

        }

        for (Constraint c : Constraint.AllConstraints
                ) {
            if(c.isHide)continue;
            int r = (int) (c.rad * 2125);
            int x1 = (int)(c.unit1.position.getX() * wb);
            int x2 = (int)(c.unit2.position.getX() * wb);
            int y1 = (int)(c.unit1.position.getY() * hb);
            int y2 = (int)(c.unit2.position.getY() * hb);
            int r1 = (int)(c.rad *0.5 * wb);


            g.setColor(new Color(r));
           // g.drawOval(x1,y1,r1,r1);
          //  g.drawOval(x2,y2,r1,r1);
           g.drawLine((int)(c.unit1.position.getX() * wb),(int)(c.unit1.position.getY() * hb), (int)(c.unit2.position.getX() * wb),(int)(c.unit2.position.getY() * hb));
           // g.drawString(String.valueOf(c.rad),x1+(x2-x1)/2,y1+(y2-y1)/2);
        }
        }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
            double xm = e.getX();
            double ym = e.getY();

                field.Click(xm / wb, ym / hb, true);
        this.invalidate();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
