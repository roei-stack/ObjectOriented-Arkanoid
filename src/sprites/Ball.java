package sprites;
import animations.GameLevel;
import biuoop.DrawSurface;
import collusion.Collidable;
import collusion.CollisionInfo;
import collusion.Velocity;
import collection.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.List;

/**
 * DESCRIPTION AND CREATOR:
 * The following class defines the object "Ball" with these fields:.
 * (Point) => the center point (x,y)
 * (int) => the Circle's radius
 * (java.awt.color) => the ball's color
 * (velocity) => the ball's velocity (dx, dy)
 * (int) the screen's edges
 * Possible operations:
 * Basic set, get, construct and toString methods
 * Drawing the circle on a given draw surface
 * Generates an array of balls with center, a color and a velocity
 * Moves the ball's center point one move ahead, according to the velocity
 * Holds data regarding the current game environment, and changes the balls velocity accordingly
 * @author Roei Cohen
 * ID 325714152
 */
public class Ball implements Sprite {

    // Private fields, velocity is initialized to be 0
    private Point center;
    private int radius;
    private java.awt.Color color = Color.BLACK;
    private Velocity v = new Velocity(0, 0);
    private GameEnvironment environment;

    /**
     * Constructor - creates a ball given it's center and radius.
     * @param center the center of the circle
     * @param r the radius
     */
    public Ball(Point center, int r) {
        this.center = center;
        this.radius = r;
    }

    /**
     * This constructor allows to predefine all elements of the ball.
     * @param center the center point
     * @param radius the size
     * @param color the color we draw the ball on the screen
     * @param velocity it's velocity
     * @param environment the list of collidable objects
     */
    public Ball(Point center, int radius, java.awt.Color color, Velocity velocity, GameEnvironment environment) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.v = velocity;
        this.environment = environment;
    }

    /**
     * Constructor - creates a ball given it's center, radius and color.
     * @param center the center of the circle
     * @param r      the radius
     * @param color  the color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Constructor - creates a ball given it's center, radius and color.
     * @param x the x coordinate of the center of the circle
     * @param y the y coordinate of the center of the circle
     * @param r the radius
     * @param color the color
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**@return the ball's center point*/
    public Point getCenter() {
        return this.center;
    }

    /** @return the radius of the circle */
    public int getSize() {
        return this.radius;
    }

    /** @return the ball's velocity */
    public Velocity getVelocity() {
        return this.v;
    }

    /** @return the game environment */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Changes the center of the circle.
     * @param p the new value
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * Changes the radius of the circle.
     * @param rad the new value
     */
    public void setRadius(int rad) {
        this.radius = rad;
    }

    /**@param e the new game environment */
    public void setGameEnvironment(GameEnvironment e) {
        this.environment = e;
    }

    /**@param c the collidable (paddle/block) being removed)*/
    public void removeFromGameEnvironment(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Changes the ball's velocity.
     * @param dx the x-axis change rate
     * @param dy the y-axis change rate
     */
    public void setVelocity(double dx, double dy) {
        this.v.setDx(dx);
        this.v.setDy(dy);
    }

    /**
     * Changes the ball's velocity.
     * @param val the new velocity
     */
    public void setVelocity(Velocity val) {
        this.v = val;
    }

    /**
     * Draws the circle on a given surface.
     * @param surface the given drawing surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /** Moves the ball one step according to it's velocity and the game state. */
    public void timePassed() {
       Line trajectory = new Line(this.center, this.v.applyToPoint(this.center));
       CollisionInfo collusion = this.environment.getClosestCollision(trajectory);
       if (collusion == null) {
           this.center = trajectory.end();
       } else {
           Point c = collusion.collisionPoint();
           this.setCenter(new Point(c.getX() - this.v.getDx(), c.getY() - this.v.getDy()));
           this.v = collusion.collisionObject().hit(this, collusion.collisionPoint(), this.v);
       }
    }

    /** @param gameLevel the gameLevel were adding this ball to */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.getPlayer().addBall(this);
    }

    /** @param gameLevel the gameLevel were removing this ball from */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.getPlayer().removeBall(this);
    }

    /**
     * Generates balls on top of the player, and gives it a predefined velocity directed up.
     * @param gameLevel the gameLevel we will assign the balls to
     * @param amount the amount of balls wished to be added
     */
    public static void createBallsOnPaddle(GameLevel gameLevel, int amount) {
        List<Velocity> velocities = gameLevel.getBallsVelocities();
        List<Color> colors = gameLevel.getBallsColors();
        // the player (paddle object) is the first collidable added.
        Rectangle r = gameLevel.getPlayer().getCollisionRectangle();
        for (int i = 1; i <= amount; i++) {
            Ball b = new Ball(new Point(r.getUpperLeft().getX() + ((i * r.getWidth()) / (amount + 1)),
                    r.getUpperLeft().getY() - 7), 6, colors.get(i - 1), velocities.get(i - 1),
                    gameLevel.getEnvironment());
            b.addToGame(gameLevel);
        }
    }

    /**
     * Prints the ball object.
     * Overrides the original toString method from object.java
     * @return String that represents the object ball
     */
    public String toString() {
        return "Center = " + this.center + ", Radius = " + this.radius;
    }
}
