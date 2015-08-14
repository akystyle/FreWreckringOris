package akyDroid.gameStudio.frewreckringoris;

import akyDroid.gameFramework.Image;
import android.graphics.Rect;

public class Tile {

	int tileX,tileY,speedX;
	public int type;
	public Image tileImage;
	
	Rect r;
	
	public Tile(int x,int y,int typeInt){
		tileX = x*40;
		tileY = y*40;
		
		type = typeInt;
		
		r = new Rect();
		
		if(type == 5){
			tileImage = Assets.myTiledirt;
		}  else if (type == 8) {
			tileImage = Assets.myTilegrassTop;
		} else if(type == 4) {
			tileImage = Assets.myTilegrassLeft;
		} else if(type  == 6) {
			tileImage = Assets.myTilegrassRight;
		} else if(type == 2) {
			tileImage = Assets.myTilegrassBot;
		} else {
			type = 0;
		}
	}
	
	@SuppressWarnings("static-access")
	public void update(){
		speedX = GameScreen.getBg1().getSpeedX() * 5;
		tileX += speedX;
		r.set(tileX,tileY,tileX+40,tileY+40);
		
		if(Rect.intersects(r, GameScreen.getPlayer().rect) && type != 0){
			checkVerticalCollision(GameScreen.getPlayer().rect1,GameScreen.getPlayer().rect2);
			checkSideCollision(GameScreen.getPlayer().rect3,GameScreen.getPlayer().rect4,GameScreen.getPlayer().footLeft,GameScreen.getPlayer().footRight);
		}
	}
	
	public void checkVerticalCollision(Rect rTop,Rect rBot){
		if(Rect.intersects(rTop, r)){
			
		}
		if(Rect.intersects(rBot, r) && type == 8){
			GameScreen.getPlayer().setJumped(false);
			GameScreen.getPlayer().setSpeedY(0);
			GameScreen.getPlayer().setCenterY(tileY - 63);
		}
	}

	public void checkSideCollision(Rect rLeft, Rect rRight,Rect leftFoot,Rect rightFoot){
		if(type!=5 && type!=2 && type!=0){
			if(Rect.intersects(rLeft, r)){
				GameScreen.getPlayer().setCenterX(tileX + 102);
				GameScreen.getPlayer().setSpeedX(0);
			} else if (Rect.intersects(leftFoot, r)){
				GameScreen.getPlayer().setCenterX(tileX + 85);
				GameScreen.getPlayer().setSpeedX(0);
			}
			if(Rect.intersects(rRight, r)){
				GameScreen.getPlayer().setCenterX(tileX - 62);
				GameScreen.getPlayer().setSpeedX(0);
			} else if (Rect.intersects(rightFoot, r)){
				GameScreen.getPlayer().setCenterX(tileX - 45);
				GameScreen.getPlayer().setSpeedX(0);
			}
		}
	}
	
	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public Rect getR() {
		return r;
	}

	public void setR(Rect r) {
		this.r = r;
	}
	
	
}
