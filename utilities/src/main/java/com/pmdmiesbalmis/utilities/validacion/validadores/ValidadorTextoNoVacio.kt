package com.pmdmiesbalmis.utilities.validacion.validadores

import com.pmdmiesbalmis.utilities.validacion.Validacion
import com.pmdmiesbalmis.utilities.validacion.Validador

class ValidadorTextoNoVacio(
    val error: String = "El campo no puede estar vacío"
) : Validador<String> {
    override fun valida(texto: String): Validacion {
        return object : Validacion {
            override val hayError: Boolean
                get() = texto.isEmpty()
            override val mensajeError: String
                get() = error
        }
    }
}
