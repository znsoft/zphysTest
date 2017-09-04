import zPhys.Constraint;
import zPhys.Position2D;
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
            //if(zField.taked==null) {
                field.Click(xm / wb, ym / hb, true);
            //}else{
              //  Position2D pos = new Position2D(xm,ym);
                //zField.taked.MouseClick(pos);

           // }

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



            g.setColor(new Color(r));
            g.drawLine((int)(c.unit1.position.getX() * wb),(int)(c.unit1.position.getY() * hb), (int)(c.unit2.position.getX() * wb),(int)(c.unit2.position.getY() * hb));
           // g.drawString(String.valueOf(c.rad),x1+(x2-x1)/2,y1+(y2-y1)/2);
        }
        }


    }
