package tests;


import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

public class TestRectangle {
    public static void main(String[] args) {

        System.out.println("Test running...");
        int failCount = 0;

        Point p = new Point(0, 4);
        Rectangle a = new Rectangle(p, 10, 4);
        Line l1 = new Line(7, -3, 12, 2);
        java.util.List<Point> list = a.intersectionPoints(l1);

        Point solution1 = new Point(10, 0);

        if (list.size() == 1 && list.get(0).equals(solution1) && l1.closestIntersectionToStartOfLine(a).equals(solution1)) {
            System.out.println("Passed first test");
        } else {
            System.out.println("FAILED first test");
            failCount++;
        }

        //second test
        list.clear();
        Line l2 = new Line(3, 5, 7, -3);
        Point sol1 = new Point(3.5, 4);
        Point sol2 = new Point(5.5, 0);
        list = a.intersectionPoints(l2);

        if (list.size() == 2 && ((list.get(0).equals(sol1) && list.get(1).equals(sol2))
                || (list.get(0).equals(sol2) && list.get(1).equals(sol1)))
                && l2.closestIntersectionToStartOfLine(a).equals(new Point(3.5, 4))) {
            System.out.println("Passed second test");
        } else {
            System.out.println("FAILED second test");
            failCount++;
        }

        //third test
        list.clear();
        Line l3 = new Line(2, 1, 7, 2);
        list = a.intersectionPoints(l3);

        if (list.size() == 0 && l3.closestIntersectionToStartOfLine(a) == null) {
            System.out.println("Passed third test");
        }
        else {
            System.out.println("FAILED third test");
            failCount++;
        }

        if (failCount == 0) {
            System.out.println("PASSED ALL TESTS!");
        }
        else {
            System.out.println(failCount + " Test(s) failed");
        }

        /*
        Line ll = new Line(0, 4, 10, 4);
        System.out.println(ll.intersectionWith(l2) + "\n" + l2.intersectionWith(ll))
        */

    }
}
