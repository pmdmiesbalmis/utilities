package com.pmdmiesbalmis.ui.features.formejemplo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.github.pmdmiesbalmis.utilities.manejo_errores.InformacionEstadoUiState
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldPassword
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldPhone
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldWithErrorState
import com.github.pmdmiesbalmis.utilities.ui.composables.SnackbarError
import com.github.pmdmiesbalmis.utilities.ui.composables.TextFieldWithErrorState
import com.pmdmiesbalmis.ui.theme.LibreriaUtilidadesTheme

@Composable
fun FormEjemploScreen() {

    var formEjemploUiState by rememberSaveable {
        mutableStateOf(FormEjemploUiState())
    }
    val validadorFormEjemploUiState = remember {
        ValidadorFormEjemploUiState()
    }
    var validacionFormEjemploUiState by remember {
        mutableStateOf(ValidacionFormEjemploUiState())
    }
    var informacionEstadoState: InformacionEstadoUiState by remember {
        mutableStateOf(InformacionEstadoUiState.Oculta())
    }
    val onFormEjemploEvent: (FormEjemploEvent) -> Unit = { e ->
        when (e) {
            is FormEjemploEvent.OnChangeNombre -> {
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionNombre = validadorFormEjemploUiState.validadorNombre.valida(e.nombre)
                )
                formEjemploUiState = formEjemploUiState.copy(nombre = e.nombre)
            }

            is FormEjemploEvent.OnChangeEdad -> {
                val edad = if (e.edad.isEmpty()) "0" else e.edad
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionEdad = validadorFormEjemploUiState.validadorEdad.valida(edad)
                )
                formEjemploUiState = formEjemploUiState.copy(edad = edad.toInt())
            }

            is FormEjemploEvent.OnChangeCorreo -> {
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionCorreo = validadorFormEjemploUiState.validadorCorreo.valida(e.correo)
                )
                formEjemploUiState = formEjemploUiState.copy(correo = e.correo)
            }

            is FormEjemploEvent.OnChangeTelefono -> {
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionTelefono = validadorFormEjemploUiState.validadorTelefono.valida(e.telefono)
                )
                formEjemploUiState = formEjemploUiState.copy(telefono = e.telefono)
            }

            is FormEjemploEvent.OnChangeClave -> {
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionClave = validadorFormEjemploUiState.validadorClave.valida(e.clave)
                )
                formEjemploUiState = formEjemploUiState.copy(clave = e.clave)
            }

            is FormEjemploEvent.OnDismissError -> {
                validacionFormEjemploUiState = ValidacionFormEjemploUiState()
            }

            is FormEjemploEvent.OnAceptar -> {
                validacionFormEjemploUiState =
                    validadorFormEjemploUiState.valida(formEjemploUiState)
                if (!validacionFormEjemploUiState.hayError) {
                } else {
                    informacionEstadoState = InformacionEstadoUiState.Error(
                        mensaje = validacionFormEjemploUiState.mensajeError!!,
                        onDismiss = { informacionEstadoState = InformacionEstadoUiState.Oculta() }
                    )
                }
            }
        }
    }

    Column {

        OutlinedTextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Nombre",
            textoState = formEjemploUiState.nombre,
            validacionState = validacionFormEjemploUiState.validacionNombre,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeNombre(it)) }
        )

        TextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Edad",
            textoState = formEjemploUiState.edad.toString(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            validacionState = validacionFormEjemploUiState.validacionEdad,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeEdad(it)) }
        )

        OutlinedTextFieldEmail(
            modifier = Modifier.fillMaxWidth(),
            emailState = formEjemploUiState.correo,
            validacionState = validacionFormEjemploUiState.validacionCorreo,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeCorreo(it)) }
        )

        OutlinedTextFieldPhone(
            modifier = Modifier.fillMaxWidth(),
            telefonoState = formEjemploUiState.telefono,
            validacionState = validacionFormEjemploUiState.validacionTelefono,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeTelefono(it)) }
        )

        OutlinedTextFieldPassword(
            modifier = Modifier.fillMaxWidth(),
            passwordState = formEjemploUiState.clave,
            validacionState = validacionFormEjemploUiState.validacionClave,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeClave(it)) }
        )

        if (informacionEstadoState is InformacionEstadoUiState.Error) {
            SnackbarError(
                mensajeError = informacionEstadoState.mensaje,
                onDismissError =  {
                    (informacionEstadoState as InformacionEstadoUiState.Error).onDismiss()
                    onFormEjemploEvent(FormEjemploEvent.OnDismissError)
                }
            )
        }

        Button(
            onClick = { onFormEjemploEvent(FormEjemploEvent.OnAceptar) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Aceptar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormEjemploScreenPreview() {
    LibreriaUtilidadesTheme {
        Surface {
            FormEjemploScreen()
        }
    }
}
