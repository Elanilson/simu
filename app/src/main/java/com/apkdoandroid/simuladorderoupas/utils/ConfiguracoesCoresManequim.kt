package com.apkdoandroid.simuladorderoupas.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.widget.ImageView
import com.apkdoandroid.simuladorderoupas.R
import com.devs.vectorchildfinder.VectorChildFinder
import com.devs.vectorchildfinder.VectorDrawableCompat

object ConfiguracoesCoresManequim {
}

fun alterarCorCalcao(imageView: ImageView,cor : Int){
   // imageView.setBackgroundColor(cor)
    imageView.setColorFilter(cor);
}
fun alterarCorCamisa(imageView: ImageView,cor : Int){
    // imageView.setBackgroundColor(cor)
    imageView.setColorFilter(cor);
}

fun alterarCorMeiao(imageView: ImageView,cor : Int){
    // imageView.setBackgroundColor(cor)
    imageView.setColorFilter(cor);
}

fun alterarCorGola(name : String, imageView: ImageView,cor : Int,context: Context, drawableResId : Int){

    val vector = VectorChildFinder(context, drawableResId, imageView)

    val path1: VectorDrawableCompat.VFullPath = vector.findPathByName(name)
    path1.setFillColor(cor)
}

fun alterarCorBainha(name : String, imageView: ImageView,cor : Int,context: Context, drawableResId : Int){

    val vector = VectorChildFinder(context, drawableResId, imageView)

    val path1: VectorDrawableCompat.VFullPath = vector.findPathByName(name)
    path1.setFillColor(cor)
}

fun alterarCorGola(name : String,bainha : String, imageView: ImageView,cor : Int,context: Context, drawableResId : Int){

    val vector = VectorChildFinder(context, drawableResId, imageView)

    val path1: VectorDrawableCompat.VFullPath = vector.findPathByName(name)
    path1.setFillColor(cor)
    val path2: VectorDrawableCompat.VFullPath = vector.findPathByName(bainha)
    path2.setFillColor(cor)
}
fun alterarCorGolaCamisaPolo( imageView: ImageView,cor : Int,cor2 : Int,context: Context, drawableResId : Int){

    val vector = VectorChildFinder(context, drawableResId, imageView)

    vector.findPathByName("manga7").setFillColor(cor2)
    vector.findPathByName("manga6").setFillColor(cor2)
    vector.findPathByName("manga5").setFillColor(cor2)
    vector.findPathByName("manga4").setFillColor(cor2)
    vector.findPathByName("manga3").setFillColor(cor2)
    vector.findPathByName("manga2").setFillColor(cor2)
    vector.findPathByName("manga1").setFillColor(cor2)

    vector.findPathByName("gola6").setFillColor(cor)
    vector.findPathByName("gola5").setFillColor(cor)
    vector.findPathByName("gola4").setFillColor(cor)
    vector.findPathByName("gola3").setFillColor(cor)
    vector.findPathByName("gola2").setFillColor(cor)
    vector.findPathByName("gola1").setFillColor(cor)

}


