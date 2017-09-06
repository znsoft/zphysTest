package zPhys;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class zCell {
    public int x,y;
    public boolean isFreeze;
    public HashSet<Unit2D> inner;

    public zCell(int x,int y){
        inner = new HashSet<>();
        this.x = x;
        this.y = y;
    }

    public void addUnit(Unit2D unit){
        inner.add(unit);
    }

    public void removeUnit(Unit2D unit){
        inner.remove(unit);
    }

    public ArrayList <Unit2D> update(Vector2 bound){

        ArrayList <Unit2D> Removed = new ArrayList<>();
        if(isFreeze)return Removed;//.addAll(inner.);
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
            //if(unit.isPinned)continue;
            double dist = unit.getDistanceTo(pos);
            if(dist<min)                minUnit = unit;
        }
        if(minUnit!=null){
            zField.taked = minUnit;
            minUnit.MouseClick(pos);minUnit.isPinned = false;}
    }

}
