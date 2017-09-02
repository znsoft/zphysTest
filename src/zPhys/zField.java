package zPhys;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class zField implements Runnable {
    public zCell[][] field;
    Vector2 bound;
    public HashSet<zCell> touchedCells;
    public int x,y;
    public zField(int x,int y) {
        field = new zCell[x][y];
        this.x = x;
        this.y = y;
        bound = new Vector2((double)x,(double)y);
        touchedCells = new HashSet<>();
        for(y=0;y<this.y;y++)        for(x=0;x<this.x;x++)   {
            zCell cell = put(x,y);
            if(cell == null)continue;
            Unit2D unit = new Unit2D(x, y );
                    if(y == 0 ) unit.isPinned = true;
                    if(y == this.y-1) unit.isPinned = true;
                    if(x != 0&&y>0) unit.attach(getUnitsInCell(x-1,y).stream().findFirst().orElse(null));
                    if(y != 0) unit.attach(getUnitsInCell(x,y-1).stream().findFirst().orElse(null));
            cell.addUnit(unit);
            touchedCells.add(cell);
        }



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


    /*public zCell AddCell(int x,int y, zCell cell){
        zCell c;
        c = get(x,y);
        if(c!=null)return put(x,y, zCell.calcPriorityCell(x,y,c,cell));
        if(cell == null)c = new zCell(x,y,entropyFactor); else c = cell;
        //force addiction

        put(x,y,c);
        return c;

    }*/

    public zField update(){
        ResolveConstraints();
        ResolveCells();

        return this;
    }

    public void ResolveCells() {
        ArrayList<zCell> toRemove = new ArrayList<>();
        ArrayList<Unit2D> needTouch = new ArrayList<>();
        for (zCell cell:touchedCells) {
            int oldCount = cell.inner.size();

            ArrayList<Unit2D> units =  cell.update(bound);
            if(units.size()==oldCount) toRemove.add(cell);
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
