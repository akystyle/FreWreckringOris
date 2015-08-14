package akyDroid.gameStudio.frewreckringoris;

import android.graphics.Rect;

public class Projectile {

	final int speedX = 7;
	final int speedY = 0;
	int x,y;
	boolean visible;
	Rect r;
	
	public Projectile(int startX, int startY){
		x = startX;
		y = startY;
		visible = true;
		
		r = new Rect(0,0,0,0);
	}
	
	public void update(){
		x += speedX;
		r.set(x,y,x+10,y+5);
		if( x > 800){
			visible = false;
			r = null;
		}
		if(x < 800){
			checkCollision();
		}
	}
	
	private void checkCollision(){
		if(Rect.intersects(r, GameScreen.heliBoy.r)){
			visible = false;
			
			if(GameScreen.heliBoy.health > 0){
				GameScreen.heliBoy.health -= 1;
			}
			if(GameScreen.heliBoy.health == 0){
				GameScreen.heliBoy.setCenterX(-100);
			}
		}
		if(Rect.intersects(r, GameScreen.heliBoy2.r)){
			visible = false;
			
			if(GameScreen.heliBoy2.health > 0){
				GameScreen.heliBoy2.health -= 1;
			}
			if(GameScreen.heliBoy2.health == 0){
				GameScreen.heliBoy2.setCenterX(-100);
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Rect getR() {
		return r;
	}

	public void setR(Rect r) {
		this.r = r;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}
}
