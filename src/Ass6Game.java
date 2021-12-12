import animations.AnimationRunner;
import animations.GameFlow;

import levels.LevelInformation;
import levels.DirectHitLevel;
import levels.BallsRushLevel;
import levels.StepUpLevel;
import levels.FinalBossFightLevel;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Roei Cohen
 * ID 325714152
 * The main class runs the game according to user specifications via the command line.
 */
public class Ass6Game {

    /**
     * Tries to add the level from it's number. if number is from 1 to 4, add the matching level.
     * @param levels the list
     * @param levelNumber the level number.
     */
    private static void tryToAdd(List<LevelInformation> levels, int levelNumber) {
        if (levelNumber == 1) {
            levels.add(new DirectHitLevel());
        }
        if (levelNumber == 2) {
            levels.add(new BallsRushLevel());
        }
        if (levelNumber == 3) {
            levels.add(new StepUpLevel());
        }
        if (levelNumber == 4) {
            levels.add(new FinalBossFightLevel());
        }
    }

    /**
     * Runs the game with levels specified by the user.
     * @param args command line arguments represent the levels the player would like to play
     * valid level arguments are integers 1,2,3,4 as there are 4 levels. other arguments will be ignored.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new LinkedList<>();
        // the game will be run in 60 fps
        GameFlow game = new GameFlow(new AnimationRunner(60));
        // When run with additional arguments, the arguments should be treated as a list of level numbers to run.
        for (String s : args) {
            // for every string in arguments, check if it represents a valid level number
            // checking if s is a number
            int levelNumber;
            try {
                levelNumber = Integer.parseInt(s);
            } catch (Exception e) {
                continue;
            }
            // if s is a number in range 1-4, add a new level
            tryToAdd(levels, levelNumber);
        }
        if (levels.size() == 0) {
            levels.add(new DirectHitLevel());
            levels.add(new BallsRushLevel());
            levels.add(new StepUpLevel());
            levels.add(new FinalBossFightLevel());
        }
        game.runLevels(levels);
    }
}
