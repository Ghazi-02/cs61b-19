package bearmaps;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class KDTree implements PointSet {

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        Node achieved = nearest(root, goal,root);
        return achieved.point;
    }
    private Node nearest(Node n, Point goal, Node best){
        double goalY = goal.getY();
        double goalX = goal.getX();
        Node goodSide;
        Node badSide;
        if(n == null){
            return best;
        }
        if (distance(n.point.getY(), goalY) < distance(best.point.getY(),goalY)){
            best = n;
        }
        if(n.height % 2 == 0) {
            if (goalY < n.point.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
            best = nearest(goodSide, goal, best);
            if (goodSide != null && distance(badSide.point.getY(), goalY) < distance(goodSide.point.getY(), goalY)) {
                best = nearest(badSide, goal, best);
            }
        }else{
            if (goalX < n.point.getX()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                badSide = n.right;
                goodSide = n.left;
            }
            best = nearest(goodSide, goal, best);
            if (goodSide != null && distance(badSide.point.getX(), goalX) < distance(goodSide.point.getX(), goalX)) {
                best = nearest(badSide, goal, best);
            }
        }


        return best;

    }
    private double distance(double y1, double y2){
        return (y2 - y1)*(y2 - y1);
    }

    private class Node implements Comparable<Point> {
        private Node left;
        private Node right;
        private Point point;
        private int height = 1;

        private Node(Point p) {
            point = p;
        }

        @Override
        public int compareTo(Point o) {
            Point p = this.point;
            if (this.height % 2 == 0) {
                if (p.getY() < o.getY()) {
                    return 1;
                } else if (p.getY() > o.getY()) {
                    return -1;
                }
                return 1;// break arbitrarily
            } else {
                if (p.getX() < o.getX()) {
                    return 1;
                } else if (p.getX() > o.getX()) {
                    return -1;
                }
                return 1;// break arbitrarily
            }
        }
    }

    private Node root;

    public KDTree(List<Point> points) {

        for (Point p : points) {
            put(p);
        }
    }


    private void put(Point p) {
        root = putHelper(root, p , 1);
    }

    private Node putHelper(Node t, Point p, int height) {
        if (t == null) {
            Node newNode = new Node(p);
            newNode.height = height;
            return newNode;
        }
        int cmp = t.compareTo(p);
        if (cmp > 0) {
            t.right = putHelper(t.right, p , height + 1);
        } else if (cmp < 0) {
            t.left = putHelper(t.left, p, height + 1);
        } else {
            t.point = p;

        }
        return t;
    }




}

