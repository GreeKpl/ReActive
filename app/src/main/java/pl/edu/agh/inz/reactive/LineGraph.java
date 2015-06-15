package pl.edu.agh.inz.reactive;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.WindowManager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pl.edu.agh.inz.reactive.games.GameRegistry;

public class LineGraph {

    public static final List<Integer> graphColors = Arrays.asList(
        R.color.graph_1, R.color.graph_2, R.color.graph_3, R.color.graph_4,
        R.color.graph_5, R.color.graph_6, R.color.graph_7, R.color.graph_8,
        R.color.graph_9, R.color.graph_10, R.color.graph_11, R.color.graph_12,
        R.color.graph_13, R.color.graph_14, R.color.graph_15, R.color.graph_16);

	public Intent getIntent(Context context, int screenHeight) {

        DatabaseManager db = new DatabaseManager(context);
        db.open();
        User user = db.getActiveUser();
        String labelUser = user.getLogin() + " (" + user.getName() + " " + user.getSurname() + ")";

        //wypisywanie zawartosci tabel
        db.printUsersTable();
        db.printLevelsTable();
        db.printResultsTable();
        System.out.println("Minimalna data: "+db.getMinDate(user.getLogin()));


        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

        //Rainbow
        int serialNumber = 0; // used only for defining graph color
        for (GameDescriptor game : GameRegistry.getInstance().getGames()) {
            Map<Long, Integer> map1 = db.getAchievements(user.getLogin(), game.getId());
            Iterator<Map.Entry<Long, Integer>> iterator = map1.entrySet().iterator();
            TimeSeries series1 = new TimeSeries(game.getName() + "   ");

            int length1 = map1.size();
            System.out.println("length1 = " + length1);
            long[] x1 = new long[length1];
            int[] y1 = new int[length1];
            int i = 0;
            series1.add(0, 0);
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();

                x1[i] = Long.valueOf(entry.getKey().toString());
                y1[i] = Integer.valueOf(entry.getValue().toString());
                System.out.println(game.getName() + " Indeks: " + x1[i] + " wynik: " + y1[i]);
                series1.add(x1[i], y1[i]);
                i++;
            }
            dataset.addSeries(series1);

            XYSeriesRenderer renderer = new XYSeriesRenderer();
            renderer.setColor(context.getResources().getColor(graphColors.get(serialNumber)));
            renderer.setPointStyle(PointStyle.CIRCLE);
            renderer.setFillPoints(true);
            renderer.setLineWidth(context.getResources().getInteger(R.integer.line_graph_line_width));
            mRenderer.addSeriesRenderer(renderer);

            serialNumber++;
        }

		mRenderer.setAxesColor(context.getResources().getColor(R.color.graph_axes));
		mRenderer.setXAxisMin(0);
		mRenderer.setYAxisMin(0);

        mRenderer.setAxisTitleTextSize(screenHeight/25);
        mRenderer.setLabelsColor(context.getResources().getColor(R.color.graph_axes_labels));
		mRenderer.setXTitle(context.getString(R.string.graph_training_day));
		mRenderer.setYTitle(context.getString(R.string.graph_points));

		mRenderer.setLegendTextSize(screenHeight/25);
        mRenderer.setLegendHeight(screenHeight/10);

        mRenderer.setLabelsTextSize(screenHeight/25);//liczby
        mRenderer.setYLabels(screenHeight/45);
        mRenderer.setXLabels(screenHeight/45);
        mRenderer.setXLabelsColor(context.getResources().getColor(R.color.graph_x_label));
        mRenderer.setYLabelsColor(0, context.getResources().getColor(R.color.graph_y_label));
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);
        mRenderer.setYLabelsPadding(10);
        mRenderer.setYLabelsVerticalPadding(-7);

        mRenderer.setShowGrid(true);
		mRenderer.setGridColor(context.getResources().getColor(R.color.graph_grid));

        mRenderer.setMarginsColor(context.getResources().getColor(R.color.graph_margin));
        mRenderer.setBackgroundColor(context.getResources().getColor(R.color.graph_background));
        mRenderer.setApplyBackgroundColor(true);

        mRenderer.setMargins(new int[] { screenHeight/30, screenHeight/7, screenHeight/12, screenHeight/30 });


		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, labelUser);
        db.close();
		return intent;
	}


}