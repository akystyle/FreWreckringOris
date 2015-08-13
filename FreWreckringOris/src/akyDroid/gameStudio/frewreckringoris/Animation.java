package akyDroid.gameStudio.frewreckringoris;

import java.util.ArrayList;

import akyDroid.gameFramework.Image;

public class Animation {

	private ArrayList<AnimFrame> frames;
	private int currentFrame;
	private long myAnimTime;
	private long totalDuration;
	
	public Animation(){
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0;
		
		synchronized (this) {
			myAnimTime = 0;
			currentFrame = 0;
		}
	}
	
	public synchronized void addFrame(Image myImage,long duration){
		totalDuration += duration;
		frames.add(new AnimFrame(myImage,totalDuration));
	}
	
	public synchronized void update(long elapsedTime){
		if(frames.size() > 1){
			myAnimTime += elapsedTime;
			if(myAnimTime >= totalDuration){
				myAnimTime = myAnimTime % totalDuration;
				currentFrame =0;
			}
		}
		while(myAnimTime > getFrame(currentFrame).endTime){
			currentFrame++;
		}
	}
	
	public synchronized Image getImage(){
		if(frames.size()==0){
			return null;
		}else{
			return getFrame(currentFrame).image;
		}
	}
	
	private AnimFrame getFrame(int frame){
		return (AnimFrame) frames.get(frame);
	}
}

class AnimFrame{
	
	Image image;
	long endTime;
	
	public AnimFrame(Image image,long endTime){
		this.image = image;
		this.endTime = endTime;
	}
}