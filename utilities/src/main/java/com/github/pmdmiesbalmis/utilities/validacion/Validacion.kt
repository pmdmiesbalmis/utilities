package com.github.pmdmiesbalmis.utilities.validacion

/**
 * Interfaz que define un validador de datos.
 * @param T Tipo de datos a validar.
 * @see Validador
 * @sample com.github.pmdmiesbalmis.utilities.validacion.Validacion
 */
interface Validacion {
    val hayError: Boolean
        get() = false
    val mensajeError: String?
        get() = null
}