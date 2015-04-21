package cn.Nino.crim.airportmap.app.Point;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/4/13 0013.
 */
public class DefaultPointB1 {
    public DefaultPointB1 defaultPointB1 = null;
    public ArrayList<Point> B1_vertex = new ArrayList<Point>();

    public DefaultPointB1() {
        defaultPointB1 = this;
    }


    public ArrayList<Point> initialPointB1() {
        B1_vertex.add(new Point("ES1", 0.11, 0.1, 0.0));
        B1_vertex.add(new Point("EL1", 0.17, 0.05, 0.0));
        B1_vertex.add(new Point("D1", 0.27, 0.05, 0.0));
        B1_vertex.add(new Point("D2", 0.5, 0.05, 0.0));
        B1_vertex.add(new Point("D3", 0.73, 0.05, 0.0));
        B1_vertex.add(new Point("EL2", 0.83, 0.05, 0.0));
        B1_vertex.add(new Point("ES2", 0.87, 0.1, 0.0));
        B1_vertex.add(new Point("EL3", 0.26, 0.37, 0.0));
        B1_vertex.add(new Point("ES3", 0.31, 0.3, 0.0));
        B1_vertex.add(new Point("ES4", 0.61, 0.3, 0.0));
        B1_vertex.add(new Point("EL4", 0.72, 0.37, 0.0));
        B1_vertex.add(new Point("ES1EL1", 0.17, 0.1, 0.0));
        B1_vertex.add(new Point("ES1D1", 0.26, 0.1, 0.0));
        B1_vertex.add(new Point("ES1D2", 0.5, 0.1, 0.0));
        B1_vertex.add(new Point("ES1D3", 0.73, 0.1, 0.0));
        B1_vertex.add(new Point("ES1EL2", 0.83, 0.1, 0.0));
        B1_vertex.add(new Point("ES3EL3", 0.27, 0.3, 0.0));
        B1_vertex.add(new Point("ES3D2", 0.5, 0.3, 0.0));
        B1_vertex.add(new Point("ES4EL4", 0.61, 0.3, 0.0));
        return B1_vertex;
    }
}
