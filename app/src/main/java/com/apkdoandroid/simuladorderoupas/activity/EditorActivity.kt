package com.apkdoandroid.simuladorderoupas.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LightingColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.BitmapDrawable
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apkdoandroid.simuladorderoupas.ItemAdapter
import com.apkdoandroid.simuladorderoupas.R
import com.apkdoandroid.simuladorderoupas.databinding.ActivityEditorBinding
import com.caverock.androidsvg.SVG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.security.AccessController.getContext


class EditorActivity : AppCompatActivity() {
    private val binding by lazy { ActivityEditorBinding.inflate(layoutInflater) }
    val STORAGE_PERMISSION_REQUEST_CODE = 6565

    var result : Bitmap? = null;
    var maskBitmap : Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val recyclerView: RecyclerView = binding.recyclerview
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = RecyclerView.HORIZONTAL
        val itemCount = 10  // Número de itens desejados
        val adapter = ItemAdapter(itemCount)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
      //  mask()

        val svgUrl = "https://apkdoandroidonline.com/totem/estampa.svg"
      //  loadSvg(svgUrl)

            loadSvgAndAddImageView(svgUrl,adapter)
    CoroutineScope(Dispatchers.IO).launch {
        delay(3000)
        withContext(Dispatchers.Main){
            // Criar um novo ImageView
          /*  val novoImageView = ImageView(this@EditorActivity)
            novoImageView.id = View.generateViewId() // Gera um ID único para o novo ImageView
            novoImageView.setImageResource(R.drawable.wall) // Substitua pelo ID da sua imagem

            // Adicionar o novo ImageView ao ConstraintLayout
            val constraintLayout = adapter.view.findViewById<ConstraintLayout>(R.id.constraintlayoutTeste) // Substitua pelo ID do seu ConstraintLayout


            // Obter as configurações do imageViewMolde2Camisa3
            val layoutParams = adapter.view.findViewById<ImageView>(R.id.imageViewMolde2Camisa3).layoutParams
            val width = layoutParams.width
            val height = layoutParams.height

            // Configurar o tamanho do novo ImageView
            novoImageView.layoutParams = ConstraintLayout.LayoutParams(width, height)

            novoImageView.elevation = 3f

            // Adicionar o novo ImageView ao ConstraintLayout
           // val constraintLayout = findViewById<ConstraintLayout>(R.id.seuConstraintLayout) // Substitua pelo ID do seu ConstraintLayout
            constraintLayout.addView(novoImageView)

            // Configurar as restrições para o novo ImageView
            val constraintSet = ConstraintSet()
            constraintSet.clone(constraintLayout)

            // Configurar as restrições de posicionamento do novo ImageView
            constraintSet.connect(
                novoImageView.id,
                ConstraintSet.TOP,
                R.id.imageViewMolde2Camisa3, // Substitua pelo ID do seu ImageView existente
                ConstraintSet.TOP
            )
            constraintSet.connect(
                novoImageView.id,
                ConstraintSet.START,
                ConstraintSet.PARENT_ID,
                ConstraintSet.START
            )
            constraintSet.connect(
                novoImageView.id,
                ConstraintSet.END,
                ConstraintSet.PARENT_ID,
                ConstraintSet.END
            )

            // Aplicar as restrições ao ConstraintLayout
            constraintSet.applyTo(constraintLayout)

            // Trazer o novo ImageView para a frente
            novoImageView.bringToFront()*/

            // Remover e readicionar as visualizações para ajustar a ordem
          //  constraintLayout.removeView(adapter.view.findViewById<ImageView>(R.id.imageView17))
           // constraintLayout.addView(adapter.view.findViewById<ImageView>(R.id.imageView17))

        }
    }



      //  LoadSvgTask().execute(svgUrl)
       // val svgXmlList = listSvgXml(svgUrl)
       // Log.d("SVG XML Tags", svgXmlList.toString())
    }

    private inner class LoadSvgTask : AsyncTask<String?, Void?, Bitmap?>() {

        override fun doInBackground(vararg params: String?): Bitmap? {
            try {
                val url = URL(params[0])
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.connect()
                var inputStream: InputStream = connection.inputStream
                // Carrega o XML do SVG como uma string
             //   val svgXml = inputStreamToXML(connection.inputStream)


              // val svg = SVG.getFromString(svgXml)
                val svg = SVG.getFromInputStream(inputStream)



            /*    Log.d("my_svg","- ${svgXml}")
                var valoresFill = obterValoresFillColorDaStringSVG(svgXml)
                Log.d("my_svg","- ${valoresFill}")
                val coresSubstitutas  = listOf("#fff", "#ecd407")

                var svgModificado = substituirCoresNaStringSVG(svgXml, valoresFill, coresSubstitutas)

                saveFile(svgModificado)


              //  val svg = SVG.getFromString(svgModificado)

                // Imprime todos os valores de fill
                for (valor in valoresFill) {
                    println("Valor de fill: $valor")
                    Log.d("my_svg","Original Valor de fill: $valor")
                }
                 valoresFill = obterValoresFillColorDaStringSVG(svgModificado)

                for (valor in valoresFill) {
                    Log.d("my_svg","Modificado Valor de fill: $valor")
                }

                Log.d("my_svg",svgModificado)*/
                // Converte o SVG para um Picture
                val picture = svg.renderToPicture()

                // Cria um Bitmap
                val bitmap = Bitmap.createBitmap(
                    picture.width, picture.height,
                    Bitmap.Config.ARGB_8888
                )

                // Desenha o Picture no Bitmap
                val canvas = Canvas(bitmap)
                canvas.drawPicture(picture)

                // Aplica as alterações de cor (exemplo: troca preto por vermelho)
                applyColorFilter(bitmap)

                return bitmap
            } catch (e: IOException) {
                Log.d("my_svg","- ${e.message}")
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            if (bitmap != null) {
                // Define o Bitmap no ImageView
                binding.imageView.setImageDrawable(BitmapDrawable(resources, bitmap))
            }
        }

        private fun applyColorFilter(bitmap: Bitmap) {
            // Exemplo: substitui preto (#000000) por vermelho (#FF0000)
            val paint = Paint()
            val colorFilter = LightingColorFilter(Color.BLACK, Color.RED)
            paint.colorFilter = colorFilter

            val canvas = Canvas(bitmap)
            canvas.drawBitmap(bitmap, 0f, 0f, paint)
        }
    }

    fun saveFile(texto : String) {
        // Cria um objeto File para o arquivo
        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "my_le.txt")

        // Cria um objeto FileOutputStream para o arquivo
        val fos = FileOutputStream(file)

        // Escreve o conteúdo do arquivo
        fos.write(texto.toByteArray())

        // Fecha o objeto FileOutputStream
        fos.close()
    }
    // Solicita permissão para acessar o armazenamento externo



    fun inputStreamToXML(inputStream: InputStream ) : String{
        // Lê o InputStream
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()

        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
          //  Log.d("my_svg","line- ${line}")
            stringBuilder.append(line)
        }

        return stringBuilder.toString()
    }

    fun obterValoresFillColorDaStringSVG(svgXml: String): List<String> {
        val pattern = """(?:fill|stop-color)\s*=\s*["'](#(?:[0-9a-fA-F]{3}|[0-9a-fA-F]{6}|[0-9a-fA-F]{8}))["']""".toRegex()
        val matchResults = pattern.findAll(svgXml)
        val valoresFill = matchResults.map { it.groupValues[1] }.toList()

        // Convertendo para um conjunto para manter apenas valores únicos
        val valoresUnicos = valoresFill.toSet().toList()

        return valoresUnicos
    }


    fun substituirCoresNaStringSVG(svgXml: String, coresOriginais: List<String>, coresSubstitutas: List<String>): String {
        Log.d("my_svg","coresOriginais- ${coresOriginais}")
        Log.d("my_svg","coresSubstitutas- ${coresSubstitutas}")
        var svgModificado = svgXml

        if (coresOriginais.size != coresSubstitutas.size) {
            throw IllegalArgumentException("As listas de cores originais e substitutas devem ter o mesmo tamanho.")
        }

        for (i in coresOriginais.indices) {
            svgModificado = svgModificado.replace(coresOriginais[i], coresSubstitutas[i])
        }

        return svgModificado
    }



    fun loadSvg(urlString: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = try {
                withContext(Dispatchers.IO) {
                    val url = URL(urlString)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.connect()
                   // val svgXml = inputStreamToXML(connection.inputStream)
                    val inputStream = connection.inputStream
                    val svgXml = inputStreamToXML(inputStream)
                  //  val svg = SVG.getFromInputStream(inputStream)

                    //
                    var valoresFill = obterValoresFillColorDaStringSVG(svgXml)
                    Log.d("my_svg","- ${valoresFill}")
                    val coresSubstitutas  = listOf("#fff", "#fff200")

                    var svgModificado = substituirCoresNaStringSVG(svgXml, valoresFill, coresSubstitutas)
                    //
                    val svg = SVG.getFromString(svgModificado)
                  //  val svg = SVG.getFromString(svgXml)
                    val picture = svg.renderToPicture()
                    val bitmap = Bitmap.createBitmap(picture.width, picture.height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(bitmap)
                    canvas.drawPicture(picture)
                    //  applyColorFilter(bitmap)
                    bitmap
                }
            } catch (e: IOException) {
                Log.d("my_svg", "- ${e.message}")
                e.printStackTrace()
                null
            }

            withContext(Dispatchers.Main) {
                if (bitmap != null) {
                  //  binding.modelo.imageViewEstampa.setImageDrawable(BitmapDrawable(resources, bitmap))
                }
            }
        }
    }


    fun mask2(){
        try {
            Log.d("DEBUG", "Antes de carregar as imagens")
            val original: Bitmap? = BitmapFactory.decodeResource(this.resources, R.drawable.time)
            val mask: Bitmap? = BitmapFactory.decodeResource(this.resources,
                R.drawable.adulto_camisa_textura_futebol_modelo1
            )
            Log.d("DEBUG", "Imagens carregadas ")

            // Carregar o SVG usando a biblioteca svg-android
            val maskDrawable = AppCompatResources.getDrawable(this, R.drawable.model_camisa_molde2)

            Log.d("DEBUG", "Imagens original ${original != null}")
            Log.d("DEBUG", "Imagens mask ${mask != null}")

            if (original != null && mask != null) {
                val iv_width = original.width
                val iv_height = original.height

                val result = Bitmap.createBitmap(iv_width, iv_height, Bitmap.Config.ARGB_8888)
                val maskBitmap = Bitmap.createScaledBitmap(mask, iv_width, iv_height, true)

                val canvas = Canvas(result)
                val paint = Paint(Paint.ANTI_ALIAS_FLAG)
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)

                Log.d("DEBUG", "Antes de desenhar no Canvas")
                canvas.drawBitmap(original, 0f, 0f, null)
                canvas.drawBitmap(maskBitmap, 0f, 0f, paint)
                Log.d("DEBUG", "Imagens desenhadas no Canvas")

                paint.xfermode = null
                paint.style = Paint.Style.STROKE

                binding.modelo.imageViewEstampa.setImageBitmap(result)
                Log.d("DEBUG", "Imagem definida no ImageView")
            }else{
                Log.d("DEBUG", "else")
            }
        } catch (e: Exception) {
            Log.e("error", "=== "+e.message.toString())
        }
    }

    fun mask(){
        try {
            val original: Bitmap? = BitmapFactory.decodeResource(resources, R.drawable.time)

            // Carregar o Vector Drawable usando a classe VectorDrawableCompat
            var maskDrawable = AppCompatResources.getDrawable(this,
                R.drawable.model_camisa_molde2
            )
            maskDrawable = binding.modelo.imageViewEstampa.getDrawable();
            val maskBitmap = Bitmap.createBitmap(original!!.width, original.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(maskBitmap)

            if (original != null && maskDrawable != null) {
                val iv_width = original.width
                val iv_height = original.height

                val result = Bitmap.createBitmap(iv_width, iv_height, Bitmap.Config.ARGB_8888)

                val paint = Paint(Paint.ANTI_ALIAS_FLAG)

                // Desenhar a imagem original no resultado
                val resultCanvas = Canvas(result)
                resultCanvas.drawBitmap(original, 0f, 0f, null)

                // Desenhar o Vector Drawable na área desejada usando o Canvas
                maskDrawable.setBounds(0, 0, iv_width, iv_height)
                maskDrawable.draw(canvas)

                // Aplicar a máscara no resultado
                paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_ATOP)
                resultCanvas.drawBitmap(maskBitmap, 0f, 0f, paint)
                paint.xfermode = null

                binding.modelo.imageViewEstampa.setImageBitmap(result)
            }
        } catch (e: Exception) {
            Log.e("error", e.message.toString())
        }
    }

    fun draw(canvas: Canvas) {
        val original =
            BitmapFactory.decodeResource(resources, R.drawable.wall)
        val mask = BitmapFactory.decodeResource(resources, R.drawable.model_camisa_molde2)

        //You can change original image here and draw anything you want to be masked on it.
        val result = Bitmap.createBitmap(mask.width, mask.height, Bitmap.Config.ARGB_8888)
        val tempCanvas = Canvas(result)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        tempCanvas.drawBitmap(original, 0f, 0f, null)
        tempCanvas.drawBitmap(mask, 0f, 0f, paint)
        paint.xfermode = null

        //Draw result after performing masking
        canvas.drawBitmap(result, 0f, 0f, Paint())
    }



    fun loadSvgAndAddImageView(urlString: String, adapter: ItemAdapter) {
        CoroutineScope(Dispatchers.IO).launch {
            val bitmap = try {
                withContext(Dispatchers.IO) {
                    val url = URL(urlString)
                    val connection = url.openConnection() as HttpURLConnection
                    connection.connect()

                    val inputStream = connection.inputStream
                    val svgXml = inputStreamToXML(inputStream)
                    val valoresFill = obterValoresFillColorDaStringSVG(svgXml)
                    val coresSubstitutas  = listOf("#fff", "#fff200")
                    val svgModificado = substituirCoresNaStringSVG(svgXml, valoresFill, coresSubstitutas)

                    val svg = SVG.getFromString(svgModificado)
                    val picture = svg.renderToPicture()
                    val bitmap = Bitmap.createBitmap(picture.width, picture.height, Bitmap.Config.ARGB_8888)
                    val canvas = Canvas(bitmap)
                    canvas.drawPicture(picture)

                    bitmap
                }
            } catch (e: IOException) {
                Log.d("my_svg", "- ${e.message}")
                e.printStackTrace()
                null
            }

            withContext(Dispatchers.Main) {
                if (bitmap != null) {
                    // Criar um novo ImageView
                    val novoImageView = ImageView(this@EditorActivity) // Substitua pelo contexto do seu adapter
                    novoImageView.id = View.generateViewId() // Gera um ID único para o novo ImageView
                    novoImageView.setImageBitmap(bitmap)

                    // Adicionar o novo ImageView ao ConstraintLayout
                    val constraintLayout = adapter.view.findViewById<ConstraintLayout>(R.id.constraintlayoutTeste) // Substitua pelo ID do seu ConstraintLayout

                    // Obter as configurações do imageViewMolde2Camisa3
                    val layoutParams = adapter.view.findViewById<ImageView>(R.id.imageViewMolde2Camisa3).layoutParams
                    val width = layoutParams.width
                    val height = layoutParams.height

                    // Configurar o tamanho do novo ImageView
                    novoImageView.layoutParams = ConstraintLayout.LayoutParams(width, height)

                    novoImageView.elevation = 3f

                    // Adicionar o novo ImageView ao ConstraintLayout
                    constraintLayout.addView(novoImageView)

                    // Configurar as restrições para o novo ImageView
                    val constraintSet = ConstraintSet()
                    constraintSet.clone(constraintLayout)

                    // Configurar as restrições de posicionamento do novo ImageView
                    constraintSet.connect(
                        novoImageView.id,
                        ConstraintSet.TOP,
                        R.id.imageViewMolde2Camisa3, // Substitua pelo ID do seu ImageView existente
                        ConstraintSet.TOP
                    )
                    constraintSet.connect(
                        novoImageView.id,
                        ConstraintSet.START,
                        ConstraintSet.PARENT_ID,
                        ConstraintSet.START
                    )
                    constraintSet.connect(
                        novoImageView.id,
                        ConstraintSet.END,
                        ConstraintSet.PARENT_ID,
                        ConstraintSet.END
                    )

                    // Aplicar as restrições ao ConstraintLayout
                    constraintSet.applyTo(constraintLayout)

                    // Trazer o novo ImageView para a frente
                    novoImageView.bringToFront()
                }
            }
        }
    }



}