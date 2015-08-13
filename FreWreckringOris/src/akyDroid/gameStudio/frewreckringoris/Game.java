package akyDroid.gameStudio.frewreckringoris;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import akyDroid.gameFramework.Screen;
import akyDroid.gameFramework.implementation.GameMain;
import android.util.Log;

public class Game extends GameMain{

	public static String map;
	boolean firstTimeCreate = true;
	
	@Override
	public Screen getInitScreen() {
		if(firstTimeCreate){
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		InputStream in = getResources().openRawResource(R.raw.map1);
		map = convertStreamToString(in);
		
		return new LoadingScreen(this);
	}
	
	private static String convertStreamToString(InputStream in) {
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try{
			while((line = br.readLine())!= null){
				sb.append((line + "\n"));
			}
		} catch(IOException e){
			Log.w("LOG", e.getMessage());
		}finally{
			try{
				in.close();
			}catch(IOException e){
				Log.w("LOG", e.getMessage());	
			}
		}
		return sb.toString();
	}

	@Override
	public void onBackPressed() {
	getCurrentScreen().backButton();
	}
	
	@Override
	public void onResume(){
		super.onResume(); 
		Assets.myThemeMusic.play();
	}
	
	@Override
	public void onPause(){
		super.onPause(); 
		Assets.myThemeMusic.pause();
	}
}
