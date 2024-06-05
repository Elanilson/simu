package com.apkdoandroid.simuladorderoupas.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.apkdoandroid.simuladorderoupas.R;

public class CropView  extends View {
    private Paint paint;
    private Path clipPath;
    private int arcHeight;

    public CropView(Context context) {
        super(context);
        init();
    }

    public CropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CropView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        arcHeight = 200;
        setLayerType();
    }

    private void setLayerType() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            // looks like this is happening in some devices with lollipop and kitkat
            // trying to fix https://github.com/lifesum/bugs/issues/7040
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        } else {
            setLayerType(LAYER_TYPE_HARDWARE, null);
        }
    }

    private Path createClipPath(int height, int width) {
        final Path path = new Path();

        path.moveTo(0, 0);
        path.lineTo(0, height);
        path.quadTo(width / 2, height + arcHeight, width, height - arcHeight);

        path.lineTo(width, 0);
        path.close();

        return path;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            int height = getMeasuredHeight();
            int width = getMeasuredWidth();
            clipPath = createClipPath(height, width);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (clipPath != null) {
            canvas.drawPath(clipPath, paint);
        }
    }
}
