package pl.edu.agh.inz.reactive.games;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SeaActivity extends GameActivity {
	/*
	ImageView object;
	RelativeLayout layout;
	RelativeLayout.LayoutParams params;
	
	Random rand = new Random();
	Random rand2 = new Random();
	int x = 100;
	int y = 100;	
	int wys = 570;
	int szer = 1070;
	
	int level = 1;
	int score = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sea);
		
		object = new ImageView(this);
		object.setImageResource(R.drawable.lodka);
		object.setClickable(true);
		
		object.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				x = rand.nextInt(szer);
				y = rand2.nextInt(250)+320;
				clickAction(v,x,y);
			}
		});	
		x = rand.nextInt(szer);
		y = rand2.nextInt(250)+320;
		params = new RelativeLayout.LayoutParams(150, 150);
		layout = (RelativeLayout)findViewById(R.id.seaLayout);
		params.setMargins(x, y, 200, 200);        
		object.setLayoutParams(params);		
		layout.addView(object);
		
		levelNotification(level++, 0);

	}
	
	public void levelNotification(int levelNo, int score) {
		Toast toast = Toast.makeText(this, "Runda "+levelNo+"\nWynik: "+score, Toast.LENGTH_SHORT);
		MediaPlayer mp = MediaPlayer.create(this, R.raw.round);
		toast.show();
		mp.start();		
	}
	
	public void clickAction(View view, int x, int y) {		
		
		params.setMargins(x,y,10,10);      
		object.setLayoutParams(params);
		layout.removeAllViewsInLayout();
		layout.addView(object);
		score += 1;
		if (score%10 == 0) {
			levelNotification(level++, score);
		}		
	}
    */
}
