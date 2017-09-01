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
    public static final double SPACING = 8;
    public static final double tearDist = 60;

    public Constraint(Unit2D unit1, Unit2D unit2) {
        this.unit1 = unit1;
        this.unit2 = unit2;
        length = Constraint.SPACING;
        AllConstraints.add(this);
    }

    public void resolve () {
        double dx = unit1.position.getX() - unit2.position.getX();
        double dy = unit2.position.getY() - unit2.position.getY();
        double dist = StrictMath.sqrt(dx * dx + dy * dy);
        if (dist == this.length) return;


        double diff = (this.length - dist) / dist;

        if (dist > tearDist) {unit1.free(unit2);unit2.free(unit1); AllConstraints.remove(this);}

        double mul = diff * 0.5 * (1 - this.length / dist);

        Vector2 delta = new Vector2(dx * mul, dy * mul);

        if (dist < this.length) {
            unit2.AddXY(delta);
            unit1.SubXY(delta);
            return;
        }
        unit1.AddXY(delta);
        unit2.SubXY(delta);
        return;
    }

}
