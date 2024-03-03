package com.pmdmiesbalmis.ui.features.formejemplo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.utilities.manejo_errores.InformacionEstadoUiState
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldEmail
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldEntero
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldPassword
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldPhone
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldReal
import com.github.pmdmiesbalmis.utilities.ui.composables.OutlinedTextFieldWithErrorState
import com.github.pmdmiesbalmis.utilities.ui.composables.SnackbarError
import com.pmdmiesbalmis.ui.theme.LibreriaUtilidadesTheme

@Composable
fun FormEjemploScreen() {

    var formEjemploUiState by rememberSaveable {
        mutableStateOf(FormEjemploUiState())
    }
    val validadorFormEjemploUiState = remember {
        ValidadorFormEjemploUiState(
            mensajeErrorGlobal = "Revisa los errores del formulario"
        )
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
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionEdad = validadorFormEjemploUiState.validadorEdad.valida(e.edad.toString())
                )
                formEjemploUiState = formEjemploUiState.copy(edad = e.edad)
            }

            is FormEjemploEvent.OnChangeAltura -> {
                validacionFormEjemploUiState = validacionFormEjemploUiState.copy(
                    validacionAltura = validadorFormEjemploUiState.validadorAltura.valida(e.altura.toString())
                )
                formEjemploUiState = formEjemploUiState.copy(altura = e.altura)
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

    Column (modifier = Modifier.padding(16.dp)) {

        OutlinedTextFieldWithErrorState(
            modifier = Modifier.fillMaxWidth(),
            label = "Nombre",
            textoState = formEjemploUiState.nombre,
            validacionState = validacionFormEjemploUiState.validacionNombre,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeNombre(it)) }
        )

        OutlinedTextFieldEntero(
            modifier = Modifier.fillMaxWidth(),
            label = "Edad",
            valorState = formEjemploUiState.edad,
            validacionState = validacionFormEjemploUiState.validacionEdad,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeEdad(it)) }
        )

        OutlinedTextFieldReal(
            modifier = Modifier.fillMaxWidth(),
            label = "Altura",
            valorState = formEjemploUiState.altura,
            numeroDecimales = 2,
            unidades = "m",
            validacionState = validacionFormEjemploUiState.validacionAltura,
            onValueChange = { onFormEjemploEvent(FormEjemploEvent.OnChangeAltura(it)) }
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

        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        Button(
            onClick = { onFormEjemploEvent(FormEjemploEvent.OnAceptar) }
        ) {
            Text(text = "Aceptar")
        }

        if (informacionEstadoState is InformacionEstadoUiState.Error) {
            Spacer(modifier = Modifier.padding(8.dp))
            SnackbarError(
                mensajeError = informacionEstadoState.mensaje,
                onDismissError =  {
                    (informacionEstadoState as InformacionEstadoUiState.Error).onDismiss()
                    onFormEjemploEvent(FormEjemploEvent.OnDismissError)
                }
            )
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
