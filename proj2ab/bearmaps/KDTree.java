package bearmaps;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class KDTree implements PointSet {

    @Override
    public Point nearest(double x, double y) {
        return null;
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
                return 1;
            } else {
                if (p.getX() < o.getX()) {
                    return 1;
                } else if (p.getX() > o.getX()) {
                    return -1;
                }
                return 1;
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

