package com.bangkitacademy.harvesthero.helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.provider.MediaStore
import android.util.Log
import com.bangkitacademy.harvesthero.R
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.CastOp
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class ImageClassifierHelper(
    val context: Context,
    val classifierListener: ClassifierListener?
) {

    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    private fun setupImageClassifier() {
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(0.1f)
            .setMaxResults(3)

        val baseOptionBuilder = BaseOptions.builder()
            .setNumThreads(4)
        optionsBuilder.setBaseOptions(baseOptionBuilder.build())

        try {
            imageClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                "crop_growth_classifier_model.tflite",
                optionsBuilder.build()
            )
        } catch (e: IllegalStateException) {
            classifierListener?.onError(context.getString(R.string.image_classifier_failed))
            Log.e("ImageClassifierHelper", e.message.toString())
        }
    }

    fun classifyStaticImage(imageUri: Uri) {
        val contentResolver = context.contentResolver
        var tensorImage = TensorImage(DataType.FLOAT32)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(150, 150, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(CastOp(DataType.FLOAT32))
            .add(NormalizeOp(127.5f, 127.5f))
            .build()

        try {
            val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(contentResolver, imageUri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            }

            val argbBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)
            tensorImage.load(argbBitmap)
            tensorImage = imageProcessor.process(tensorImage)

        } catch (e: Exception) {
            classifierListener?.onError(e.message.toString())
            return
        }

        val inferenceTime = SystemClock.uptimeMillis()
        val results = imageClassifier?.classify(tensorImage)
        val inferenceDuration = SystemClock.uptimeMillis() - inferenceTime

        classifierListener?.onResults(
            results?.firstOrNull()?.categories?.map { it.score } ?: emptyList(),
            inferenceDuration
        )
    }

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(results: List<Float>?, inferenceTime: Long)
    }
}