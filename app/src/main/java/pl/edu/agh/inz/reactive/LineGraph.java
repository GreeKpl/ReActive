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

import java.util.Iterator;
import java.util.Map;

public class LineGraph {



	public Intent getIntent(Context context) {

        DatabaseManager db = new DatabaseManager(context);
        db.open();
        User user = db.getActiveUser();
        String labelUser = user.getLogin() + " (" + user.getName() + " " + user.getSurname() + ")";

        //wypisywanie zawartosci tabel
        db.printUsersTable();
        db.printLevelsTable();
        db.printResultsTable();
        System.out.println("Minimalna data: "+db.getMinDate(user.getLogin()));

        //Rainbow
        Map<Long, Integer> map1 = db.getAchievements(user.getLogin(), 1);
        Iterator<Map.Entry<Long, Integer>> iterator = map1.entrySet().iterator();
        TimeSeries series1 = new TimeSeries("Rainbow   ");

        int length1 = map1.size();
        System.out.println("length1 = "+length1);
        long[] x1 = new long[length1];
        int[] y1 = new int[length1];
        int i = 0;
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();

            x1[i] = new Long(entry.getKey().toString());
            y1[i] = new Integer(entry.getValue().toString());
            System.out.println("Rainbow Indeks: " + x1[i] + " wynik: " + y1[i]);
            series1.add(x1[i], y1[i]);
            i++;
        }

        //Rainbow training
        Map<Long, Integer> map2 = db.getAchievements(user.getLogin(), 2);
        iterator = map2.entrySet().iterator();
        TimeSeries series2 = new TimeSeries("Rainbow training   ");

        int length2 = map2.size();
        System.out.println("length2 = "+length2);
        long[] x2 = new long[length2];
        int[] y2 = new int[length2];
        i = 0;
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();

            x2[i] = new Long(entry.getKey().toString());
            y2[i] = new Integer(entry.getValue().toString());
            System.out.println("Rainbow training Indeks: " + x2[i] + " wynik: " + y2[i]);
            series2.add(x2[i], y2[i]);
            i++;
        }

        //Three
        Map<Long, Integer> map3 = db.getAchievements(user.getLogin(), 3);
        iterator = map3.entrySet().iterator();
        TimeSeries series3 = new TimeSeries("Three   ");

        int length3 = map3.size();
        System.out.println("length3 = "+length3);
        long[] x3 = new long[length3];
        int[] y3 = new int[length3];
        i = 0;
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();

            x3[i] = new Long(entry.getKey().toString());
            y3[i] = new Integer(entry.getValue().toString());
            System.out.println("Three Indeks: " + x3[i] + " wynik: " + y3[i]);
            series3.add(x3[i], y3[i]);
            i++;
        }

        //Three training
        Map<Long, Integer> map4 = db.getAchievements(user.getLogin(), 4);
        iterator = map4.entrySet().iterator();
        TimeSeries series4 = new TimeSeries("Three training  ");

        int length4 = map4.size();
        System.out.println("length4 = "+length4);
        long[] x4 = new long[length4];
        int[] y4 = new int[length4];
        i = 0;
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();

            x4[i] = new Long(entry.getKey().toString());
            y4[i] = new Integer(entry.getValue().toString());
            System.out.println("Three training Indeks: " + x4[i] + " wynik: " + y4[i]);
            series4.add(x4[i], y4[i]);
            i++;
        }

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);


		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(context.getResources().getColor(R.color.graph_first_chart));
		renderer.setPointStyle(PointStyle.CIRCLE);
		renderer.setFillPoints(true);

		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(context.getResources().getColor(R.color.graph_second_chart));
		renderer2.setPointStyle(PointStyle.CIRCLE);
		renderer2.setFillPoints(true);

        XYSeriesRenderer renderer3 = new XYSeriesRenderer();
        renderer3.setColor(context.getResources().getColor(R.color.graph_third_chart));
        renderer3.setPointStyle(PointStyle.CIRCLE);
        renderer3.setFillPoints(true);

        XYSeriesRenderer renderer4 = new XYSeriesRenderer();
        renderer4.setColor(context.getResources().getColor(R.color.graph_fourth_chart));
        renderer4.setPointStyle(PointStyle.CIRCLE);
        renderer4.setFillPoints(true);

		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
        mRenderer.addSeriesRenderer(renderer3);
        mRenderer.addSeriesRenderer(renderer4);

		mRenderer.setAxesColor(context.getResources().getColor(R.color.graph_axes));
		mRenderer.setXAxisMin(0);
		mRenderer.setYAxisMin(0);

        mRenderer.setAxisTitleTextSize(20);
        mRenderer.setLabelsColor(context.getResources().getColor(R.color.graph_axes_labels));
		mRenderer.setXTitle("Dzień ćwiczeń");
		mRenderer.setYTitle("Punkty");

		mRenderer.setLegendTextSize(20);
        mRenderer.setLegendHeight(50);

        mRenderer.setLabelsTextSize(20);    //liczby
        mRenderer.setYLabels(7);
        mRenderer.setXLabels(7);
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

        mRenderer.setMargins(new int[] {20, 70, 30, 20});


		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, labelUser);
        db.close();
		return intent;
	}

}