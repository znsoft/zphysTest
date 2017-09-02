package zPhys;

import java.util.*;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class Unit2D {
    public Vector2 force;
    public Position2D position;
    public double entropyFactor;
    public HashMap<Unit2D,Constraint> linked = new HashMap<>();
    double friction = 0.0000099;
    double delta = 0.00016;
    double gravity = 0.0000000400;
    double bounce = 0.5;

    public boolean isPinned = false;

    public Unit2D(int x, int y, double entropyFactor) {
        position = new Position2D(x, y);
        this.entropyFactor = entropyFactor;
        this.force = new Vector2(0.0f,0.0f);
    }


    public Unit2D(double x, double y) {
        position = new Position2D(x, y);
        this.force = new Vector2(0.0f,0.0f);
    }

    public Unit2D(int x, int y) {
        position = new Position2D(x, y);
        force = new Vector2(0.0f,0.0f);
    }

    public Unit2D AddForce(Vector2 vec){
        force.Add(vec);
        return this;
    }

    public Unit2D AddXY(Vector2 vec){
       if(!isPinned)position.Add(vec);
        return this;
    }
    public Unit2D SubXY(Vector2 vec){
        if(!isPinned)position.Sub(vec);
        return this;
    }

    public Unit2D update( Vector2 bound){
        if(isPinned)return this;
        AddForce(new Vector2(0, gravity));
        double nx = position.getX() + (position.getX() - position.prevpos.x) * friction + force.x * delta;
        double ny = position.getY() + (position.getY() - position.prevpos.y) * friction + force.y * delta;
        if (nx >= bound.x) {
            nx = bound.x + (bound.x - position.prevpos.x) * bounce;
        } else if (nx <= 0) {
            nx *= -1 * bounce;
        }

        if (ny >= bound.y) {
            ny = bound.y + (bound.y - position.prevpos.y) * bounce;
        } else if (ny <= 0) {
            ny *= -1 * bounce;
        }

        position.SetXY(nx,ny);
        force = Vector2.ZERO;
        return this;
    }

    public Constraint attach(Unit2D unit){
        if(unit==null)return null;
        if (linked.containsKey(unit)) return linked.get(unit);
        linked.put(unit,new Constraint(this,unit));
        return linked.put(unit, new Constraint(this,unit));
    }

    public Unit2D free(Unit2D unit){
        linked.remove(unit);
        return this;
    }



    public static Unit2D calcPriorityUnit(Position2D point, Unit2D unit1, Unit2D unit2){
        if(unit1==null)return unit2;
        if(unit2==null)return unit1;
        double r1,r2;
        r1 = unit1.getSqrDistanceTo(point);
        r2 = unit2.getSqrDistanceTo(point);
        if(r1>r2)return unit2;
        return unit1;

    }



    public double getDistanceTo(Position2D point){
        return position.getDistanceTo(point);
    }

    public double getSqrDistanceTo(Position2D point){
        return position.getSqrDistanceTo(point);
    }


    public void MouseClick(Position2D pos) {

        position.SetXY(pos.getX(),pos.getY());

    }
}
