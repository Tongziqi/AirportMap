package cn.Nino.crim.airportmap.app.Point;

import java.util.List;
/**
 * Created by toy on 15-7-31.
 */

public class PointEstimate {

    public static final int SAME_X = 1;
    public static final int SAME_Y = 2;
    public static final int OTHER = 3;

    private static int isXYSame(Point p1, Point p2){ //  x same = 1; y same = 2; other = 3
        if(p1.getPointX() == p2.getPointX())
            return 1;
        if(p1.getPointY() == p2.getPointY())
            return 2;
        return 3;
    }

    private static boolean isReached(Point current, Point reached, double scope_radius){
        double radius = Math.pow(current.getPointX()-reached.getPointX(), 2)
                + Math.pow(current.getPointY()-reached.getPointY(), 2);
        if(radius < Math.pow(scope_radius, 2))
            return true;
        return false;
    }

    private static boolean isInRectangle(Point p, double left, double right, double up, double down){
        if(p.getPointX() >= left && p.getPointX() <= right) {
            if (p.getPointY() >= down && p.getPointY() <= up)
                return true;
        }
        return false;
    }
    private static double getUp(Point p1, Point p2){
        if(p1.getPointY() >= p2.getPointY())
            return p1.getPointY();
        return p2.getPointY();
    }
    private static double getDown(Point p1, Point p2){
        if(p1.getPointY() < p2.getPointY())
            return p1.getPointY();
        return p2.getPointY();
    }
    private static double getLeft(Point p1, Point p2){
        if(p1.getPointX() < p2.getPointX())
            return p1.getPointX();
        return p2.getPointX();
    }

    private static double getRight(Point p1, Point p2){
        if(p1.getPointX() >= p2.getPointX())
            return p1.getPointX();
        return p2.getPointX();
    }

    private static boolean isInCircle(Point p, Point po, Point p2){
        return isReached(p,po,Math.sqrt(Math.pow(po.getPointX()-p2.getPointX(), 2)
                + Math.pow(po.getPointY()-p2.getPointY(), 2)));
    }

    private static boolean isInScope(Point p, Point po, Point p2, double scope){
        double l = getLeft(po, p2);
        double r = getRight(po, p2);
        double u = getUp(po, p2);
        double d = getDown(po, p2);
        if(isXYSame(po, p2) == SAME_X){
            l -= scope;
            r += scope;
        }else if(isXYSame(po, p2) == SAME_Y){
            u += scope;
            d -= scope;
        }else{
            return isInCircle(p, po, p2);
        }
        return isInRectangle(p, l, r, u, d);
    }

    public static boolean isOnPath(Point p, List<Point> list,int count, double scope){
        if(count < 1)
            return true;
        return isInScope(p, list.get(count), list.get(count - 1), scope);
    }

    public static boolean isOnAllPath(Point p, List<Point> list,double scope){

        for(int count = 1; count < list.size()-1; count ++){
            if(isOnPath(p, list, count, scope)){
                return true;
            }
        }
        return false;
    }

/*    public static void main(String[] args) {
        List<Point> list = new ArrayList<Point>();
        list.add(0, new Point("a", 0.5, 0.3, 1));
        list.add(1, new Point("a", 0.5, 0.3, 1));
        list.add(2, new Point("a", 0.6, 0.3, 1));
        list.add(3, new Point("a", 0.6, 0.1, 1));
        list.add(4, new Point("a", 0.7, 0.1, 1));
        PointEstimate pe = new PointEstimate();
        Point cur = new Point("sb", 0.51, 0.3, 1);
        if(pe.isOnAllPath(cur, list, 0.1))
            System.out.println("good");
        else
            System.out.println("bad");
    }*/
}
