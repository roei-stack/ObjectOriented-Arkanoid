package tests;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collusion.Collidable;
import collection.GameEnvironment;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;

import java.awt.*;


public class TestPart1 {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DELAY = 16;

    public static void draw(Ball ball) {

        GUI gui = new GUI("TEST PART 1", WIDTH , HEIGHT);
        Sleeper sleeper = new Sleeper();
        java.util.List<Collidable> c = ball.getEnvironment().getCollidables();
        int len = c.size();

        while (true) {

            ball.timePassed();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);

            for (int i = 4; i < len; i++) {
                Block b = (Block) c.get(i);
                b.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(DELAY);
        }


    }

    public static void main(String[] args) {

        GameEnvironment e = new GameEnvironment();
        Ball ball = new Ball(100, 170, 7, Color.BLUE);
        ball.setVelocity(3, 4);

        Block wall1 = new Block(new Rectangle(new Point(0, 0), WIDTH, HEIGHT));
        Block wall2 = new Block(new Rectangle(new Point(0, 0), 0, HEIGHT));
        Block wall3 = new Block(new Rectangle(new Point(WIDTH, 0), 0, HEIGHT));
        Block wall4 = new Block(new Rectangle(new Point(0, HEIGHT), WIDTH, 0));

        Block block1 = new Block(new Rectangle(new Point(90, 60), 100, 100));
        Block block2 = new Block(new Rectangle(new Point(120, 220), 120, 35));
        Block block3 = new Block(new Rectangle(new Point(230, 105), 50, 90));
        Block block4 = new Block(new Rectangle(new Point(345, 150), 80, 40));
        Block block5 = new Block(new Rectangle(new Point(100, 100), 70, 30));
        Block block6 = new Block(new Rectangle(new Point(170, 100), 70, 30));
        Block block7 = new Block(new Rectangle(new Point(170, 130), 70, 30));
        Block block8 = new Block(new Rectangle(new Point(225, 130), 90, 10));

        e.addCollidable(wall1);
        e.addCollidable(wall2);
        e.addCollidable(wall3);
        e.addCollidable(wall4);

        e.addCollidable(block1);
        e.addCollidable(block2);
    //    e.addCollidable(block3);
        e.addCollidable(block4);
   //     e.addCollidable(block5);
   //     e.addCollidable(block6);
    //    e.addCollidable(block7);
        e.addCollidable(block8);

        ball.setGameEnvironment(e);

        draw(ball);

    }
}
