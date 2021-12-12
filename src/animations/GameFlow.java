package animations;
import biuoop.KeyboardSensor;
import geometry.Counter;
import levels.LevelInformation;
import java.util.List;

/**
 * @author Roei Cohen
 * ID 325714152
 * "GmaeFlow" is responsible for making a smooth transition between the diffrent levels
 */
public class GameFlow {

    // the keyboard may be accessed by runner.keyboard()
    private final AnimationRunner runner;
    private final Counter score;

    /**
     * @param ar the given animation runner gives us access to run all levels on the same gui.
     */
    public GameFlow(AnimationRunner ar) {
        this.runner = ar;
        this.score = new Counter(0);
    }

    /**
     * @param levels runs all levels in the list.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean passedAllLevels = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner, this.score);
            level.initialize();
            while (level.blocksLeft() > 0 && level.ballsLeft() > 0) {
                level.run();
            }
            // if we lost all balls, we quit the game
            if (level.ballsLeft() == 0) {
                passedAllLevels = false;
                break;
            }
        }
        // displaying the end screen and closing the gui after game is finished
        this.runner.run(new KeyPressStoppableAnimation(this.runner.keyboard(), KeyboardSensor.SPACE_KEY,
                new EndScreenAnimation(this.score.getValue(), passedAllLevels)));
        this.runner.kill();
    }
}
