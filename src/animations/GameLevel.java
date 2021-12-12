package animations;

import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Counter;

import levels.LevelInformation;
import sprites.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;
import sprites.ScoreTrackingListener;
import sprites.StatsIndicator;

import collusion.Collidable;
import collusion.Velocity;
import collusion.HitListener;
import collusion.BlockRemover;
import collusion.BallRemover;

import collection.GameEnvironment;
import collection.SpriteCollection;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * DESCRIPTION AND CREATOR...
 * The game object is responsible for initializing an 800*600 screen and animate the ball and the player
 * bouncing off walls, blocks and the paddle.
 * @author Roei Cohen
 * ID 325714152
 */

public class GameLevel implements Animation {

    public static final int WALLS_THICKNESS = 20; //20
    // keeping track of scores, balls left and blocks left
    private final Counter remainingBalls;
    private final Counter remainingBlocks;
    private final Counter score;
    // holds data regarding all drawable and collidable objects(player, ball, blocks...), the keyboard input and the gui
    private final AnimationRunner runner;
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final List<Block> blocks;
    private final Sprite background;
    private final List<Velocity> initialBallVelocities;
    private final List<Color> initialBallColors;
    private final String levelName;
    private final Paddle player;
    private boolean running;

    /**
     * This constructor takes the level settings and adjusts the level accordingly.
     * @param settings the game settings file/class
     * @param runner the animation runner takes care of making a gui and a keyboard function
     * @param startingScore the score were entering this level with
     */
    public GameLevel(LevelInformation settings, AnimationRunner runner, Counter startingScore) {
        // if there are more than 179 balls, we cant give them unique velocities
        this.remainingBalls = new Counter(Math.min(settings.numberOfBalls(), 179));
        this.remainingBlocks = new Counter(settings.numberOfBlocksToRemove());
        this.player = new Paddle(settings.paddleWidth(), settings.paddleSpeed(), runner.keyboard());
        this.initialBallVelocities = settings.initialBallVelocities();
        this.initialBallColors = settings.initialBallColors();
        this.background = settings.getBackground();
        this.levelName = settings.levelName();
        this.blocks = settings.blocks();
        this.runner = runner;
        this.score = startingScore;
    }

    /** @param c a collidable object. */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**@param c the collidable being removed*/
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /** @param s the sprite. */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /** @return the player*/
    public Paddle getPlayer() {
        return this.player;
    }

    /** @return how many removable blocks are left. */
    public int blocksLeft() {
        return this.remainingBlocks.getValue();
    }

    /** @return how many balls are left. */
    public int ballsLeft() {
        return this.remainingBalls.getValue();
    }

    /** @return the initial balls velocities. */
    public List<Velocity> getBallsVelocities() {
        return this.initialBallVelocities;
    }

    /** @return the initial balls colors. */
    public List<Color> getBallsColors() {
        return this.initialBallColors;
    }

    /**@param s the sprite (drawable) being removed*/
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /** @return the collidables */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**Creates 3 screen borders, and a bottom wall that detects which balls leave the game. */
    private void initializeWalls() {
        int w = AnimationRunner.WIDTH, h = AnimationRunner.HEIGHT;
        Block wall1 = new Block(new Rectangle(new Point(0, 15), w, WALLS_THICKNESS), Color.GRAY); //top
        Block wall2 = new Block(new Rectangle(new Point(w - WALLS_THICKNESS, 0), WALLS_THICKNESS, //right
                h), Color.GRAY);
        Block wall3 = new Block(new Rectangle(new Point(0, 0), WALLS_THICKNESS, h),  Color.GRAY); //left
        Block deathRegion = new Block(new Rectangle(new Point(0, h), w, 0),
                Color.red);
        Sprite scoreBoard = new StatsIndicator(score, levelName);
        wall1.addToGame(this); // adding top wall
        wall2.addToGame(this); // right wall
        wall3.addToGame(this); // left wall
        deathRegion.addToGame(this); // bottom death region
        // death region will remove whatever hits it
        deathRegion.addHitListener(new BallRemover(this, remainingBalls));
        scoreBoard.addToGame(this);
    }

    /**Initializes all collidable, non corner blocks, for a cleaner code. */
    private void initializeBlocks() {
        HitListener blockRemover = new BlockRemover(this, remainingBlocks);
        HitListener scoreTracker = new ScoreTrackingListener(score);
        //todo healer and killer blocks
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracker);
        }
    }

    /** Initialize a new game: create the Blocks and Ball (and Paddle), and add them to the game. */
    public void initialize() {
        addSprite(background);
        player.addToGame(this);
        // creating the balls, with a predefined center and a random speed between 4-6. the speed's angle is pointing up
        Ball.createBallsOnPaddle(this, remainingBalls.getValue());
        //adding all 4 walls
        initializeWalls();
        //creating blocks
        initializeBlocks();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (remainingBalls.getValue() <= 0) {
            running = false;
        }
        if (remainingBlocks.getValue() <= 0) {
            score.increase(100); // 100 points rewarded for removing all blocks
            running = false;
        }
        if (runner.keyboard().isPressed("p") || runner.keyboard().isPressed("P")) {
            runner.run(new KeyPressStoppableAnimation(runner.keyboard(),
                    KeyboardSensor.SPACE_KEY, new PauseScreen(sprites)));
        }
    }

    /** Run the game -- start the animation loop. */
    public void run() {
        //this.createBallsOnTopOfPaddle()
       this.runner.run(new CountdownAnimation(2, 3, sprites)); // 3..2..1.. counter
       this.running = true;
       this.runner.run(this);
    }
}
