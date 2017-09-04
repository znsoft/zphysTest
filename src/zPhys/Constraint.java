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
    public double tearDist;// длина обрыва связи

    public static final double SPACING = 0.61; // длина спокойной (не сжатой и не растянутой) связи
    public static final double SPRINGCRASH = 0.95; // длина сжатой связи
    public static final double TEARDIST = 13;// длина обрыва связи
    public static final double GLUEDIST = 0.073;// длина обрыва связи
public boolean isHide;

    public Constraint(Unit2D unit1, Unit2D unit2, boolean ishide) {
        this.unit1 = unit1;
        this.unit2 = unit2;
        length = Constraint.SPACING * unit1.getDistanceTo(unit2);
        AllConstraints.add(this);
        tearDist = length * TEARDIST;
        isHide = ishide;
    }


    public Constraint resolve () {
        double dx = unit1.position.getX() - unit2.position.getX();
        double dy = unit1.position.getY() - unit2.position.getY();
        double dist = StrictMath.sqrt(dx * dx + dy * dy);
        rad = dist;
        if (dist == this.length) return null;


        double diff = (this.length - dist) / dist;
        double mul = diff * 0.15 * (1 - this.length / dist);

        Vector2 delta = new Vector2(dx * mul *0.9, dy * mul);

            if (dist > tearDist || delta.length()> SPRINGCRASH) {
            unit1.free(unit2);
            unit2.free(unit1);
            return this;}


        if (dist <= this.length) {
            unit2.AddXY(delta);
            unit1.SubXY(delta);
            return null;
        }
        unit1.AddXY(delta);
        unit2.SubXY(delta);
        return null;
    }

}
