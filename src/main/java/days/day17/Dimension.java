package days.day17;

import java.util.ArrayList;

public class Dimension {

    int xSize = 0;
    int ySize = 0;

    ArrayList<ArrayList<String>> xy = new ArrayList<>();

    public Dimension(){}
    public Dimension(int ySize, int xSize) {
        this.xSize = xSize;
        for(int y =0; y < ySize; y++) {
            addYRow(null, true);
        }
    }

    public Dimension(Dimension dimension) {
        this.xSize = dimension.xSize;
        this.ySize = dimension.ySize;
        this.xy = dimension.xy;
    }

    public void addValue(int yRow, Integer x, String value) {

        ArrayList<String> yRowList = xy.get(yRow);
        if(x != null) {
            yRowList.add(x, value);
        } else {
            yRowList.add(value);
        }
        xSize = yRowList.size();
    }

    public void updateValue(int yRow, int x, String value) {
        xy.get(yRow).set(x, value);
    }

    public void addYRow(Integer pos, boolean fill){
        ArrayList<String> yRow = new ArrayList<String>();

        if(fill) {
            for (int x = 0; x < this.xSize; x++) {
                yRow.add(".");
            }
        }

        if(pos != null) {
            xy.add(pos, yRow);
        } else {
            xy.add(yRow);

        }
        ySize = xy.size();
    }
}
