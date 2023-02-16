package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;

    public NaivePointSet(List<Point> p){
        points = p;
    }
    @Override
    public Point nearest(double x, double y) {
        Point givenPoint = new Point(x,y);
        Point minPoint = new Point(0,0);
        double minDistance = Double.POSITIVE_INFINITY;
        for(Point point: points){
           if(Point.distance(point,givenPoint) <= minDistance){
              minDistance = Point.distance(point,givenPoint);
              minPoint =new Point(point.getX(),point.getY());
           }

        }
    return minPoint;
    }
}
