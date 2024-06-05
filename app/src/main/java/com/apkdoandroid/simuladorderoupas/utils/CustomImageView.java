package com.apkdoandroid.simuladorderoupas.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.apkdoandroid.simuladorderoupas.R;

public class CustomImageView extends AppCompatImageView {
    private int width, height;
    private Bitmap backgroundBitmap, foregroundBitmap;
    private Paint paint;

    public CustomImageView(Context context) {
        super(context);
        initialize(context);
    }

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context);
    }

    private void initialize(Context context) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setFilterBitmap(true); // Ativar filtro para melhorar a qualidade
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setSubpixelText(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.BLUE);
        paint.setFlags(
                        Paint.ANTI_ALIAS_FLAG |
                        Paint.FAKE_BOLD_TEXT_FLAG |
                        Paint.UNDERLINE_TEXT_FLAG |
                        Paint.FILTER_BITMAP_FLAG |
                        Paint.DITHER_FLAG |
                        Paint.STRIKE_THRU_TEXT_FLAG |
                        Paint.EMBEDDED_BITMAP_TEXT_FLAG |
                        Paint.LINEAR_TEXT_FLAG |
                                Paint.SUBPIXEL_TEXT_FLAG
        );
        paint.setElegantTextHeight(true);
        paint.setUnderlineText(true);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (backgroundBitmap == null) {
            backgroundBitmap = getBitmapFromDrawable(getDrawable());
        }
        if (foregroundBitmap == null) {
            foregroundBitmap = createBitmap(R.drawable.model_camisa_molde2_background);
        }



        // Desenha a imagem original primeiro (no fundo)
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);

        // Desenha a imagem recortada por cima
        canvas.drawBitmap(foregroundBitmap, 0, 0, paint);
        paint.setColor(Color.RED);



        //canvas.drawRect(400, 400, 600, 600, paint);
        //canvas.drawCircle(400, 400, 200, paint);
    }

    public int[] getImagePixels() {
        int[] pixels = new int[backgroundBitmap.getWidth() * backgroundBitmap.getHeight()];
        backgroundBitmap.getPixels(pixels, 0, backgroundBitmap.getWidth(), 0, 0, backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
        return pixels;
    }

    public Bitmap getMainBitmap() {
        return backgroundBitmap;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        // Reset bitmaps when size changes
        backgroundBitmap = null;
        foregroundBitmap = null;
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private Bitmap createBitmap(int resId) {
        Bitmap logo = BitmapFactory.decodeResource(getResources(), resId);
        return Bitmap.createScaledBitmap(logo, width, height, true).copy(Bitmap.Config.ARGB_8888, true);
    }
}
