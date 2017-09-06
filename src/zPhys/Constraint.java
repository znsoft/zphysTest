package zPhys;

import java.util.HashSet;

/**
 * Created by es.zheludkov on 01.09.2017.
 */
public class Constraint {
    public static final double XDROP = 1.0;
    public static final double CONSTRAINTFRICTION = 0.5;
    public static HashSet<Constraint> AllConstraints = new HashSet<>();
    public Unit2D unit1;
    public Unit2D unit2;
    public double length;
    public double rad;
    public double tearDist;// длина обрыва связи

    public static final double SPACING = 0.61; // длина спокойной (не сжатой и не растянутой) связи
    public static final double SPRINGCRASH = 1.95; // длина сжатой связи
    public static final double TEARDIST = 15.5;// длина обрыва связи
    public static final double GLUEDIST = 0.5455273;// длина обрыва связи
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
        double mul = diff * CONSTRAINTFRICTION * (1 - this.length / dist);

        Vector2 delta = new Vector2(dx * mul * XDROP, dy * mul);


        if (dist > tearDist )//|| delta.length()> SPRINGCRASH) {
            return this.tear();//}

        if (dist <= GLUEDIST){
            unit1.position.swap();
            unit2.position.swap();



        }
        if (dist <= this.length) {
            //unit2.AddXY(delta);
            //unit1.SubXY(delta);
            unit1.AddForce(delta.Mul(-1));
            unit2.AddForce(delta.Mul(-1));

            return null;
        }
        unit1.AddForce(delta.Mul(1));
        unit2.AddForce(delta.Mul(-1));

        //unit1.AddXY(delta);
        //unit2.SubXY(delta);
        return null;
    }

    public Constraint tear(){
        unit1.free(this);
        return this;
    }

}
