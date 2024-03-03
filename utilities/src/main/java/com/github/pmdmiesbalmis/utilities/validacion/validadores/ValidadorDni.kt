package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador
import java.util.Locale

/**
 * Validador de DNI.
 *
 * @param error Mensaje de error que se mostrará si el DNI no es válido.
 * @see Validador
 */

class ValidadorDni(
    val error: String = "El DNI no es válido"
) : Validador<String> {

    /**
     * Valida que el DNI sea válido.
     *
     * @return Una validación con error si el DNI no es válido, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorDni.valida
     */
    override fun valida(texto: String): Validacion {
        val letras = "TRWAGMYFPDXBNJZSQVHLCKE"
        val dni = texto.uppercase(Locale.getDefault())
        var hayError = dni.length != 9 || !dni.matches(Regex("[0-9]{8}[A-Z]"))
        if (!hayError) {
            val letra = dni.substring(8, 9)
            val index = Integer.parseInt(dni.substring(0, 8)) % 23
            hayError = letras.substring(index, index + 1) != letra
        }
        return object : Validacion {
            override val hayError: Boolean
                get() = hayError
            override val mensajeError: String
                get() = error
        }
    }
}