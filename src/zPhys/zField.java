package zPhys;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class zField implements Runnable {
    public zCell[][] field;
    public static Unit2D taked;
    Vector2 bound;
    public HashSet<zCell> touchedCells;
    public int x,y;
    public zField(int x,int y) {
        taked = null;
        field = new zCell[x][y];
        this.x = x;
        this.y = y;
        bound = new Vector2((double)x,(double)y);
        touchedCells = new HashSet<>();
        for(y=0;y<this.y;y++)
            for(x=0;x<this.x;x++)
                put(x,y);


        int s = 1;
        for(y=0;y<this.y;y+=s)        for(x=0;x<this.x;x+=s)   {
            zCell cell = put(x,y);
            if(cell == null)continue;
            Unit2D unit = new Unit2D(x, y );
            if(y == 0 && (x+3)%6==0 ) unit.isPinned = true;
           // if(y == this.y-1) unit.isPinned = true;
            //if(x != 0) unit.attach(getUnitsInCell(x - s, y).stream().findFirst().orElse(null));
            //if(y != 0) {

                unit.attach(getUnitsInCell(x - s, y).stream().findFirst().orElse(null),false);
                unit.attach(getUnitsInCell(x, y - s).stream().findFirst().orElse(null),false);
                unit.attach(getUnitsInCell(x-s, y - s).stream().findFirst().orElse(null),true);
                unit.attach(getUnitsInCell(x+s, y - s).stream().findFirst().orElse(null),true);
s=2;
            unit.attach(getUnitsInCell(x - s, y).stream().findFirst().orElse(null),false);
            unit.attach(getUnitsInCell(x, y - s).stream().findFirst().orElse(null),false);
            unit.attach(getUnitsInCell(x-s, y - s).stream().findFirst().orElse(null),true);
            unit.attach(getUnitsInCell(x+s, y - s).stream().findFirst().orElse(null),true);
s=1;

            //}
            cell.addUnit(unit);
            touchedCells.add(cell);
        }
        /*
        Unit2D luCorner = getUnitsInCell(0, 0).stream().findFirst().orElse(null);
        Unit2D ldCorner = getUnitsInCell(0, this.y-1).stream().findFirst().orElse(null);
        Unit2D ruCorner = getUnitsInCell(this.x-1, 0).stream().findFirst().orElse(null);
        Unit2D rdCorner = getUnitsInCell(this.x-1, this.y-1).stream().findFirst().orElse(null);
        rdCorner.attach(luCorner,false);
        rdCorner.attach(ruCorner,false);
        rdCorner.attach(ldCorner,false);

        ldCorner.attach(luCorner,false);
        ldCorner.attach(ruCorner,false);

        luCorner.attach(ruCorner,false);
*/

    }



    public  HashSet<Unit2D> getUnitsInCell(int x,int y){
        zCell cell = get(x,y);
        if(cell==null)return new HashSet<>();
        return cell.inner;
    }

    public zCell get(Position2D point){
        return get(point.getXi(),point.getYi());
    }

    public zCell get(int x,int y){
        if (x < 0 || x >= this.x || y < 0 || y >= this.y) return null;
        return field[x][y];
    }

    private zCell put(int x,int y, zCell cell){
        if (x < 0 || x >= this.x || y < 0 || y >= this.y) return null;
        field[x][y] = cell;
        return cell;
    }

    private zCell put(int x,int y){
        if (x < 0 || x >= this.x || y < 0 || y >= this.y) return null;
        zCell cell = new zCell(x,y);
        field[x][y] = cell;
        return cell;
    }

    public zField update(){
        ResolveCells();
        ResolveConstraints();

        return this;
    }

    public void ResolveCells() {
        ArrayList<zCell> toRemove = new ArrayList<>();
        ArrayList<Unit2D> needTouch = new ArrayList<>();
        for (zCell cell:touchedCells) {
            int oldCount = cell.inner.size();
            if(oldCount==0){ toRemove.add(cell);continue;}

            ArrayList<Unit2D> units =  cell.update(bound);
            if(units.size()==oldCount) toRemove.add(cell);
            cell.inner.removeAll(units);
            needTouch.addAll(units);
        }
        touchedCells.removeAll(toRemove);
        for (Unit2D unit:needTouch                ) {
            zCell nCell = get(unit.position.getXi(),unit.position.getYi());
            if(nCell == null)continue;
            nCell.addUnit(unit);
            touchedCells.add(nCell);
        }
    }

    public void ResolveConstraints() {
        ArrayList<Constraint> toRemove = new ArrayList<>();
        for (Constraint link:Constraint.AllConstraints             ) {
            Constraint l = link.resolve();
            if(l!=null)toRemove.add(l);
        }
        Constraint.AllConstraints.removeAll(toRemove);
    }


    @Override
    public void run() {
        while(true)this.update();
    }

    public void Click(double x, double y, boolean b) {
        Position2D pos = new Position2D(x,y);
        zCell cell = get(pos);
        if(cell==null)return;
        if(cell.inner.size()==0)return;
        cell.MouseClick(pos);
    }
}
