package com.github.pmdmiesbalmis.utilities.validacion.validadores

import android.util.Range
import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de números enteros.
 *
 * @param rango Rango de valores permitidos.
 * @param error Mensaje de error que se mostrará si el valor no es un número entero.
 * @see Validador
 */
class ValidadorNumeroEntero(
    val rango: Range<Int> = Range(0, Int.MAX_VALUE),
    val error: String = "Se espera un valor entero"
) : Validador<String> {

    /**
     * Valida que el texto sea un número entero.
     *
     * @return Una validación con error si el texto no es un número entero, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorNumeroEntero.valida
     */
    override fun valida(datos: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = !datos.matches(Regex("^[+-]?[0-9]+$"))
                        ||
                        !rango.contains(datos.toInt())
            override val mensajeError: String
                get() = error
        }
    }
}