package io.github.isoteriktech.xgdx.animation.test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.GameObject;
import io.github.isoteriktech.xgdx.Scene;
import io.github.isoteriktech.xgdx.animation.FrameAnimation;
import io.github.isoteriktech.xgdx.utils.SpriteUtils;

public class FrameAnimationTest extends Scene {
    public FrameAnimationTest(){
        Array<TextureRegion> frames = SpriteUtils.getSpriteSequence("sprites/wingMan", ".png",
                1, 5, 1);
        FrameAnimation frameAnimation = new FrameAnimation(frames, .25f);

        GameObject wingMan = newSpriteObject(frames.first());
        wingMan.addComponent(frameAnimation);
        addGameObject(wingMan);

        wingMan.transform.setPosition((gameWorldUnits.getWorldWidth() - wingMan.transform.getWidth())/2f,
                (gameWorldUnits.getWorldHeight() - wingMan.transform.getHeight())/2f);
    }
}
