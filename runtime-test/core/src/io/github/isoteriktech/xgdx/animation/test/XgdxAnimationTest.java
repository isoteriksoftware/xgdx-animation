package io.github.isoteriktech.xgdx.animation.test;

import com.badlogic.gdx.graphics.Texture;
import io.github.isoteriktech.xgdx.Scene;
import io.github.isoteriktech.xgdx.XGdxGame;

public class XgdxAnimationTest extends XGdxGame {
	@Override
	protected Scene initGame() {
		xGdx.assets.enqueueFolderContents("sprites", Texture.class);
		xGdx.assets.loadAssetsNow();

		return new AnimatorTest();
	}
}