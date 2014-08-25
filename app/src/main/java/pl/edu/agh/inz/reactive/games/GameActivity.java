package pl.edu.agh.inz.reactive.games;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.WrapperListAdapter;

import pl.edu.agh.inz.reactive.R;

public abstract class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showLevelSelection() {
        setContentView(R.layout.activity_level_selection);

        ListView levelList = (ListView) this.findViewById(R.id.levelList);

        int maxLevel = getLogic().getMaxLevel();
        Integer[] ints = new Integer[maxLevel];
        for (int i = 0; i < maxLevel; i++) {
            ints[i] = i + 1;
        }
        ListAdapter adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, ints);
        levelList.setAdapter(adapter);

        levelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
            }
        });

    }

    public abstract AbstractGame getLogic();
}
