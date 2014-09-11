package pl.edu.agh.inz.reactive;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.devsmart.android.ui.HorizontialListView;

import java.util.Arrays;
import java.util.List;

import pl.edu.agh.inz.reactive.games.GameActivity;
import pl.edu.agh.inz.reactive.games.rainbow.RainbowActivity;
import pl.edu.agh.inz.reactive.games.ThreeActivity;

public class MainMenuActivity extends Activity implements OnClickListener {

    Context ctx;
    RadioGroup radioGroup;
    String labelUser;
    EditText etPassword;

    private static final int TILES_HEIGHT = 300;

    private void showGraph(View view) {
        /*LineGraph line = new LineGraph();
        Intent intent = line.getIntent(this);
        startActivity(intent);
        */
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if (getIntent().getStringExtra("login") != null) {
            labelUser = getIntent().getStringExtra("login") + " ("
                    + getIntent().getStringExtra("name") + " "
                    + getIntent().getStringExtra("surname") + ")";
        } else {
            labelUser = "Gość";
        }

        final HorizontialListView gamesView = (HorizontialListView) this.findViewById(R.id.gamesList);

        ImageView seaGame    = createGameImage(RainbowActivity.class, R.drawable.gra1);
        ImageView three1Game = createGameImage(ThreeActivity.class, R.drawable.gra2);
        ImageView three2Game = createGameImage(ThreeActivity.class, R.drawable.gra2);
        ImageView three3Game = createGameImage(ThreeActivity.class, R.drawable.gra2);
        ImageView three4Game = createGameImage(ThreeActivity.class, R.drawable.gra2);
        ImageView three5Game = createGameImage(ThreeActivity.class, R.drawable.gra2);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        List<ImageView> games = Arrays.asList(seaGame, three1Game, three2Game, three3Game, three4Game, three5Game);

        ImageArrayAdapter adapter = new ImageArrayAdapter(this, android.R.layout.simple_list_item_1, games);

        gamesView.setAdapter(adapter);

        gamesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Class<? extends GameActivity> game = (Class<? extends GameActivity>) gamesView.getAdapter().getItem(position);

                Intent intent = new Intent(MainMenuActivity.this, RainbowActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });


        View showGraph = this.findViewById(R.id.btnSeeResults);
        showGraph.setOnClickListener(this);
        View logout = this.findViewById(R.id.btnAdminPanel);
        logout.setOnClickListener(this);
        TextView labelTextView = (TextView) this.findViewById(R.id.tvLoginUser);
        labelTextView.setText(labelUser);
    }

    private ImageView createGameImage(Class<? extends GameActivity> gameClass, int imgSrc) {
        ImageView game = new ImageView(this);
        game.setImageResource(imgSrc);
        game.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));

        return game;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSeeResults:
                showGraph(v);
                break;
            case R.id.btnAdminPanel:
                passwordPopup();
                break;
        }
    }

    public void passwordPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_password, null))
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (etPassword.getText().toString().equals("admin")) {
                                    goLogin();
                                }
                            }
                        });
        AlertDialog dialog = builder.create();
        dialog.show();
        etPassword = (EditText) dialog.findViewById(R.id.etPassword);
    }

    private void goLogin() {

        Intent intent = new Intent(this, AdminActivity.class);
        finish();
        startActivity(intent);
    }
}
