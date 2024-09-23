package com.github.pmdmiesbalmis.utilities.imagetools

import android.graphics.Bitmap

enum class FormatosCompresion {
    JPG_90,
    PNG_90,
    JPG_75,
    PNG_75;

    val info : Pair<Bitmap.CompressFormat, Int>
        get() = when (this) {
            JPG_90 -> Bitmap.CompressFormat.JPEG to 90
            PNG_90 -> Bitmap.CompressFormat.PNG to 90
            JPG_75 -> Bitmap.CompressFormat.JPEG to 75
            PNG_75 -> Bitmap.CompressFormat.PNG to 75
        }
}