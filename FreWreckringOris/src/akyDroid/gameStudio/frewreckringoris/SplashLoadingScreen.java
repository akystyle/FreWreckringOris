package akyDroid.gameStudio.frewreckringoris;

import akyDroid.gameFramework.Game;
import akyDroid.gameFramework.Graphics;
import akyDroid.gameFramework.Graphics.ImageFormat;
import akyDroid.gameFramework.Screen;

public class SplashLoadingScreen extends Screen {

	public SplashLoadingScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime){
		Graphics myGraphics = game.getGraphics();
		Assets.mySplash = myGraphics.newImage("splash.jpg", ImageFormat.RGB565);
		
		game.setScreen(new LoadingScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}
	
}
