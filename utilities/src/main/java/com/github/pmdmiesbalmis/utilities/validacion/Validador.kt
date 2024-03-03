package com.github.pmdmiesbalmis.utilities.validacion

/**
 * Interfaz que define un validador de datos.
 * @param T Tipo de datos a validar.
 * @see Validacion
 * @sample com.github.pmdmiesbalmis.utilities.validacion.Validador
 */
interface Validador<T> {
    fun valida(datos: T): Validacion
}

