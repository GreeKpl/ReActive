package pl.edu.agh.inz.reactive.games;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import pl.edu.agh.inz.reactive.R;
import pl.edu.agh.inz.reactive.games.finish.criteria.FinishCriteriaFactory;

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

        ListView levelList = (ListView) this.findViewById(R.id.levelList);

        ListAdapter adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, getLogic().getLevelsArray());
        levelList.setAdapter(adapter);

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("pos " + position + " id " + id);
                startLevel((int)id);
            }
        });
    }

    public abstract void startLevel(int levelId);

    public abstract AbstractGame getLogic();

    public abstract void createGameLogic();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getLogic() != null) { // logic can be null if destroyed when being on level selection page
            getLogic().destroy();
        }
    }
}
