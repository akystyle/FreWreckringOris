package akyDroid.gameStudio.frewreckringoris;

import android.graphics.Rect;

public class Enemy {

	private int myPower,centerX,speedX,centerY;
	
	public Rect r = new Rect(0,0,0,0);
	public int health = 5;
	
	private int movementSpeed;
	
	@SuppressWarnings("static-access")
	public void update(){
		follow();
		centerX += speedX;
		speedX = GameScreen.getBg1().getSpeedX() * 5 + movementSpeed;
		r.set(centerX - 25, centerY - 25,centerX +25, centerY + 35);
		
		if(Rect.intersects(r, GameScreen.getPlayer().rect)){
			checkCollision();
		}
	}
	
	@SuppressWarnings("static-access")
	private void checkCollision(){
		if(Rect.intersects(r, GameScreen.getPlayer().rect1) || Rect.intersects(r, GameScreen.getPlayer().rect2) || Rect.intersects(r, GameScreen.getPlayer().rect3) || Rect.intersects(r, GameScreen.getPlayer().rect4)){
			
		}
	}
	
	public void follow(){
		if(centerX < -95 || centerX > 810){
			movementSpeed = 0;
		} else if(Math.abs(GameScreen.getPlayer().getCenterX() - centerX) < 5){
			movementSpeed = 0;
		} else {
			if(GameScreen.getPlayer().getCenterX() >= centerX){
				movementSpeed = 1;	
			} else {
				movementSpeed = -1;
			}
		}
	}
	
	public void die(){
		
	}
	
	public void attack(){
		
	}
	
	public int getMyPower() {
		return myPower;
	}

	public void setMyPower(int myPower) {
		this.myPower = myPower;
	}

	public int getCenterX() {
		return centerX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
}
