package com.apkdoandroid.simuladorderoupas.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

public class OutlineTransformation extends BitmapTransformation {
    private static final String ID = "com.example.GlideApp.OutlineTransformation";
    private static final byte[] ID_BYTES = ID.getBytes();
    private final int strokeWidth;
    private final int strokeColor;

    public OutlineTransformation(int strokeWidth, int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);

        if (result == null) {
            result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(toTransform, 0, 0, null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(strokeColor);
        canvas.drawRect(0, 0, toTransform.getWidth(), toTransform.getHeight(), paint);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof OutlineTransformation;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
