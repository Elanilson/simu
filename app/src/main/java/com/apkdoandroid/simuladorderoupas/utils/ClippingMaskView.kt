package com.apkdoandroid.simuladorderoupas.utils

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.apkdoandroid.simuladorderoupas.R

class ClippingMaskView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private var landscapeBitmap: Bitmap? = null
    private var maskBitmap: Bitmap? = null
    private var paint: Paint = Paint()

    init {
        // Carregue suas imagens aqui
        landscapeBitmap = BitmapFactory.decodeResource(resources, R.drawable.wall)
        maskBitmap = BitmapFactory.decodeResource(resources, R.drawable.model_camisa_molde2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Verifique se as imagens foram carregadas corretamente
        if (landscapeBitmap == null || maskBitmap == null) {
            return
        }

        // Crie um bitmap para aplicar a máscara
        val resultBitmap = Bitmap.createBitmap(maskBitmap!!.width, maskBitmap!!.height, Bitmap.Config.ARGB_8888)
        val resultCanvas = Canvas(resultBitmap)

        // Desenhe a paisagem na área da máscara
        resultCanvas.drawBitmap(landscapeBitmap!!, null, Rect(0, 0, maskBitmap!!.width, maskBitmap!!.height), paint)

        // Configure o modo de mesclagem para aplicar a máscara
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

        // Desenhe a máscara na área da paisagem (aplicará o recorte)
        resultCanvas.drawBitmap(maskBitmap!!, null, Rect(0, 0, maskBitmap!!.width, maskBitmap!!.height), paint)

        // Limpe o modo de mesclagem
        paint.xfermode = null

        // Desenhe o resultado na tela
        canvas.drawBitmap(resultBitmap, null, Rect(0, 0, width, height), paint)
    }
}
