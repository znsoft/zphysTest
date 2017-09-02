package zPhys;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class zCell {
    public int x,y;
    public HashSet<Unit2D> inner;
    public zCell(int x,int y){
        inner = new HashSet<>();
        this.x = x;
        this.y = y;
    }

    public void addUnit(Unit2D unit){
        for (Unit2D u:inner) {
            //u.attach(unit);
        }
        inner.add(unit);

    }

    public void removeUnit(Unit2D unit){
        inner.remove(unit);
    }

    public ArrayList <Unit2D> update(Vector2 bound){

        ArrayList <Unit2D> Removed = new ArrayList<>();
        for (Unit2D unit: inner ) {
            unit.update(bound);
            if(unit.position.getXi()!=x||unit.position.getYi()!=y){Removed.add(unit);continue;}

        }
       return  Removed;

    }

    public void MouseClick(Position2D pos) {
        double min = Double.MAX_VALUE;
        Unit2D minUnit = null;
        for (Unit2D unit:inner             ) {
            if(unit.isPinned)continue;
            double dist = unit.getDistanceTo(pos);
            if(dist<min)                minUnit = unit;
        }
        if(minUnit!=null)minUnit.MouseClick(pos);
    }
/*    public zCell AddForce(Vector2 vec){
        unit.AddForce(vec);
        return this;
    }

    public zCell Move(){
        unit.Move();
        return this;
    }


    public static zCell calcPriorityCell(int x, int y, zCell cell1, zCell cell2){
        if(cell1==null)return cell2;
        if(cell2==null)return cell1;
        Position2D currentPoint = new Position2D(x,y);
        double r1,r2;
        r1 = cell1.getSqrDistanceTo(currentPoint);
        r2 = cell2.getSqrDistanceTo(currentPoint);

        if(r1>r2)return cell2;
        return cell1;

    }

    public zCell Collider(zCell cell){


      return this;
    }

    public double getDistanceTo(Position2D point){
        return unit.position.getDistanceTo(point);
    }

    public double getSqrDistanceTo(Position2D point){
        return unit.position.getSqrDistanceTo(point);
    }
    */
}
