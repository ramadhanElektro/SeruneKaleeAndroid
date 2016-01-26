package com.serunekalee;

import com.serunekaleebeta.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SeruneActivity extends ActionBarActivity
	implements View.OnTouchListener 
{

/**
 * Create the view for the activity.
 *
 */
	// inisialisasi
	MediaPlayer mp;
	Button btnDemo,btnStop;

	@Override public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.activity_serune);
	
	    ImageView iv = (ImageView) findViewById (R.id.image);
	    if (iv != null) {
	       iv.setOnTouchListener (this);
	    }
	
	    toast ("Tekan Pada Lubang Nada Serune Kalee.");
	    
	    btnDemo=(Button)findViewById(R.id.btnDemo);
		btnDemo.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View arg0) {
					sounddemo();
						}
						});
		
		//button btnStop
		btnStop=(Button)findViewById(R.id.btnStop);
		btnStop.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View arg0) {
				stopsound();
			}
		}); 
	}


	// proses ketika ditekan pada
	public boolean onTouch (View v, MotionEvent ev) 
	{
	    boolean handledHere = false;
	
	    final int action = ev.getAction();
	
	    final int evX = (int) ev.getX();
	    final int evY = (int) ev.getY();
	    
	    
	    switch (action) {
	    case MotionEvent.ACTION_DOWN :
	       handledHere = true;
	       break;
	
	    case MotionEvent.ACTION_UP :
	      int touchColor = getHotspotColor (R.id.warna, evX, evY);
	
	      int toleransi = 25;
	       
	       
	       if (cekWarna(Color.MAGENTA, touchColor, toleransi)) 
	       {
	    	   sounddo();
	       }
	       else if (cekWarna (Color.BLUE, touchColor, toleransi))
	       {
	    	   soundre();
	       }
	       else if (cekWarna (Color.GRAY, touchColor, toleransi))
	       {
	    	   soundmi();
	       }
	       else if (cekWarna (Color.GREEN, touchColor, toleransi))
	       {
	    	   soundfa();
	       }
	       else if (cekWarna (Color.RED, touchColor, toleransi))
	       {
	    	   soundsol();
	       }
	       else if (cekWarna (Color.CYAN, touchColor, toleransi))
	       {
	    	   soundla();
	       }
	       else if (cekWarna (Color.YELLOW, touchColor, toleransi)) 
	       {
	    	   soundsi();
	       }
	       else if (cekWarna (Color.DKGRAY, touchColor, toleransi)) 
	       {
	    	   sounddoo();
	       }
	       else if (cekWarna (Color.WHITE, touchColor, toleransi)) 
	       {
	    	   toast ("Tekan Pada Lubang Nada Serune Kalee.");
	       }
	
	       
	       handledHere = true; 
	       break;
	
	    default:
	       handledHere = false;
	    } // end switch
	
	    
	    return handledHere;
	}



/**
 * Resume the activity.
 */

	@Override protected void onResume() {
	    super.onResume();
	}


	public int getHotspotColor (int hotspotId, int x, int y) {
	    ImageView img = (ImageView) findViewById (hotspotId);
	    if (img == null) {
	       return 0;
	    } else {
	      img.setDrawingCacheEnabled(true); 
	      Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
	      if (hotspots == null) {
	         return 0;
	      } else {
	        img.setDrawingCacheEnabled(false);
	        return hotspots.getPixel(x, y);
	      }
	    }
	}

	// tampilkan pesan toast
	public void toast (String msg)
	{
	    Toast.makeText (getApplicationContext(), msg, Toast.LENGTH_LONG).show ();
	} // end toast


	// SERUNE AUDIO
	protected void sounddo(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
			mp.prepare();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.do1);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}
	
	protected void soundre(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.re2b);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}
	
	protected void soundmi(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
			mp.prepare();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.mi3b);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}
	
	protected void soundfa(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.fa4b);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}
	
	protected void soundsol(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
			mp.prepare();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.so5);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}
	
	protected void soundla(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.la6);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}
	
	protected void soundsi(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
			mp.prepare();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.si7b);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}

	protected void sounddoo(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
			mp.prepare();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.do8);
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}

	protected void sounddemo(){
		try{
		if(mp.isPlaying()){
			mp.pause();
			mp.release();
			mp.prepare();
		}
		}catch(Exception e){
	
		}
		//setup sound
		mp=MediaPlayer.create(this, R.raw.tarek);
		
	
		//agar media tidak looping
		mp.setLooping(false);
	
		//memainkan suara
		mp.start();
	}

	//method untuk menghentikan suara
	protected void stopsound(){
		//menghentikan suara
		
		mp.stop();
		
	}


	//method untuk menghentikan suara ketika tombol back ditekan
	@Override
	public void onBackPressed() {
		//stopsound();
		finish();
	}
	
	
	// passing ke RGB
	public boolean cekWarna (int warna, int touchColor, int toleransi){
		if ((int) Math.abs (Color.red (warna) - Color.red (touchColor)) > toleransi ) return false;
	    if ((int) Math.abs (Color.green (warna) - Color.green (touchColor)) > toleransi ) return false;
	    if ((int) Math.abs (Color.blue (warna) - Color.blue (touchColor)) > toleransi ) return false;
	 return true;   
	}
	
} // end class