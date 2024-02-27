package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador


class ValidadorPassword(
    val error: String = "Password débil (mínimo 8 caracteres, 1 mayúscula, 1 minúscula, 1 número y 1 carácter especial)"
) : Validador<String> {
    override fun valida(texto: String): Validacion {
        return object : Validacion {

            // ^                 # comienzo de la cadena
            // (?=.*[0-9])       # un dígito debe ocurrir al menos una vez
            // (?=.*[a-z])       # una letra minúscula debe ocurrir al menos una vez
            // (?=.*[A-Z])       # una letra mayúscula debe ocurrir al menos una vez
            // (?=.*[@#$%^&+=_])  # un carácter especial debe ocurrir al menos una vez
            // (?=\S+$)          # no se permite espacios en blanco en toda la cadena
            // .{8,}             # cualquier cosa, al menos ocho lugares
            // $                 # final de la cadena
            override val hayError: Boolean
                get() = !Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$").matches(texto)

            override val mensajeError: String
                get() = error
        }
    }
}