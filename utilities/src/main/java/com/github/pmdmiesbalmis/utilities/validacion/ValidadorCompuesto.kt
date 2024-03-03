package com.github.pmdmiesbalmis.utilities.validacion

/**
 * Clase que define un validador compuesto de datos.
 * @param T Tipo de datos a validar.
 * @see Validador
 * @sample com.github.pmdmiesbalmis.utilities.validacion.ValidadorCompuesto
 */
open class ValidadorCompuesto<T> : Validador<T> {
    private val validadores = mutableListOf<Validador<T>>()

    /**
     * Añade un validador a la lista de validadores.
     * @param validador El validador que se añadirá.
     * @return Este validador compuesto.
     */
    fun add(validador: Validador<T>): ValidadorCompuesto<T> {
        validadores.add(validador)
        return this
    }

    /**
     * Valida los datos a partir de los validadores que se hayan añadido.
     * @return La validación que se produzca primero con error, o una validación vacía si no hay errores.
     */
    override fun valida(datos: T): Validacion =
        validadores
            .map { it.valida(datos) }
            .firstOrNull { it.hayError }
            ?: object : Validacion {}
}
