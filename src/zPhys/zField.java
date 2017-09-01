package zPhys;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class zField implements Runnable {
    public zCell[][] field;
    HashSet<zCell> touchedCells;
    private int x,y;
    public zField(int x,int y) {
        field = new zCell[x][y];
        this.x = x;
        this.y = y;
        touchedCells = new HashSet<>();
        for(;y>=0;--y)        for(;x>=0;--x)   {    zCell cell = put(x,y);

            Unit2D unit = new Unit2D(x, y );
                    if(y == 0) unit.isPinned = true;
                    if(x != 0) unit.attach(getUnitsInCell(x-1,y).stream().findFirst().orElse(null));
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
        touchedCells.stream().forEach(cell->{
            int oldCount = cell.inner.size();
            ArrayList<Unit2D> units =  cell.update();
            if(units.size()==oldCount) touchedCells.remove(cell);
            for (Unit2D unit:units
                 ) {
                zCell nCell = get(unit.position.getXi(),unit.position.getYi());
                nCell.addUnit(unit);
                touchedCells.add(nCell);
            }
        });
        for (Constraint link: Constraint.AllConstraints        ) {
            link.resolve();
        }


        return this;
    }


    @Override
    public void run() {

    }
}
