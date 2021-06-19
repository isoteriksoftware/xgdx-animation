package io.github.isoteriktech.xgdx.animation.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import io.github.isoteriktech.xgdx.GameObject;
import io.github.isoteriktech.xgdx.Scene;
import io.github.isoteriktech.xgdx.animation.Animator;
import io.github.isoteriktech.xgdx.animation.FrameAnimation;
import io.github.isoteriktech.xgdx.animation.conditions.BooleanCondition;
import io.github.isoteriktech.xgdx.animation.conditions.CompoundCondition;
import io.github.isoteriktech.xgdx.asset.GameAssetsLoader;
import io.github.isoteriktech.xgdx.input.KeyCodes;
import io.github.isoteriktech.xgdx.input.KeyTrigger;

public class AnimatorTest extends Scene {
    public AnimatorTest() {
        setBackgroundColor(Color.BLACK);

        GameAssetsLoader assets = xGdx.assets;

        FrameAnimation bunnyStand = new FrameAnimation(new Array<>(new TextureRegion[]{
                assets.regionForTexture("sprites/bunny_stand.png", true)}), 1f);
        FrameAnimation bunnyJump = new FrameAnimation(new Array<>(new TextureRegion[]{
                assets.regionForTexture("sprites/bunny_jump.png", true)}), 1f);
        FrameAnimation bunnyWalk = new FrameAnimation(new Array<>(new TextureRegion[]{
                assets.regionForTexture("sprites/bunny_walk1.png", true),
                assets.regionForTexture("sprites/bunny_walk2.png", true)}), .25f);

        Animator<FrameAnimation> animator = new Animator<>(bunnyStand, bunnyJump, bunnyWalk);

        GameObject bunny = newSpriteObject(bunnyStand.getCurrentFrame());
        bunny.addComponent(animator);
        addGameObject(bunny);

        BooleanCondition standCondition = new BooleanCondition(true);
        BooleanCondition jumpCondition = new BooleanCondition(false);
        CompoundCondition<Boolean> walkCondition = new CompoundCondition<>(true)
                .not(standCondition).not(jumpCondition);

        animator
            .addTransition(bunnyJump, bunnyStand, standCondition)
            .addTransition(bunnyStand, bunnyWalk, true, walkCondition)
            .addTransition(bunnyJump, jumpCondition);

        input.addKeyListener(KeyTrigger.keyDownTrigger(KeyCodes.UP).setPolled(true), (name, evt) -> {
            jumpCondition.dataSource.set(true);
            standCondition.dataSource.set(false);
        });
        input.addKeyListener(KeyTrigger.keyUpTrigger(KeyCodes.UP), (name, evt) -> {
            jumpCondition.dataSource.set(false);
            standCondition.dataSource.set(true);
        });
        input.addKeyListener(KeyTrigger.keyDownTrigger(KeyCodes.RIGHT).setPolled(true), (name, evt) -> {
            standCondition.dataSource.set(false);
        });
        input.addKeyListener(KeyTrigger.keyUpTrigger(KeyCodes.RIGHT), (name, evt) -> {
            standCondition.dataSource.set(true);
        });
    }
}









