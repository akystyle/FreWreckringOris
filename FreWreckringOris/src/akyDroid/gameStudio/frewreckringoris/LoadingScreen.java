package akyDroid.gameStudio.frewreckringoris;

import akyDroid.gameFramework.*;
import akyDroid.gameFramework.Game;
import akyDroid.gameFramework.Graphics.ImageFormat;

public class LoadingScreen extends Screen{

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics myGraphics = game.getGraphics();
		Assets.myMenu = myGraphics.newImage("menu.jpg", ImageFormat.RGB565);
		Assets.myBackground = myGraphics.newImage("background.jpg", ImageFormat.RGB565);
		Assets.myCharacter = myGraphics.newImage("character.png", ImageFormat.ARGB4444);
		Assets.myCharacter2 = myGraphics.newImage("character2.png", ImageFormat.ARGB4444);
		Assets.myCharacter3 = myGraphics.newImage("character3.png", ImageFormat.ARGB4444);
		Assets.myCharacterJump = myGraphics.newImage("jumped.png", ImageFormat.ARGB4444);
		Assets.myCharacterDown = myGraphics.newImage("down.png", ImageFormat.ARGB4444);
		
		Assets.myHeliboy = myGraphics.newImage("heliboy.png", ImageFormat.ARGB4444);
		Assets.myHeliboy2 = myGraphics.newImage("heliboy2.png", ImageFormat.ARGB4444);
		Assets.myHeliboy3 = myGraphics.newImage("heliboy3.png", ImageFormat.ARGB4444);
		Assets.myHeliboy4 = myGraphics.newImage("heliboy4.png", ImageFormat.ARGB4444);
		Assets.myHeliboy5 = myGraphics.newImage("heliboy5.png", ImageFormat.ARGB4444);
		
		Assets.myTiledirt = myGraphics.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.myTilegrassTop = myGraphics.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.myTilegrassBot = myGraphics.newImage("tilegrassbot.png", ImageFormat.RGB565);
		Assets.myTilegrassLeft = myGraphics.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.myTilegrassRight = myGraphics.newImage("tilegrassright.png", ImageFormat.RGB565);
		
		Assets.myButton = myGraphics.newImage("button.png", ImageFormat.RGB565);
		
		
		Assets.myClickSound = game.getAudio().createSound("click.ogg");
		
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		Graphics myGraphics = game.getGraphics();
		myGraphics.drawImage(Assets.mySplash, 0, 0);
		
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
