package akyDroid.gameStudio.frewreckringoris;

import akyDroid.gameFramework.Image;
import akyDroid.gameFramework.Music;
import akyDroid.gameFramework.Sound;

public class Assets {

	public static Image myMenu,mySplash,myBackground,myCharacter,myCharacter2,myCharacter3,myHeliboy,myHeliboy2,myHeliboy3,myHeliboy4,myHeliboy5;
	public static Image myTiledirt, myTilegrassTop, myTilegrassBot, myTilegrassLeft, myTilegrassRight, myCharacterJump, myCharacterDown, myButton;
	public static Sound myClickSound;
	public static Music myThemeMusic;
	
	public static void load(Game myGame){
		myThemeMusic = myGame.getAudio().createMusic("menutheme.mp3");
		myThemeMusic.setLooping(true);
		myThemeMusic.setVolume(0.85f);
		myThemeMusic.play();
	}
}
