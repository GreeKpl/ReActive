package pl.edu.agh.inz.reactive;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class LineGraph {

	public Intent getIntent(Context context) {
		int[] x1 = {1,2,3,4,5,6,7,9,10};
		int[] y1 = {0,6,5,8,10,9,11,12,15};

		TimeSeries series1 = new TimeSeries("Funkcje poznawcze");
		for (int i=0; i < x1.length; i++) {
			series1.add(x1[i], y1[i]);
		}

		int[] x2 = {0,1,2,3,4,5,7,8,10};
		int[] y2 = {0,1,3,5,5,7,4,6,8};

		TimeSeries series2 = new TimeSeries("Koordynacja ruchowa");
		for (int i=0; i < x2.length; i++) {
			series2.add(x2[i], y2[i]);
		}

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.BLUE);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);

		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.GREEN);
		renderer2.setPointStyle(PointStyle.CIRCLE);
		renderer2.setFillPoints(true);

		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
		mRenderer.setChartTitle("Postępy");
		mRenderer.setAxesColor(Color.MAGENTA);
		mRenderer.setXAxisMin(0);
		mRenderer.setYAxisMin(0);
		mRenderer.setXTitle("Dzień ćwiczeń");
		mRenderer.setYTitle("Punkty");
		mRenderer.setLegendTextSize(25);
		mRenderer.setAxisTitleTextSize(20);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsColor(Color.YELLOW);
		mRenderer.setLegendHeight(100);
		mRenderer.setShowGrid(true);
		mRenderer.setGridColor(Color.DKGRAY);
		mRenderer.setBackgroundColor(Color.BLACK);


		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Pacjent Zdzisek");
		return intent;
	}

}