package com.github.pmdmiesbalmis.utilities.validacion.validadores

import android.util.Range
import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de números reales.
 *
 * @param rango Rango de valores permitidos.
 * @param error Mensaje de error que se mostrará si el valor no es un número real.
 * @see Validador
 */
class ValidadorNumeroReal(
    val rango: Range<Double> = Range(0.0, Double.MAX_VALUE),
    val error: String = "Se espera un valor real"
) : Validador<String> {

    /**
     * Valida que el texto sea un número real.
     *
     * @return Una validación con error si el texto no es un número real, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorNumeroReal.valida
     */
    override fun valida(texto: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = !texto.matches(Regex("^[0-9]+(\\.[0-9]+)?$"))
                        ||
                        !rango.contains(texto.toDouble())
            override val mensajeError: String
                get() = error
        }
    }
}