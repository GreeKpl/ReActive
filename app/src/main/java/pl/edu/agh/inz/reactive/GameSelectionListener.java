package pl.edu.agh.inz.reactive;

import pl.edu.agh.inz.reactive.games.Game;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class GameSelectionListener implements OnClickListener {
	
	private Class<? extends Game> game;
	private Activity mainMenu;

	public GameSelectionListener(Activity mainMenu, Class<? extends Game> game) {
		this.mainMenu = mainMenu;
		this.game = game;
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(mainMenu, game);
		mainMenu.startActivity(intent);
	}

}
