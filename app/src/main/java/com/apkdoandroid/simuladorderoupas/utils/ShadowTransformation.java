package com.apkdoandroid.simuladorderoupas.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.security.MessageDigest;

public class ShadowTransformation extends BitmapTransformation {
    private static final String ID = "com.example.GlideApp.ShadowTransformation";
    private static final byte[] ID_BYTES = ID.getBytes();
    private final int radius;
    private final int margin;
    private final int shadowColor;

    public ShadowTransformation(int radius, int margin, int shadowColor) {
        this.radius = radius;
        this.margin = margin;
        this.shadowColor = shadowColor;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap result = pool.get(toTransform.getWidth() + margin, toTransform.getHeight() + margin, Bitmap.Config.ARGB_8888);

        if (result == null) {
            result = Bitmap.createBitmap(toTransform.getWidth() + margin, toTransform.getHeight() + margin, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setColor(shadowColor);
        paint.setShadowLayer(radius, 0, 0, shadowColor);
        canvas.drawBitmap(toTransform, margin / 2, margin / 2, paint);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ShadowTransformation;
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
