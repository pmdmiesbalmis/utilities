package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de teléfono.
 *
 * @param error Mensaje de error que se mostrará si el teléfono no es válido.
 * @see Validador
 */
class ValidadorTelefono(
    val error: String = "Teléfono no válido"
) : Validador<String> {

    /**
     * Valida que el teléfono tenga entre 9 y 18 dígitos.
     *
     * @return Una validación con error si el teléfono no tiene entre 9 y 18 dígitos, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorTelefono.valida
     */
    override fun valida(texto: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = !Regex("^[0-9 ]{9,18}$").matches(texto)
            override val mensajeError: String
                get() = error
        }
    }
}