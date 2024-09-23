package com.github.pmdmiesbalmis.utilities.imagetools

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Base64
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.ByteArrayOutputStream
import kotlin.math.sqrt

private const val BYTES_PER_KB = 1024

/**
 * Redimensiona una `ImageBitmap` manteniendo la relación de aspecto.
 * @param ladoLargo El lado más largo deseado en la imagen redimensionada.
 * @return La `ImageBitmap` redimensionada.
 */
fun ImageBitmap.resize(ladoLargo: Int): ImageBitmap {
    val w = this.width
    val h = this.height
    val newW: Int
    val newH: Int
    if (w > h) {
        val aspRat = h.toFloat() / w.toFloat()
        newW = ladoLargo
        newH = (newW * aspRat).toInt()
    } else {
        val aspRat = w.toFloat() / h.toFloat()
        newH = ladoLargo
        newW = (newH * aspRat).toInt()
    }
    return Bitmap.createScaledBitmap(this.asAndroidBitmap(), newW, newH, false).asImageBitmap()
}

/**
 * Convierte una `ImageBitmap` a un `String` codificado en Base64.
 * @param formato El formato de compresión deseado (por defecto, JPG_75).
 * @return El String que representa la imagen en Base64.
 */
fun ImageBitmap.toBase64(formato: FormatosCompresion = FormatosCompresion.PNG_90): String = this.asAndroidBitmap().let { bitmap ->
    val stream = ByteArrayOutputStream()
    val (f, q) = formato.info
    bitmap.compress(f, q, stream)
    Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT)
}

/**
 * Convierte una `ImageBitmap` a un `ByteArray`.
 * @param formato El formato de compresión deseado (por defecto, PNG_90).
 * @return El ByteArray que representa la imagen en el formato especificado.
 */
fun ImageBitmap.toBlob(formato: FormatosCompresion = FormatosCompresion.PNG_90): ByteArray {
    val stream = ByteArrayOutputStream()
    val (f, q) = formato.info
    this.asAndroidBitmap().compress(f, q, stream)
    return stream.toByteArray()
}

/**
 * Convierte un String codificado en Base64 a una `ImageBitmap`.
 * @return La `ImageBitmap` que representa la imagen de la cadena codificada em Base64.
 */
fun String.base64ToImageBitmap(): ImageBitmap =
    Base64.decode(this, Base64.DEFAULT).let { decodedString ->
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString!!.size)
    }.asImageBitmap()

/**
 * Convierte un String codificado en Base64 a un `ByteArray`.
 * @return El ByteArray que representa la cadena codificada en Base64.
 */
fun String.base64ToByteArray(): ByteArray =
    Base64.decode(this, Base64.DEFAULT)

/**
 * Convierte un ByteArray a un String codificado en Base64.
 * @return El String que representa el ByteArray en Base64.
 */
fun ByteArray.toBase64(): String =
    Base64.encodeToString(this, Base64.DEFAULT)

/**
 * Convierte un ByteArray a una `ImageBitmap`.
 * @return La `ImageBitmap` que representa la imagen del ByteArray.
 */
fun ByteArray.toImageBitmap(): ImageBitmap =
    BitmapFactory.decodeByteArray(this, 0, this.size).asImageBitmap()

/**
 * Carga una `ImageBitmap` desde un recurso de la aplicación Android sin compresión.
 * @param recurso El ID del recurso de la imagen.
 * @return La `ImageBitmap` cargada.
 */
fun Context.imageBitmapFromRerouceId(recurso: Int): ImageBitmap =
    BitmapFactory.decodeResource(this.resources, recurso).asImageBitmap()

/**
 * Carga una `ImageBitmap` desde una Uri, con control de tamaño y formato.
 * @param uri La Uri de la imagen.
 * @param formato El formato de compresión deseado (por defecto, JPG_75).
 * @param tamanoMaxKB El tamaño máximo permitido en kilobytes (por defecto, 512).
 * @return La `ImageBitmap` cargada, o null si ocurre un error.
 */
fun Context.imageBitmapFromUri(
    uri: Uri,
    formato: FormatosCompresion = FormatosCompresion.JPG_75,
    tamanoMaxKB: Int = 512
): ImageBitmap? {
    try {
        val contextResolver = this.contentResolver
        val source = ImageDecoder.createSource(contextResolver, uri)
        var imagenCargada = ImageDecoder.decodeBitmap(source).asImageBitmap()

        val tamanoActualKB = imagenCargada.toBlob(formato).size / BYTES_PER_KB
        if (tamanoActualKB > tamanoMaxKB) {
            val factorRedimension = sqrt(tamanoMaxKB.toFloat() / tamanoActualKB)
            val nuevoLadoLargo = (imagenCargada.height.coerceAtLeast(imagenCargada.width) * factorRedimension).toInt()
            imagenCargada = imagenCargada.resize(nuevoLadoLargo)
        }

        return imagenCargada
    } catch (e: Exception) {
        // Manejo de errores (log, notificación, etc.)
        return null
    }
}