package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador

/**
 * Validador de correos electrónicos.
 * Deben empezar por una letra, seguida de cualquier número de caracteres, seguida de un arroba, seguida de cualquier número de caracteres, seguida de un punto, seguida de cualquier número de caracteres.
 *
 * @param error Mensaje de error que se mostrará si el correo no es válido.
 * @see Validador
 * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorCorreo
 */
class ValidadorCorreo(
    val error: String = "Correo no válido"
) : Validador<String> {
    override fun valida(texto: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$").matches(texto)
            override val mensajeError: String
                get() = error
        }
    }
}