package com.apkdoandroid.simuladorderoupas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.apkdoandroid.simuladorderoupas.R;
import com.apkdoandroid.simuladorderoupas.utils.CustomImageView;

public class testeActivity extends AppCompatActivity {
    Button grab;
    CustomImageView iv_custom;
    ImageView iv_later;
    Bitmap bitmapBG,bitmapOrg;
    int iMin,jMin,iMax,jMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste);
        iv_custom = (CustomImageView)findViewById(R.id.iv_custom);
        iv_custom.setDrawingCacheEnabled(true);
        iv_later = (ImageView)findViewById(R.id.iv_later);
        grab = (Button)findViewById(R.id.btn_grab);
        grab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_custom.buildDrawingCache();
                bitmapBG = iv_custom.getDrawingCache();
                getSelectedRegionOnly();
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
            Toast.makeText(testeActivity.this,"Array length are not same",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Este método selecionará apenas a região selecionada da imagem principal e criará o bitmap
     */
    private void getSelectedRegionOnly() {
        generateBounds();

        int width = 1 + jMax - jMin;
        int height = 1 + iMax - iMin;

        Bitmap finalBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        for (int i = iMin; i <= iMax; i++) {
            for (int j = jMin; j <= jMax; j++) {
                int bgPixel = bitmapBG.getPixel(j, i);
                int orgPixel = bitmapOrg.getPixel(j, i);

                if (bgPixel != orgPixel) {
                    finalBitmap.setPixel(j - jMin, i - iMin, orgPixel);
                } else {
                    finalBitmap.setPixel(j - jMin, i - iMin, Color.WHITE);
                }
            }
        }

        iv_later.setImageBitmap(finalBitmap);
    }


    /**
     * gera o limite da região colorida
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


}