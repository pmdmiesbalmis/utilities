package com.github.pmdmiesbalmis.utilities.validacion

/**
 * Interfaz que define un validador de datos.
 * @see Validacion
 */
open class ValidacionCompuesta : Validacion {
    private val validaciones = mutableListOf<Validacion>()

    /**
     * Añade una validación a la lista de validaciones.
     * @param validacion La validación que se añadirá.
     * @return Este validador compuesto.
     */
    fun add(validacion: Validacion): ValidacionCompuesta {
        validaciones.add(validacion)
        return this
    }

    /**
     * Valida los datos a partir de las validaciones que se hayan añadido.
     * @return La validación que se produzca primero con error, o una validación vacía si no hay errores.
     */
    override val hayError: Boolean
        get() = validaciones.any { it.hayError }

    /**
     * Obtiene el mensaje de error de la primera validación que haya producido un error.
     * @return El mensaje de error de la primera validación que haya producido un error, o nulo si no hay errores.
     */
    override val mensajeError: String?
        get() = validaciones.firstOrNull { it.hayError }?.mensajeError
}