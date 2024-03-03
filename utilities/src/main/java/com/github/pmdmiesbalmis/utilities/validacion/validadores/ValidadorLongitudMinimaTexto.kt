package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de longitud mínima de texto.
 *
 * @param tamañoMinimo Tamaño mínimo que debe tener el texto.
 * @param error Mensaje de error que se mostrará si el texto no tiene el tamaño mínimo.
 * @see Validador
 */
class ValidadorLongitudMinimaTexto(
    val tamañoMinimo: Int,
    val error: String = "El texto debe mayor o igual a ${tamañoMinimo}"
) : Validador<String> {

    /**
     * Valida que el texto tenga al menos el tamaño mínimo.
     *
     * @return Una validación con error si el texto no tiene al menos el tamaño mínimo, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorLongitudMinimaTexto.valida
     */
    override fun valida(texto: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = texto.length < tamañoMinimo
            override val mensajeError: String
                get() = error
        }
    }
}
