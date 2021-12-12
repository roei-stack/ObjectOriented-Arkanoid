package tests;

import animations.*;
import collection.SpriteCollection;

public class TestStoppable {
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(60);
        Animation a1 = new EndScreenAnimation(100, true);
        Animation a2 = new PauseScreen(new SpriteCollection()); // also an Animation
      //  Animation a1k = new KeyPressStoppableAnimation(runner.keyboard(), "m", a1);
      //  Animation a2k = new KeyPressStoppableAnimation(runner.keyboard(), "m", a2);
      //  runner.run(a1k);
      //  runner.run(a2k);
       // runner.kill();
    }
}
