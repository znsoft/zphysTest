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
    public static final double SPACING = 0.7; // длина спокойной (не сжатой и не растянутой) связи
    public static final double SPRING = 0.5; // длина сжатой связи
    public static final double tearDist = 3;// длина обрыва связи

    public Constraint(Unit2D unit1, Unit2D unit2) {
        this.unit1 = unit1;
        this.unit2 = unit2;
        length = Constraint.SPACING;
        AllConstraints.add(this);
    }

    public Constraint resolve () {
        double dx = unit1.position.getX() - unit2.position.getX();
        double dy = unit1.position.getY() - unit2.position.getY();
        double dist = StrictMath.sqrt(dx * dx + dy * dy);
        if (dist < this.length&&dist>Constraint.SPRING) return null;


        double diff = (this.length - dist) / dist;

        if (dist > tearDist && unit1.linked.size()<2&&unit2.linked.size()<2) {
            //if(unit1.linked.size()>1)
            unit1.free(unit2);
            //if(unit2.linked.size()>1)
            unit2.free(unit1);
            return this;}

        double mul = diff * 0.5 * (1 - this.length / dist);

        Vector2 delta = new Vector2(dx * mul, dy * mul);


        if (dist <= Constraint.SPRING) {
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
