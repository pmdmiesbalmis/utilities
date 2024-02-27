package com.pmdmiesbalmis.ui.features.formejemplo

sealed interface FormEjemploEvent {
    data class OnChangeNombre(val nombre: String) : FormEjemploEvent
    data class OnChangeEdad(val edad: String) : FormEjemploEvent
    data class OnChangeCorreo(val correo: String) : FormEjemploEvent
    data class OnChangeTelefono(val telefono: String) : FormEjemploEvent
    data class OnChangeClave(val clave: String) : FormEjemploEvent
    data object OnAceptar : FormEjemploEvent
    data object OnDismissError : FormEjemploEvent
}