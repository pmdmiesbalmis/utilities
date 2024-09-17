package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de longitud máxima de texto.
 *
 * @param tamañoMaximo Tamaño máximo del texto.
 * @param error Mensaje de error que se mostrará si el texto supera el tamaño máximo.
 * @see Validador
 */
class ValidadorLongitudMaximaTexto(
    val tamañoMaximo: Int,
    val error: String = "El texto debe ser inferior o igual a ${tamañoMaximo}"
) : Validador<String> {

    /**
     * Valida que el texto no supere el tamaño máximo.
     *
     * @return Una validación con error si el texto supera el tamaño máximo, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorLongitudMaximaTexto.valida
     */
    override fun valida(datos: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = datos.length > tamañoMaximo
            override val mensajeError: String
                get() = error
        }
    }
}