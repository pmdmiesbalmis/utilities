package com.github.pmdmiesbalmis.utilities.validacion.validadores

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.Validador


/**
 * Validador de contraseña.
 * La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.
 *
 * @param error Mensaje de error que se mostrará si la contraseña no es válida.
 * @see Validador
 */
class ValidadorPassword(
    val error: String = "Password débil (mínimo 8 caracteres, 1 mayúscula, 1 minúscula, 1 número y 1 carácter especial)"
) : Validador<String> {

    /**
     * Valida que la contraseña tenga al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial.
     *
     * @return Una validación con error si la contraseña no cumple los requisitos, o una validación vacía si no hay errores.
     * @sample com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorPassword.valida
     */
    override fun valida(datos: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = !Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$").matches(datos)

            override val mensajeError: String
                get() = error
        }
    }
}