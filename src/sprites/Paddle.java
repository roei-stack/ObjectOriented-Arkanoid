package sprites;
import animations.AnimationRunner;
import animations.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collusion.Collidable;
import collusion.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;

/**
 * DESCRIPTION AND CREATOR:
 * The paddle object is the player in our game.
 * It's made from a Block, but it behaves differently
 * It can move left or right, unlike a static block
 * And like a block, it's drawn to the surface but it has it's own collusion mechanism.
 * @author Roei Cohen
 * ID 325714152
 */
public class Paddle implements Sprite, Collidable {

    private final biuoop.KeyboardSensor keyboard;
    // player width should be divisible by 5 for a smoother experience
    private final int playerWidth; //85
    private final int playerHeight = 15; //15
    private final int playerSpeed; //7
    private final java.util.List<Ball> balls = new ArrayList<>();
    private final Block player;
    //cooldown the paddle from moving to let any colliding ball collide with the paddle properly
    private int cooldown = 0;

    /**
     * Constructs a player given it's speed and width for more flexibility.
     * @param width player's width
     * @param speed player's speed
     * @param keyboard a reference to the keyboard
     */
    public Paddle(int width, int speed, KeyboardSensor keyboard) {
        this.playerWidth = width;
        this.playerSpeed = speed;
        this.keyboard = keyboard;
        Color playerColor = Color.RED;
        player = new Block(new Rectangle(new Point((AnimationRunner.WIDTH - playerWidth) / 2,
                AnimationRunner.HEIGHT - playerHeight), playerWidth, playerHeight), playerColor);
    }

    /**
     * adds a ball to the ball list
     * now the paddle is aware of the ball.
     * @param b the ball
     */
    public void addBall(Ball b) {
        this.balls.add(b);
    }

    /**
     * removes a ball
     * now the paddle is aware of the ball.
     * @param b the ball
     */
    public void removeBall(Ball b) {
        this.balls.remove(b);
    }

    /** moves the paddle a predefined amount of units to the left. */
    public void moveLeft() {
        int destination = (int) Math.max(this.getCollisionRectangle().getUpperLeft().getX() - playerSpeed,
                animations.GameLevel.WALLS_THICKNESS);
        this.getCollisionRectangle().getUpperLeft().setX(destination);
    }

    /** moves the paddle a predefined amount of units to the right. */
    public void moveRight() {
        int destination = (int) Math.min(this.getCollisionRectangle().getUpperLeft().getX() + playerSpeed,
                AnimationRunner.WIDTH - GameLevel.WALLS_THICKNESS - playerWidth);
        this.getCollisionRectangle().getUpperLeft().setX(destination);
    }

    // Implementing the sprite interface...
    /** Checking for keyboard inputs. */
    public void timePassed() {
        if (cooldown > 0) {
            cooldown--;
            return;
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            Rectangle rct = new Rectangle(
                    new Point(Math.max(getCollisionRectangle().getUpperLeft().getX() - playerSpeed, 0),
                    getCollisionRectangle().getUpperLeft().getY()), getCollisionRectangle().getUpperLeft().getX()
                    + playerWidth - Math.max(getCollisionRectangle().getUpperLeft().getX() - playerSpeed, 0),
                    playerHeight);
            for (Ball b : balls) {
                if (rct.includes(b.getCenter())) {
                    b.setCenter(new Point(rct.getUpperLeft().getX() - 2,
                            AnimationRunner.HEIGHT - 0.5 * playerHeight)); //-wall width
                    b.getCenter().setX(b.getCenter().getX()); // max (wall width, that thing)
                    b.getVelocity().setDx(-b.getVelocity().getDx());
                    b.setCenter(b.getVelocity().applyToPoint(b.getCenter()));
                    cooldown += 3;
                }
            }
            if (cooldown == 0) {
                moveLeft();
            }
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {

            Rectangle rct = new Rectangle(getCollisionRectangle().getUpperLeft(),
                    Math.min(AnimationRunner.WIDTH, getCollisionRectangle().getUpperLeft().getX() //width: -wall width
                            + playerSpeed + playerWidth) - getCollisionRectangle().getUpperLeft().getX(), playerHeight);
            for (Ball b : balls) {
                if (rct.includes(b.getCenter())) {
                    b.setCenter(new Point(rct.getUpperLeft().getX() + rct.getWidth()
                            + 2, AnimationRunner.HEIGHT - 0.5 * playerHeight)); // - wall width
                    b.getCenter().setX(Math.min(AnimationRunner.WIDTH, b.getCenter().getX())); // -wall thickness
                    b.getVelocity().setDx(-b.getVelocity().getDx());
                    b.setCenter(b.getVelocity().applyToPoint(b.getCenter()));
                    cooldown += 3;
                }
            }
            if (cooldown == 0) {
                moveRight();
            }
        }
    }

    /** @param d draw the paddle on d. */
    public void drawOn(DrawSurface d) {
        this.player.drawOn(d);
    }

    // Implementing Collidable...
    /** @return the paddle's shape (a rectangle). */
    public Rectangle getCollisionRectangle() {
        return this.player.getCollisionRectangle();
    }

    /**
     * Implementing the collusion mechanism.
     * @param hitter the hitting ball
     * @param collisionPoint the collusion point
     * @param currentVelocity the velocity
     * @return the expected velocity after the hit
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Point ul = this.getCollisionRectangle().getUpperLeft();
        int spaceBetweenRegions = playerWidth / 5;

        // Reminder: the even indexes of this array store the horizontal lines of the rectangle
        java.util.List<Line> lines = this.player.getCollisionRectangle().getLines();
        //removing the bottom line to avoid unnecessary calculations
        lines.remove(2);

        // If no intersection found, return the same velocity
        if (!collisionPoint.inList(lines)) {
            return currentVelocity;
        }

        // checking if the intersection is on the top (Gams.WALL_WIDTH)
        if (Math.abs(collisionPoint.getY() - AnimationRunner.HEIGHT + playerHeight) < Math.pow(10, -10)) {
            // region 1
            if (collisionPoint.getX() <= ul.getX() + spaceBetweenRegions) {
                return Velocity.fromAngleAndSpeed(300, currentVelocity.absoluteSpeed());
            }
            // region 2
            if (collisionPoint.getX() <= ul.getX() + 2 * spaceBetweenRegions) {
                return Velocity.fromAngleAndSpeed(330, currentVelocity.absoluteSpeed());
            }
            // region 3
            if (collisionPoint.getX() <= ul.getX() + 3 * spaceBetweenRegions) {
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            }
            // region 4
            if (collisionPoint.getX() <= 4 * spaceBetweenRegions) {
                return Velocity.fromAngleAndSpeed(30, currentVelocity.absoluteSpeed());
            }
            // region 5
            return Velocity.fromAngleAndSpeed(60, currentVelocity.absoluteSpeed());
        }
        // colliding with left wall or right wall, changing dx
        return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
    }

    /** @param gameLevel adds the paddle to the gameLevel. */
    public void addToGame(GameLevel gameLevel) {
     gameLevel.addCollidable(this);
     gameLevel.addSprite(this);
    }

    /** @return a string representing this paddle (mainly for debugging purposes) */
    public String toString() {
        return "PLAYER INFO : " + this.player.getCollisionRectangle();
    }
}
