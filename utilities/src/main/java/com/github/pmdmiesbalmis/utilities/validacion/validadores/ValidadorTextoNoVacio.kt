package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de texto no vacío.
 *
 * @param error Mensaje de error que se mostrará si el texto está vacío.
 * @see Validador
 */
class ValidadorTextoNoVacio(
    val error: String = "El campo no puede estar vacío"
) : Validador<String> {

    /**
     * Valida que el texto no esté vacío.
     *
     * @return Una validación con error si el texto está vacío, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorTextoNoVacio.valida
     */
    override fun valida(datos: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = datos.isEmpty()
            override val mensajeError: String
                get() = error
        }
    }
}
