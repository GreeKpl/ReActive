package pl.edu.agh.inz.reactive;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ImageArrayAdapter extends ArrayAdapter<ImageView> {
	
	private List<ImageView> objects;

	public ImageArrayAdapter(Context context, int resource,
			List<ImageView> objects) {
		super(context, resource, objects);
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return objects.get(position);
	}

}
