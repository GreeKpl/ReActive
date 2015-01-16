package pl.edu.agh.inz.reactive.games;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteriaFactory;
import pl.edu.agh.inz.reactive.games.summary.dialog.PreparationDialog;

public abstract class GameActivity extends Activity {

    protected FinishCriteriaFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean withTimer = getIntent().getExtras().getBoolean("withTimer");

        factory = new FinishCriteriaFactory(withTimer);

        createGameLogic();

        showLevelSelection();
    }

    public void showLevelSelection() {
        setContentView(R.layout.activity_level_selection);

        GridView levelList = (GridView) this.findViewById(R.id.levelList);

        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getLogic().getLevelsArray());
        levelList.setAdapter(adapter);

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int levelId = (int) id;
                PreparationDialog dialog = new PreparationDialog() {
                    @Override
                    protected void runLevel() {
                        startLevel(levelId);
                    }
                };
                dialog.setParams(GameActivity.this.getLogic().getLevelDescription(levelId)).startNextLevel();
                dialog.show(GameActivity.this.getFragmentManager(), "przygotuj sie");
            }
        });
    }

    public abstract void startLevel(int levelId);

    public abstract AbstractGame getLogic();

    public abstract void createGameLogic();

    protected Point getScreenSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return  size;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getLogic() != null) { // logic can be null if destroyed when being on level selection page
            getLogic().destroy();
        }
    }
}
