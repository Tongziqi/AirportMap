package cn.Nino.crim.airportmap.app.Point;

/**
 * Created by Administrator on 2015/3/31 0031.
 */
public class Point {
    private double pointX;
    private double pointY;
    private double pointZ;
    private String mTittle;

    public Point(String name, double x, double y, double z) {
        mTittle = name;
        pointX = x;
        pointY = y;
        pointZ = z;
    }

    public Point() {
        mTittle = "test";
        pointX = 1;
        pointY = 1;
        pointZ = 0;
    }


    public double getPointX() {
        return pointX;
    }

    public void setPointX(double pointX) {
        this.pointX = pointX;
    }

    public double getPointY() {
        return pointY;
    }

    public void setPointY(double pointY) {
        this.pointY = pointY;
    }

    public double getPointZ() {
        return pointZ;
    }

    public void setPointZ(double pointZ) {
        this.pointZ = pointZ;
    }

    public String getmTittle() {
        return mTittle;
    }

    public void setmTittle(String mTittle) {
        this.mTittle = mTittle;
    }

    @Override
    public String toString() {
        return mTittle;
    }
}
