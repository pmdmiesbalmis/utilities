package com.pmdmiesbalmis.ui.features.formejemplo

import com.github.pmdmiesbalmis.utilities.validacion.Validacion
import com.github.pmdmiesbalmis.utilities.validacion.ValidacionCompuesta

data class ValidacionFormEjemploUiState(
    val validacionNombre: Validacion = object : Validacion {},
    val validacionEdad: Validacion = object : Validacion {},
    val validacionCorreo: Validacion = object : Validacion {},
    val validacionTelefono: Validacion = object : Validacion {},
    val validacionClave: Validacion = object : Validacion {},
) : ValidacionCompuesta() {
    init {
        this.add(validacionNombre)
            .add(validacionEdad)
            .add(validacionCorreo)
            .add(validacionTelefono)
            .add(validacionClave)
    }
}