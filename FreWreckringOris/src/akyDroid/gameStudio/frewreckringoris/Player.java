package akyDroid.gameStudio.frewreckringoris;

import java.util.ArrayList;

import android.graphics.Rect;

public class Player {

	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	
	int centerX = 100;
	int centerY = 377;
	boolean jumped = false;
	boolean movingRight = false;
	boolean movingLeft = false;
	boolean ducked = false;
	boolean readyToFire = true;
	
	int speedX = 0;
	int speedY = 0;
	public static Rect rect = new Rect(0,0,0,0);
	public static Rect rect1 = new Rect(0,0,0,0);
	public static Rect rect2 = new Rect(0,0,0,0);
	public static Rect rect3 = new Rect(0,0,0,0);
	public static Rect rect4 = new Rect(0,0,0,0);
	
	public static Rect footLeft = new Rect(0,0,0,0);
	public static Rect footRight = new Rect(0,0,0,0);
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	public void update(){
		
		if(speedX < 0){
			centerX += speedX;
		}
		if(speedX == 0 || speedX < 0){
			GameScreen.getBg1().setSpeedX(0);
			GameScreen.getBg2().setSpeedX(0);
		}
		if(centerX <=200 && speedX >0){
			centerX += speedX;
		}
		if(speedX > 0 && centerX > 200){
			GameScreen.getBg1().setSpeedX(-MOVESPEED/5);
			GameScreen.getBg2().setSpeedX(-MOVESPEED/5);
		}
		
		centerY += speedY;
		
		//Handles Jumping
		speedY += 1;
		
		if(speedY > 3){
			jumped = true;
		}
		
		if(centerX + speedX <= 60){
			centerX = 61;
		}

        rect.set(centerX - 110, centerY - 110, centerX + 70, centerY + 70);
        rect1.set(centerX - 34, centerY - 63, centerX + 34, centerY);
        rect2.set(rect1.left, rect1.top + 63, rect1.left+68, rect1.top + 128);
        rect3.set(rect1.left - 26, rect1.top+32, rect1.left, rect1.top+52);
        rect4.set(rect1.left + 68, rect1.top+32, rect1.left+94, rect1.top+52);
        footLeft.set(centerX - 50, centerY + 20, centerX, centerY + 35);
        footRight.set(centerX, centerY + 20, centerX+50, centerY+35);
	}
	
	public void moveRight(){
		if(ducked == false){
			speedX = MOVESPEED;
		}
	}
	
	public void moveLeft(){
		if(ducked == false){
			speedX = -MOVESPEED;
		}
	}

	public void stopRight(){
		setMovingRight(false);
		stop();
	}
	
	public void stopLeft(){
		setMovingLeft(false);
		stop();
	}
	
	private void stop(){
		if(isMovingRight() == false && isMovingLeft() == false){
			speedX = 0;
		}
		if(isMovingRight() == true && isMovingLeft() == false){
			moveRight();
		}
		if(isMovingRight() == false && isMovingLeft() == true){
			moveLeft();
		}
	}
	
	public void jump(){
		if(jumped == false){
			speedY = JUMPSPEED;
			jumped = true;
		}
	}
	
	public void shoot(){
		if(readyToFire){
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}
	
	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	 public ArrayList<Projectile> getProjectiles() {
	        return projectiles;
	    }
}
