package com.pmdmiesbalmis.ui.features.formejemplo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormEjemploUiState(
    val nombre: String = "",
    val edad: Int = 25,
    val altura: Double = 1.76,
    val correo: String = "",
    val telefono: String = "",
    val clave: String = ""
) : Parcelable
