package com.apkdoandroid.simuladorderoupas


import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.apkdoandroid.simuladorderoupas.databinding.ActivityMainBinding
import com.apkdoandroid.simuladorderoupas.utils.ConfiguracoesCoresManequim
import com.apkdoandroid.simuladorderoupas.utils.alterarCorBainha
import com.apkdoandroid.simuladorderoupas.utils.alterarCorCalcao
import com.apkdoandroid.simuladorderoupas.utils.alterarCorCamisa
import com.apkdoandroid.simuladorderoupas.utils.alterarCorGola
import com.apkdoandroid.simuladorderoupas.utils.alterarCorGolaCamisaPolo
import com.apkdoandroid.simuladorderoupas.utils.alterarCorMeiao
import com.devs.vectorchildfinder.VectorChildFinder
import com.devs.vectorchildfinder.VectorDrawableCompat


class MainActivity : AppCompatActivity() {
    private val binsing by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binsing.root)

       // val drawableResId = R.drawable.gola_2_futebol_model_modelo1
       // val fillColor = extractFillColorFromDrawableXml(this, drawableResId)
        val imageView: ImageView = findViewById(R.id.imageView4)
// Exemplo de uso:
        val svgString = """
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="240dp"
    android:height="296dp"
    android:viewportWidth="236408"
    android:viewportHeight="291873">
  <path
      android:fillColor="#FF000000"
      android:pathData="M..."
      android:fillType="evenOdd"/>
</vector>
"""

        val drawableResId = R.drawable.gola_camisa_polo_mangas_curtas_modelo1
        val vector = VectorChildFinder(this, drawableResId, imageView)

      /*  val path1: VectorDrawableCompat.VFullPath = vector.findPathByName("fundo_gola")
        path1.setFillColor(resources.getColor(R.color.amarelo))*/
        val cor = resources.getColor(R.color.amarelo)


        val modelo = 2;
        if(modelo == 1){ // futebol masculino adulto verso modelo 1

          //  val drawableResId = R.drawable.gola_v_verso_model_modelo1
          //  alterarCorGola("gola",binsing.layoutCamisafutebolMasculinoAdultoVersoModelo1.imageViewGolaModelo,cor,this,drawableResId)
          //  alterarCorCamisa(binsing.layoutCamisafutebolMasculinoAdultoVersoModelo1.imageViewWallpaper,cor)
          //  alterarCorCalcao(binsing.layoutCalcaofutebolMasculinoAdultoVersoModelo1.imageViewWallpaper,cor)
          //  alterarCorMeiao(binsing.layoutMeiaofutebolMasculinoAdultoVersoModelo1.imageViewWallpapeMeiao,cor)
        }else if(modelo == 2){ // futebol masculino adulto  modelo 1
           /* val drawableResId = R.drawable.gola_v_futebol_model_modelo1
            alterarCorGola("gola",binsing.layoutCamisa1.imageViewGolaFundo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisa1.imageViewWallpaper,cor)
            alterarCorCalcao(binsing.layoutCalcao1.imageViewWallpaper,cor)
            alterarCorMeiao(binsing.layoutMeiao1.imageViewWallpapeMeiao,cor)*/
        }else if(modelo == 3){ // futebol masculino adulto  modelo 2
            val drawableResId = R.drawable.gola_v_2_model_modelo2
            alterarCorGola("gola",binsing.layoutCamisa2.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisa2.imageViewWallpaper,cor)
            alterarCorCalcao(binsing.layoutCalcao2.imageViewWallpaper,cor)
            alterarCorMeiao(binsing.layoutMeiao2.imageViewWallpapeMeiao,cor)
        }else if(modelo == 4){ // futebol masculino adulto verso modelo 2
            val drawableResId = R.drawable.gola_v_verso_model_modelo1
            alterarCorGola("gola",binsing.layoutCamisa3.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisa3.imageViewWallpaper,cor)
            alterarCorCalcao(binsing.layoutCalcao3.imageViewFundo,cor)
            alterarCorMeiao(binsing.layoutMeiao3.imageViewWallpapeMeiao,cor)
        }else if(modelo == 5){ // camisa social  manga longa masculina adulto modelo1
            val drawableResId = R.drawable.camisa_social_detalhe_gola_modelo1
            alterarCorGola("gola","bainha",binsing.layoutCamisaSocialMangaLonga.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisaSocialMangaLonga.imageViewWallpaper,cor)

        }else if(modelo == 6){ // camisa social manga longa masculina adulta verso modelo 1
            val drawableResId = R.drawable.camisa_social_detalhe_gola_modelo1
            alterarCorGola("gola","bainha",binsing.layoutCamisaSocialMangaLongaVerso.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisaSocialMangaLongaVerso.imageViewGolaModelo,cor)
            alterarCorCalcao(binsing.layoutCamisaSocialMangaLongaVerso.imageViewWallpaper,cor)

        }else if(modelo == 7){// camisa social  manga curta masculina adulto modelo1
            val drawableResId = R.drawable.camisa_social_gola_modelo1
            alterarCorGola("gola","bainha",binsing.layoutCamisaSocialMangaCurta.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisaSocialMangaCurta.imageViewWallpaper,cor)

        }else if(modelo == 8){// camisa social  manga curta verso masculina adulto modelo1
            alterarCorCamisa(binsing.layoutCamisaSocialMangaCurtaverso.imageViewWallpaper,cor)

        }else if(modelo == 9){ // camisa pesca manga longa masculina adulto modelo 1
            val drawableResId = R.drawable.gola_v_pesca_modelo1
            alterarCorGola("gola",binsing.layoutCamisaPescaMangaComprida.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisaPescaMangaComprida.imageViewWallpaper,cor)
        }else if(modelo == 10){ // camisa pesca manga longa verso masculina adulto modelo 1
            val drawableResId = R.drawable.gola_redonda_pesca_verso_modelo1
            val drawableResIdBainha = R.drawable.camisa_pesca_detalhe_verso_modelo1
            alterarCorGola("gola",binsing.layoutCamisaPescaMangaCompridaVerso.imageViewGolaModelo,cor,this,drawableResId)
            alterarCorBainha("bainha",binsing.layoutCamisaPescaMangaCompridaVerso.imageViewBainha,cor,this,drawableResIdBainha)
            alterarCorCamisa(binsing.layoutCamisaPescaMangaCompridaVerso.imageViewWallpaper,cor)

        }else if(modelo == 11){ // camisa polo masculina adulto modelo 1
            val drawableResId = R.drawable.gola_camisa_polo_mangas_curtas_modelo1
            val cor2 = resources.getColor(R.color.black)
            alterarCorGolaCamisaPolo(binsing.layoutCamisaPolo.imageViewGolaModelo,cor,cor2,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisaPolo.imageViewWallpaper,cor)
        }else if(modelo == 12){
            alterarCorCamisa(binsing.layoutCamisaPoloVerso.imageViewWallpaper,cor)

        }else if(modelo == 13){ //camiseta manga curta masculina adulto
            val drawableResId = R.drawable.gola_v_mangas_curtas_modelo1
            alterarCorGola("gola",binsing.layoutCamisetaCMangaCurtaMasculinoModelo1.imageViewGolaFundo,cor,this,drawableResId)
            alterarCorCamisa(binsing.layoutCamisetaCMangaCurtaMasculinoModelo1.imageViewWallpaper,cor)

        }else if(modelo == 14){//camiseta manga curta verso masculina adulto
            alterarCorCamisa(binsing.layoutCamisetaCMangaCurtaVersoMasculinoModelo1.imageViewWallpaper,cor)

        }










    }



}