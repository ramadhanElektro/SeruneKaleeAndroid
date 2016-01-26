package com.serunekalee;

import com.serunekaleebeta.R;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
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

/**
 * Respond to the user touching the screen.
 * Change images to make things appear and disappear from the screen.
 *
 */    

public boolean onTouch (View v, MotionEvent ev) 
{
    boolean handledHere = false;

    final int action = ev.getAction();

    final int evX = (int) ev.getX();
    final int evY = (int) ev.getY();
    int nextImage = -1;			// resource id of the next image to display

    // If we cannot find the imageView, return.
    ImageView imageView = (ImageView) v.findViewById (R.id.image);
    if (imageView == null) return false;

    // When the action is Down, see if we should show the "pressed" image for the default image.
    // We do this when the default image is showing. That condition is detectable by looking at the
    // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
    Integer tagNum = (Integer) imageView.getTag ();
    int currentResource = (tagNum == null) ? R.drawable.serune : tagNum.intValue ();

    // Now that we know the current resource being displayed we can handle the DOWN and UP events.

    switch (action) {
    case MotionEvent.ACTION_DOWN :
       if (currentResource == R.drawable.serune) {
          // nextImage = R.drawable.p2_ship_pressed;
          handledHere = true;
       /*
       } else if (currentResource != R.drawable.serune) {
         nextImage = R.drawable.serune;
         handledHere = true;
       */
       } else handledHere = true;
       break;

    case MotionEvent.ACTION_UP :
       // On the UP, we do the click action.
       // The hidden image (image_areas) has three different hotspots on it.
       // The colors are red, blue, and yellow.
       // Use image_areas to determine which region the user touched.
       int touchColor = getHotspotColor (R.id.image_areas, evX, evY);

       // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
       // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
       // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
       // varying pixel density.
       ColorTool ct = new ColorTool ();
       int toleransi = 25;
       //nextImage = R.drawable.serune;
       if (ct.closeMatch (Color.RED, touchColor, toleransi)) 
       {
    	   soundsol();
       }
       else if (ct.closeMatch (Color.MAGENTA, touchColor, toleransi))
       {
    	   sounddo();
       }
       else if (ct.closeMatch (Color.BLUE, touchColor, toleransi))
       {
    	   soundre();
       }
       else if (ct.closeMatch (Color.GRAY, touchColor, toleransi))
       {
    	   soundmi();
       }
       else if (ct.closeMatch (Color.GREEN, touchColor, toleransi))
       {
    	   soundfa();
       }
       else if (ct.closeMatch (Color.CYAN, touchColor, toleransi))
       {
    	   soundla();
       }
       else if (ct.closeMatch (Color.YELLOW, touchColor, toleransi)) 
       {
    	   soundsi();
       }
       else if (ct.closeMatch (Color.DKGRAY, touchColor, toleransi)) 
       {
    	   sounddoo();
       }
       else if (ct.closeMatch (Color.WHITE, touchColor, toleransi)) 
       {
    	   toast ("Tekan Pada Lubang Nada Serune Kalee.");
       }

       
       // If the next image is the same as the last image, go back to the default.
       // toast ("Current image: " + currentResource + " next: " + nextImage);
       if (currentResource == nextImage) {
          nextImage = R.drawable.serune;
       } 
       handledHere = true; 
       break;

    default:
       handledHere = false;
    } // end switch

    if (handledHere) {
 
       if (nextImage > 0) {
          imageView.setImageResource (nextImage);
          imageView.setTag (nextImage);
       }
    }
    return handledHere;
}



/**
 * Resume the activity.
 */

@Override protected void onResume() {
    super.onResume();

    // View v  = findViewById (R.id.wglxy_bar);
    //if (v != null) {
       //Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
       //anim1.setAnimationListener (new StartActivityAfterAnimation (i));
       //v.startAnimation (anim1);
   // }
}

/**
 * Handle a click on the Wglxy views at the bottom.
 *
 */    

public void onClickWglxy (View v) {
    Intent viewIntent = new Intent ("android.intent.action.VIEW", 
                                    Uri.parse("http://double-star.appspot.com/blahti/ds-download.html"));
    startActivity(viewIntent);
    
}


/**
 */
// More methods

/**
 * Get the color from the hotspot image at point x-y.
 * 
 */

public int getHotspotColor (int hotspotId, int x, int y) {
    ImageView img = (ImageView) findViewById (hotspotId);
    if (img == null) {
       Log.d ("ImageAreasActivity", "Hot spot image not found");
       return 0;
    } else {
      img.setDrawingCacheEnabled(true); 
      Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
      if (hotspots == null) {
         Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
         return 0;
      } else {
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
      }
    }
}

/**
 * Show a string on the screen via Toast.
 * 
 * @param msg String
 * @return void
 */

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


} // end class