package com.pmdmiesbalmis.utilities.validacion

interface Validacion {
    val hayError: Boolean
        get() = false
    val mensajeError: String?
        get() = null
}