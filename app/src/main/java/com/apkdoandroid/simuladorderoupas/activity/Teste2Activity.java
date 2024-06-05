package com.apkdoandroid.simuladorderoupas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkdoandroid.simuladorderoupas.R;
import com.apkdoandroid.simuladorderoupas.utils.CustomImageView;
import com.apkdoandroid.simuladorderoupas.utils.OutlineTransformation;
import com.apkdoandroid.simuladorderoupas.utils.ShadowTransformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

//https://stackoverflow.com/questions/38181230/copy-a-bit-matrix-from-bitmap-image-in-android-programming
public class Teste2Activity extends AppCompatActivity {
    Button grab;
    CustomImageView iv_custom;
    ImageView iv_later;
    ImageView iv_later2;
    Bitmap bitmapBG,bitmapOrg;
    int iMin,jMin,iMax,jMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste2);



        iv_custom = (CustomImageView)findViewById(R.id.iv_custom);
        iv_later = (ImageView)findViewById(R.id.imageViewEstampa);
        iv_later2 = (ImageView)findViewById(R.id.imageViewEstam3);
        grab = (Button)findViewById(R.id.btn_grab);
        grab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iv_custom.buildDrawingCache();
                bitmapBG = iv_custom.getDrawingCache();
               // getSelectedRegionWithBG();
                getSelectedRegionOnly();
                iv_custom.setVisibility(View.GONE);
            }
        });
    }

    /**
     * este método irádevolver a imagem completa Mas apenasa região seleccionadaérealçada e a restante secçãoésimplesmente branca
     */
    private void getSelectedRegionWithBG(){
        int[] mainImageArray = iv_custom.getImagePixels();
        int[] bgImageArray = new int[bitmapBG.getWidth() * bitmapBG.getHeight()];
        int[] finalImageArray = new int[bitmapBG.getWidth() * bitmapBG.getHeight()];
        bitmapBG.getPixels(bgImageArray,0,bitmapBG.getWidth(), 0, 0, bitmapBG.getWidth(), bitmapBG.getHeight());

        if(mainImageArray.length == bgImageArray.length){
            for(int i = 0; i < (bitmapBG.getWidth() * bitmapBG.getHeight());i++){
                if(mainImageArray[i] == bgImageArray[i]){
                    finalImageArray[i] = Color.WHITE;
                }else{
                    finalImageArray[i] = mainImageArray[i];
                }
            }

            Bitmap finalBitmap = Bitmap.createBitmap(bitmapBG.getWidth(), bitmapBG.getHeight(), Bitmap.Config.ARGB_8888);
            // Set the pixels
            finalBitmap.setPixels(finalImageArray, 0, finalBitmap.getWidth(), 0, 0, finalBitmap.getWidth(), finalBitmap.getHeight());
            iv_later.setImageBitmap(finalBitmap);
        }else{
            Toast.makeText(Teste2Activity.this,"Array length are not same",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Este método selecionará apenas a região selecionada da imagem principal e criará o bitmap
     */
    private void getSelectedRegionOnly() {
        generateBounds();

        int width = 1 + jMax - jMin;
        int height = 1 + iMax - iMin;

        Bitmap finalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        int[] pixels = new int[width * height];

        for (int i = iMin; i <= iMax; i++) {
            for (int j = jMin; j <= jMax; j++) {
                int bgPixel = bitmapBG.getPixel(j, i);
                int orgPixel = bitmapOrg.getPixel(j, i);

                // Obtenha os componentes de cor do pixel original
                int red = Color.red(orgPixel);
                int green = Color.green(orgPixel);
                int blue = Color.blue(orgPixel);

                // Defina o canal alfa (transparência) com base na diferença entre os pixels
                int alpha = (bgPixel != orgPixel) ? 255 : 0;

                // Combine os componentes de cor e o canal alfa
                int finalPixel = Color.argb(alpha, red, green, blue);

                // Calcule a posição no array de pixels
                int arrayIndex = (i - iMin) * width + (j - jMin);
                pixels[arrayIndex] = finalPixel;
            }
        }

        // Defina os pixels no Bitmap final
        finalBitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        // Aplicar efeito de sombra usando RequestOptions
        RequestOptions requestOptions = new RequestOptions()
              //  .placeholder(R.drawable.placeholder)  // substitua pelo seu recurso de placeholder
              //  .error(R.drawable.error)  // substitua pelo seu recurso de erro
                .diskCacheStrategy(DiskCacheStrategy.ALL)  // ou DiskCacheStrategy.NONE se preferir não armazenar em cache
                .transform(new OutlineTransformation(10, Color.BLACK));  // ajuste conforme necessário

      //  iv_later.setImageBitmap(finalBitmap);
        Glide.with(this)
                .load(finalBitmap)
               // .apply(requestOptions)  // Ajuste o tamanho desejado
                .into(iv_later);

    }




    /**
     * Gera o limite da região colorida
     */
    private void generateBounds() {
        bitmapOrg = iv_custom.getMainBitmap();
        iMax = jMax = 0;
        iMin = jMin = bitmapBG.getWidth() * bitmapBG.getHeight();

        for (int i = 0; i < bitmapBG.getHeight(); i++) {
            for (int j = 0; j < bitmapBG.getWidth(); j++) {
                int bgPixel = bitmapBG.getPixel(j, i);
                int orgPixel = bitmapOrg.getPixel(j, i);

                if (bgPixel != orgPixel) {
                    iMin = Math.min(iMin, i);
                    jMin = Math.min(jMin, j);
                    iMax = Math.max(iMax, i);
                    jMax = Math.max(jMax, j);
                }
            }
        }
    }

    public Bitmap doFilter(Bitmap source, Bitmap filter) {
        Bitmap filtered = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());
        Paint paint = new Paint();
        Canvas canvas = new Canvas(filtered);
        canvas.drawBitmap(source, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(filter, 0, 0, paint);
        return filtered;
    }
}