package zPhys;

/**
 * Created by es.zheludkov on 30.08.2017.
 */
public class zField implements Runnable {
    public zCell[][] field;
    private int x,y;
    public zField(int x,int y) {
        field = new zCell[x][y];
        this.x = x;
        this.y = y;

        for(;y>=0;--y)        for(;x>=0;--x)           put(x,y);

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




        return this;
    }


    @Override
    public void run() {

    }
}
