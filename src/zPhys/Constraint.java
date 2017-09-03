package zPhys;

import java.util.HashSet;

/**
 * Created by es.zheludkov on 01.09.2017.
 */
public class Constraint {
    public static HashSet<Constraint> AllConstraints = new HashSet<>();
    public Unit2D unit1;
    public Unit2D unit2;
    public double length;
    public double rad;
    public static final double SPACING = 0.61; // длина спокойной (не сжатой и не растянутой) связи
    public static final double SPRING = 0.7; // длина сжатой связи
    public static final double tearDist = 20;// длина обрыва связи
public boolean isHide;

    public Constraint(Unit2D unit1, Unit2D unit2, boolean ishide) {
        this.unit1 = unit1;
        this.unit2 = unit2;
        length = Constraint.SPACING * unit1.getDistanceTo(unit2);
        AllConstraints.add(this);

        isHide = ishide;
    }

    public Constraint(Unit2D unit1, Unit2D unit2, double spacing) {
        this.unit1 = unit1;
        this.unit2 = unit2;
        length = spacing;

        AllConstraints.add(this);
        isHide = false;

    }


    public Constraint resolve () {
        double dx = unit1.position.getX() - unit2.position.getX();
        double dy = unit1.position.getY() - unit2.position.getY();
        double dist = StrictMath.sqrt(dx * dx + dy * dy);
        rad = dist;
        //if (dist < this.length&&dist>Constraint.SPRING) return null;


        double diff = (this.length - dist) / dist;

//        if (dist > tearDist && unit1.linked.size()<2&&unit2.linked.size()<2) {
            if (dist > tearDist ) {
            //if(unit1.linked.size()>1)
            unit1.free(unit2);
            //if(unit2.linked.size()>1)
            unit2.free(unit1);
            return this;}

        double mul = diff * 0.15 * (1 - this.length / dist);

        Vector2 delta = new Vector2(dx * mul *0.5, dy * mul);

       // if(delta.radius()>tearDist )delta.normalize().Mul(-tearDist);

        if (dist <= this.length) {
            //delta.Mul(0.755);
           //unit2.AddForce(delta);
           //unit1.AddForce(delta.Mul(-1));
            unit2.AddXY(delta);
            unit1.SubXY(delta);
            return null;
        }
        //unit1.AddForce(delta);
        //unit2.AddForce(delta.Mul(-1));

        unit1.AddXY(delta);
        unit2.SubXY(delta);
        return null;
    }

}
