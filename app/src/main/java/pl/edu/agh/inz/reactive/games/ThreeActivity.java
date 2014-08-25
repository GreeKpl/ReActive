package pl.edu.agh.inz.reactive.games;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public abstract class ThreeActivity extends GameActivity /* implements OnClickListener,*/ {
/*
	ImageView imgPattern, imgEx1, imgEx2, imgEx3, imgAnswer;
	Random rand = new Random();
	int i, size;
	int level = 1;
	int score = 0;
	Set<Integer> images;

	int tabSetImages[] = new int[4];
	int[] tabImage = { R.drawable.kw3, R.drawable.kw4, R.drawable.kw5,
			R.drawable.kw6, R.drawable.kw7, R.drawable.kw8, R.drawable.kw9,
			R.drawable.kw10 };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_three);

		imgPattern = (ImageView) this.findViewById(R.id.imgvPattern);
		imgEx1 = (ImageView) this.findViewById(R.id.imgvExample1);
		imgEx2 = (ImageView) this.findViewById(R.id.imgvExample2);
		imgEx3 = (ImageView) this.findViewById(R.id.imgvExample3);
		imgAnswer = (ImageView) this.findViewById(R.id.imgvAnswer);

		imgEx1.setOnClickListener(this);
		imgEx2.setOnClickListener(this);
		imgEx3.setOnClickListener(this);

		randImages();
		levelNotification(level++, 0);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.imgvExample1:
			if (compareImg(1)) {
				correctAnswer();
			} else {
				incorrectAnswer();
			}
			
			randImages();
			//imgAnswer.setVisibility(View.INVISIBLE);
			break;
		case R.id.imgvExample2:
			if (compareImg(2)) {
				correctAnswer();
			} else {
				incorrectAnswer();
			}
							
			randImages();
			//imgAnswer.setVisibility(View.INVISIBLE);
			
			break;
		case R.id.imgvExample3:
			if (compareImg(3)) {
				correctAnswer();
			} else {
				incorrectAnswer();
			}
			randImages();
			//imgAnswer.setVisibility(View.INVISIBLE);
			
			break;
		}
	}

	private void correctAnswer() 	{
		System.out.println("Dobrze!");
		imgAnswer.setImageResource(R.drawable.greentick);
		imgAnswer.setVisibility(View.VISIBLE);

		score += 1;
		if (score % 10 == 0) {
			levelNotification(level++, score);
		}
	}

	private void incorrectAnswer() {
		System.out.println("Źle!");
		imgAnswer.setImageResource(R.drawable.redcross);
		imgAnswer.setVisibility(View.VISIBLE);
		
	}

//	private void myDelay(final int delay) {
//		Thread thread = new Thread() {
//			public void run() {
//				try {
//					sleep(delay);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				} finally {
//					System.out.println("dupa");
//				}
//			}
//		};
//		thread.start();
//	}

	private void randImages() {

		images = new HashSet<Integer>();
		for (int it = 0; it < tabImage.length; it++) {
			images.add(it);
		}
		size = tabImage.length;

		i = rand.nextInt(size);
		images.remove(i);
		tabSetImages[0] = tabImage[i];
		imgEx1.setImageResource(tabSetImages[0]);

		do {
			i = rand.nextInt(size);
		} while (!images.contains(i));
		images.remove(i);
		tabSetImages[1] = tabImage[i];
		imgEx2.setImageResource(tabSetImages[1]);

		do {
			i = rand.nextInt(size);
		} while (!images.contains(i));
		images.remove(i);
		tabSetImages[2] = tabImage[i];
		imgEx3.setImageResource(tabSetImages[2]);

		i = rand.nextInt(3);
		tabSetImages[3] = tabSetImages[i];
		imgPattern.setImageResource(tabSetImages[3]);
	}

	public boolean compareImg(int imgNo) {
		return tabSetImages[imgNo - 1] == tabSetImages[3];
	}

	public void levelNotification(int levelNo, int score) {
		Toast toast = Toast.makeText(this, "Runda " + levelNo + "\nWynik: "
				+ score, Toast.LENGTH_SHORT);
		MediaPlayer mp = MediaPlayer.create(this, R.raw.round);
		toast.show();
		mp.start();
	}
*/
}
