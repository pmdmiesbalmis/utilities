package com.pmdmiesbalmis.ui.features.formejemplo

import android.util.Range
import com.github.pmdmiesbalmis.utilities.validacion.Validador
import com.github.pmdmiesbalmis.utilities.validacion.ValidadorCompuesto
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorCorreo
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorLongitudMaximaTexto
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorLongitudMinimaTexto
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorNumeroEntero
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorPassword
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorTelefono
import com.github.pmdmiesbalmis.utilities.validacion.validadores.ValidadorTextoNoVacio
class ValidadorFormEjemploUiState : Validador<FormEjemploUiState>{

    val validadorNombre = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El nombre no puede estar vacío"))
        .add(ValidadorLongitudMinimaTexto(3, "El nombre debe tener al menos 3 caracteres"))
        .add(ValidadorLongitudMaximaTexto(20, "El nombre debe tener como máximo 20 caracteres"))

    val validadorEdad = ValidadorCompuesto<String>()
        .add(ValidadorNumeroEntero(Range(1, 120), "La edad debe estar entre 1 y 120"))

    val validadorCorreo = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El correo no puede estar vacío"))
        .add(ValidadorCorreo("El correo no es válido"))

    val validadorTelefono = ValidadorCompuesto<String>()
        .add(ValidadorTextoNoVacio("El teléfono no puede estar vacío"))
        .add(ValidadorLongitudMinimaTexto(9, "El teléfono debe tener 9 caracteres"))
        .add(ValidadorLongitudMaximaTexto(18, "El teléfono debe tener 18 caracteres"))
        .add(ValidadorTelefono("El teléfono no es válido"))

    val validadorClave = ValidadorPassword()
    override fun valida(datos: FormEjemploUiState): ValidacionFormEjemploUiState {
        val validacionNombre = validadorNombre.valida(datos.nombre)
        val validacionEdad = validadorEdad.valida(datos.edad.toString())
        val validacionCorreo = validadorCorreo.valida(datos.correo)
        val validacionTelefono = validadorTelefono.valida(datos.telefono)
        val validacionClave = validadorClave.valida(datos.clave)

        return ValidacionFormEjemploUiState(
            validacionNombre = validacionNombre,
            validacionEdad = validacionEdad,
            validacionCorreo = validacionCorreo,
            validacionTelefono = validacionTelefono,
            validacionClave = validacionClave
        )
    }
}