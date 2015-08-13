package akyDroid.gameStudio.frewreckringoris;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



//import akyDroid.gameFramework.Game;
import akyDroid.gameFramework.Graphics;
import akyDroid.gameFramework.Image;
import akyDroid.gameFramework.Input.TouchEvent;
import akyDroid.gameFramework.Screen;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.Animation;

public class GameScreen extends Screen {

	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;
	private static Background myBG1, myBG2;
	private static Player myPlayer;
	private static HeliBoy heliBoy,heliBoy2;
	private Animation myPlayerAnim, myHeliBoyAnim;
	private Image currentSprite, myCharacterImage,myCharacterImage2,myCharacterImage3,myHeliBoyImage,myHeliBoyImage2,myHeliBoyImage3,myHeliBoyImage4,myHeliBoyImage5;
	private ArrayList myTileArray = new ArrayList();
	int lifeleft = 1;
	Paint myLargePainter,mySmallPainter;


	public GameScreen(Game game) {
		super(game);

//Initialize game objects
		myBG1 = new Background(0,0);
		myBG2 = new Background(2160,0);
		myPlayer = new Player;
		heliBoy = new Heliboy(340,360);
		heliBoy2 = new Heliboy(700,360);
		
//referencing to all the loaded images
		myCharacterImage = Assets.myCharacter;
		myCharacterImage2 = Assets.myCharacter2;
		myCharacterImage3 = Assets.myCharacter3;
		myHeliBoyImage = Assets.myHeliboy;
		myHeliBoyImage2 = Assets.myHeliboy2;
		myHeliBoyImage3 = Assets.myHeliboy3;
		myHeliBoyImage4 = Assets.myHeliboy4;
		myHeliBoyImage5 = Assets.myHeliboy5;
		
//Defining Animations
		myPlayerAnim = new Animation();
		myPlayerAnim.addFrame(myCharacterImage,750);
		myPlayerAnim.addFrame(myCharacterImage2,50);
		myPlayerAnim.addFrame(myCharacterImage3,50);
		myPlayerAnim.addFrame(myCharacterImage2,50);
		myHeliBoyAnim = new Animation();
		myHeliBoyAnim.addFrame(myHeliBoyImage,100);
		myHeliBoyAnim.addFrame(myHeliBoyImage2,100);
		myHeliBoyAnim.addFrame(myHeliBoyImage3,100);
		myHeliBoyAnim.addFrame(myHeliBoyImage4,100);
		myHeliBoyAnim.addFrame(myHeliBoyImage5,100);
		
		currentSprite = myPlayerAnim.getImage();
		
//Defining Paint objects 		
		myLargePainter= new Paint();
		myLargePainter.setTextSize(100);
		myLargePainter.setTextAlign(Paint.Align.CENTER);
		myLargePainter.setAntiAlias(true);
		myLargePainter.setColor(Color.WHITE);
		
		mySmallPainter = new Paint();
		mySmallPainter.setTextSize(30);
		mySmallPainter.setTextAlign(Paint.Align.CENTER);
		mySmallPainter.setAntiAlias(true);
		mySmallPainter.setColor(Color.WHITE);

//Loading Maps
		loadMap();	
	}

	
	private void loadMap() {
		ArrayList<String> rows = new ArrayList<String>();
		int width = 0;
		int height = 0;
		
		Scanner myScanner = new Scanner(Game.map);
		while (myScanner.hasNextLine()){
			String row = myScanner.nextLine();
			if(row == null)
				break;
			if(!row.startsWith("!")){
				rows.add(row);
				width = Math.max(width, row.length());
			}
		}
		height = rows.size();
		
		for (int i =0; i < 12; i++){
			String row = rows.get(i);
			for(int j=0;j<width;j++){
				char tile = row.charAt(j);
				Tile myTile = new Tile(j,i,Character.getNumericValue(tile));
				myTileArray.add(myTile);
			}
			
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> myTouchEvent = game.getInput().getTouchEvents();

		if (state == GameState.Ready) {
			updateReady(myTouchEvent);
		} else if (state == GameState.Running) {
			updateRunning(myTouchEvent,deltaTime);
		} else if (state == GameState.Paused) {
			updatePaused(myTouchEvent);
		} else if (state == GameState.GameOver) {
			updateGameOver(myTouchEvent);
		}
	}

	private void updateGameOver(List<TouchEvent> myTouchEvent) {
		int len = myTouchEvent.size();
		for(int i =0;i<len;i++){
			TouchEvent tempEvent = myTouchEvent.get(i);
			if(tempEvent.type == TouchEvent.TOUCH_UP){
				if(inBounds(tempEvent,0,0,800,480)){
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}
	}

	private void updatePaused(List<TouchEvent> myTouchEvent) {
		int len = myTouchEvent.size();
		for (int i =0;i < len; i++){
			TouchEvent tempEvent = myTouchEvent.get(i);
			if(tempEvent.type == TouchEvent.TOUCH_UP){
				if(inBounds(tempEvent,0,0,800,240)){
					if(!inBounds(tempEvent,0,0,35,35)){
						resume();
					}
				}
				
				if(inBounds(tempEvent,0,240,800,240)){
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateRunning(List<TouchEvent> myTouchEvent,float deltaTime) {

		// 1. All touch input is handled here:
		int len = myTouchEvent.size();
		for (int i = 0; i < len; i++) {
			TouchEvent tempEvent = myTouchEvent.get(i);
			if (tempEvent.type == TouchEvent.TOUCH_DOWN) {
				if(inBounds(tempEvent,0,285,65,65)){
					myPlayer.jump();
					currentSprite = myPlayerAnim.getImage();
					myPlayer.setDucked(false);
				}else if(inBounds(tempEvent,0,350,65,65)){
					if(myPlayer.isReadyToFire()){
						myPlayer.shoot();
					}
				}else if(inBounds(tempEvent,0,415,65,65)){
					currentSprite = Assets.myCharacterDown;
					myPlayer.setDucked(true);
					myPlayer.setSpeedX(0);
				}
				
				if(tempEvent.x > 400){
					myPlayer.moveRight();
					myPlayer.setMovingRight(true);
				}				
			}
			if (tempEvent.type == TouchEvent.TOUCH_UP) {
				if(inBounds(tempEvent,0,415,65,65)){
					currentSprite = myPlayerAnim.getImage();
					myPlayer.setDucked(false);
				}else if(inBounds(tempEvent,0,0,65,65)){
					pause();
				}
				if(tempEvent.x > 400){
					//Stop Right
					myPlayer.stopRight();
				}
			}
		}
		
	       // 2. Check miscellaneous events like death:		
		if (lifeleft == 0){
			state = GameState.GameOver;
		}
		
        // 3. Call individual update() methods here.
		myPlayer.update();
		if(myPlayer.isJumped()){
			currentSprite = Assets.myCharacterJump;
		}else if(myPlayer.jumped() == false && myPlayer.isDucked() == false){
			currentSprite = myPlayerAnim.getImage();
		}
		
		ArrayList projectiles = myPlayer.getProjectiles();
		for(int i =0; i< projectiles.size();i++){
			Projectile tempProjectile = (Projectile) projectiles.get(i);
			if(tempProjectile.isVisible() == true){
				tempProjectile.update();
			} else {
				projectiles.remove(i);
			}
		}
		
		updateTiles();
		heliBoy.update();
		heliBoy2.update();
		myBG1.update();
		myBG2.update();
		animate();
		
		if(myPlayer.getCenterY() > 500){
			state = GameState.GameOver;
		}
	}

	private void updateTiles() {
		for (int i=0;i<myTileArray.size();i++){
			Tile t = myTileArray.get(i);
			t.update();
		}
		
	}


	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}
	
	private void updateReady(List<TouchEvent> myTouchEvent) {
		if (myTouchEvent.size() > 0)
			state = GameState.Running;
	}

	@Override
	public void paint(float deltaTime) {

		Graphics g = game.getGraphics();
//Drawing the Game Elements
		g.drawImage(Assets.myBackground, myBG1.getBgX(), myBG1.getBgY());
		g.drawImage(Assets.myBackground, myBG2.getBgX(), myBG2.getBgY());
		paintTiles(g);
		
		ArrayList projectiles = myPlayer.getProjectiles();
		for(int i = 0; i < projectiles.size(); i++){
			Projectiles tempProjectile = (Projectile) projectiles.get(i);
			g.drawRect(tempProjectile.getX(), tempProjectile.getY(), 10, 5, Color.GREEN);
		}
		
		g.drawImage(currentSprite, myPlayer.getCenterX() - 61, myPlayer.getCenterY() - 63);
		g.drawImage(myHeliBoyAnim.getImage(), heliBoy.getCenterX() - 48, heliBoy.getCenterY() - 48);
		g.drawImage(myHeliBoyAnim.getImage(), heliBoy2.getCenterX() - 48, heliBoy2.getCenterY() - 48);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < myTileArray.size(); i++) {
			Tile t = (Tile) myTileArray.get(i);
			if (t.type != 0) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate(){
		myPlayerAnim.update(10);
		myHeliBoyAnim.update(50);
	}
	
	private void nullify(){
		//setting everything to Null. Recreating in constructor
		
		mySmallPainter = null;
		myLargePainter = null;
		myBG1 = null;
		myBG2 = null;
		myPlayer = null;
		heliBoy = null;
		heliBoy2 = null;
		currentSprite = null;
		myCharacterImage = null;
		myCharacterImage2 = null;
		myCharacterImage3 = null;
		myHeliBoyImage = null;
		myHeliBoyImage2 = null;
		myHeliBoyImage3 = null;
		myHeliBoyImage4 = null;
		myHeliBoyImage5 = null;
		myPlayerAnim = null;
		myHeliBoyAnim = null;
		
		//Collecting Garbage
		System.gc();
		
	}

	private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER", 400,240, myLargePainter);
        g.drawString("Tap to return", 400,240, mySmallPainter);
	}

	private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, myLargePainter);
        g.drawString("Menu", 400, 360, myLargePainter);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.myButton, 0, 285, 0, 0, 65, 65);
		g.drawImage(Assets.myButton, 0, 350, 0, 65, 65, 65);
		g.drawImage(Assets.myButton, 0, 415, 0, 130, 65, 65);
		g.drawImage(Assets.myButton, 0, 0, 0, 195, 35, 35);
	}

	private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start",400,240, mySmallPainter);
		
	}

	private void GameRestart() {
		// Set all variables to null. You will be recreating them in the
        // constructor.
        mySmallPainter = null;

        // Call garbage collector to clean up memory.
        System.gc();
	}
	
	@Override
	public void pause() {
		if(state == GameState.Running)
			state = GameState.Paused;
	}

	
	@Override
	public void resume() {
		if(state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		pause();
	}
	
	public void goToMenu(){
		game.setScreen(new MainMenuScreen(game));
	}
	
	public static Background getBg1(){
		return myBG1;
	}
	
	public static Background getBg2(){
		return myBG2;
	}
	
	public static Player getPlayer(){
		return myPlayer;
	}
}