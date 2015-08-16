package pl.edu.agh.inz.reactive.games.fit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.ImageView;

/**
 * Created by Jacek on 2015-08-16.
 */
public class BackgroundLines extends ImageView {

    int rows;

    int cols;

    int lineWidth;

    int backColor;

    int lineColor;

    Point screenSize;

    public BackgroundLines(Context context, int rows, int cols, int lineWidth, int backColor, int lineColor, Point screenSize) {
        super(context);
        this.rows = rows;
        this.cols = cols;
        this.lineWidth = lineWidth;
        this.backColor = backColor;
        this.lineColor = lineColor;
        this.screenSize = screenSize;
    }

    private void drawLines(Canvas canvas) {

        int hight = screenSize.y;
        int width = screenSize.x;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(lineColor);

        int x = lineWidth / 2;
        int y = lineWidth / 2;

        for (int i = 0; i < rows; i++) {
            canvas.drawLine(0, y, width, lineWidth, paint);
            x =+ width / cols;
        }

        for (int j = 0; j < cols; j++) {
            canvas.drawLine(x, 0, lineWidth, hight, paint);
            x =+ width / cols;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(backColor);
        drawLines(canvas);
    }

}
