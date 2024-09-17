package com.github.pmdmiesbalmis.utilities.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.utilities.manejo_errores.InformacionEstadoUiState
import com.github.pmdmiesbalmis.utilities.ui.icons.Filled

/**
 * Composable que emite un Snackbar con un mensaje de error de duración indefinida con un botón para cerrar el Snackbar.
 *
 * @param mensajeError El mensaje de error que se mostrará.
 * @param onDismissError La acción que se ejecutará al cerrar el Snackbar.
 */
@Composable
fun SnackbarError(
    mensajeError: String,
    onDismissError: () -> Unit = {}
) {
    Snackbar(
        modifier = Modifier.padding(bottom = 16.dp),
        containerColor = MaterialTheme.colorScheme.onErrorContainer,
        contentColor = MaterialTheme.colorScheme.onError,
        dismissAction = {
            IconButton(
                onClick = onDismissError,
            ) {
                Icon(
                    painter = Filled.getCancelIcon(),
                    contentDescription = "Cancelar",
                    modifier = Modifier.size(ButtonDefaults.IconSize),
                )
            }
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = Filled.getErrorIcon(),
                contentDescription = "Error",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = mensajeError)
        }
    }
}

/**
 * Composable que emite un Snackbar con un mensaje de información de duración indefinida.
 *
 * @param mensajeInfo El mensaje de información que se mostrará.
 * @param muestraProgreso Indica si se muestra un CircularProgressIndicator junto al mensaje de información.
 * @see CircularProgressIndicator
 */
@Composable
fun SnackbarInfo(
    mensajeInfo: String,
    muestraProgreso: Boolean = false,
) {
    Snackbar(
        modifier = Modifier.padding(bottom = 16.dp),
        containerColor = MaterialTheme.colorScheme.onSecondaryContainer,
        contentColor = MaterialTheme.colorScheme.onSecondary,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = Filled.getInfoIcon(),
                contentDescription = "Información",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = mensajeInfo)
            if (muestraProgreso) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSecondary,
                        trackColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
    }
}

/**
 * Composable que ejecuta una corrutina para gestionar el estado de un Snackbar que se mostrará en el
 * SnackbarHost del Scaffold. Funciona en combinación con el composable SnackbarCommon.
 * Se debe llamar justo antes de emitir un Scaffold que contenga un SnackbarHost de la siguiente forma:
 *
 * ```
 * val snackbarHostState = remember { SnackbarHostState() }
 *
 * CorrutinaGestionSnackBar(
 *      snackbarHostState = snackbarHostState,
 *      informacionEstado = informacionEstado
 * )
 *
 * Scaffold(
 *      snackbarHost = {
 *           SnackbarHost(hostState = snackbarHostState) {
 *               SnackbarCommon(informacionEstado = informacionEstado)
 *           }
 *      }
 * ) {
 *      ...
 * }
 * ```
 *
 * @param informacionEstado El estado de la información que se mostrará en el Snackbar.
 * @param snackbarHostState El estado del SnackbarHost del Scaffold.
 * @see SnackbarCommon
 */
@Composable
fun CorrutinaGestionSnackBar(
    informacionEstado : InformacionEstadoUiState,
    snackbarHostState: SnackbarHostState
) {
    LaunchedEffect(
        key1 = informacionEstado,
        block = {
            when (informacionEstado) {
                is InformacionEstadoUiState.Informacion -> snackbarHostState.showSnackbar(
                    message = informacionEstado.mensaje,
                    duration = SnackbarDuration.Indefinite
                )

                is InformacionEstadoUiState.Error -> snackbarHostState.showSnackbar(
                    message = informacionEstado.mensaje,
                    duration = SnackbarDuration.Indefinite
                )

                is InformacionEstadoUiState.Oculta -> snackbarHostState.currentSnackbarData?.dismiss()
            }
        }
    )
}

/**
 * Composable que emite un Snackbar peronalizado en el SnackbarHost del Scaffold
 * dependiendo del valor de informacionEstado.
 * Se debe usar en combinación con el composable CorrutinaGestionSnackBar.
 *
 * @param informacionEstado El estado de la información que se mostrará en el Snackbar.
 * @see CorrutinaGestionSnackBar
 */
@Composable
fun SnackbarCommon(
    informacionEstado: InformacionEstadoUiState
) {
    when(informacionEstado) {
        is InformacionEstadoUiState.Informacion -> {
            SnackbarInfo(
                mensajeInfo = informacionEstado.mensaje,
                muestraProgreso = informacionEstado.muestraProgreso
            )
        }
        is InformacionEstadoUiState.Error -> {
            SnackbarError(
                mensajeError = informacionEstado.mensaje,
                onDismissError = informacionEstado.onDismiss
            )
        }
        is InformacionEstadoUiState.Oculta -> {
            // No hay Snackbar en el SnackbarHost del Scaffold
        }
    }
}


@Preview(
    showBackground = true,
)
@Composable
private fun SnackBarErrorPreview() {
    SnackbarError(mensajeError = "Error")
}

@Preview(
    showBackground = true,
)
@Composable
private fun SnackBarInfoPreview() {
    SnackbarInfo(mensajeInfo = "Cargado...", muestraProgreso = true)
}