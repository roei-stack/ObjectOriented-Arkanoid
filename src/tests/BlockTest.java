package tests;

import collusion.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;

class BlockTest {
    private static void printResult(boolean passed, int testNumber) {
        if (passed) {
            System.out.println("passed test " + testNumber);
        } else {
            System.out.println("failed test " + testNumber);
        }
    }

    private static boolean hitCheck(Block block, Ball ball, Velocity velocity) {
        ball.setVelocity(block.hit(ball, ball.getCenter(), ball.getVelocity()));
        return velocity.equals(ball.getVelocity());
    }

    public static void main(String[] args) {
        //test 1 - ball doesn't hit block
        Ball b1 = new Ball(new Point(1, 1), 2);
        Block block1 = new Block(new Rectangle(new Point(0, 8), 3, 3));
        b1.setVelocity(0, 1);
        printResult(hitCheck(block1, b1, b1.getVelocity()), 1);

        //test 2 - ball hits block from down, changes dy
        Ball b2 = new Ball(new Point(4, 4), 1);
        Block block2 = new Block(new Rectangle(new Point(4, 6), 1, 1));
        b2.setVelocity(1, 1);
        printResult(hitCheck(block2, b2, new Velocity(1, -1)), 2);

        //test 3 - ball hits block from left, changes dx
        Ball b3 = new Ball(new Point(5, 5), 2);
        Block block3 = new Block(new Rectangle(new Point(7, 6), 1, 2));
        b3.setVelocity(1, 0);
        printResult(hitCheck(block3, b3, new Velocity(-1, 0)), 3);

        //test 4 - ball hits block from right, changes dx
        Ball b4 = new Ball(new Point(8, 5), 2);
        Block block4 = new Block(new Rectangle(new Point(7, 6), 1, 2));
        b4.setVelocity(-1, 0);
        printResult(hitCheck(block4, b4, new Velocity(1, 0)), 4);

        //test 5 - ball hits block from up, changes dx
        Ball b5 = new Ball(new Point(5, 10), 2);
        Block block5 = new Block(new Rectangle(new Point(5, 8), 1, 2));
        b5.setVelocity(0, -2);
        printResult(hitCheck(block5, b5, new Velocity(0, 2)), 5);

        //test 6 - ball hits block , changes dx and dy
        Ball b6 = new Ball(new Point(4, 4), 1);
        Block block6 = new Block(new Rectangle(new Point(6, 6), 1, 1));
        b6.setVelocity(2, 2);
        printResult(hitCheck(block6, b6, new Velocity(-2, -2)), 6);

    }
}