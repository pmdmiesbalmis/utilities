package com.github.pmdmiesbalmis.utilities.ui.composables

import android.telephony.PhoneNumberUtils
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.github.pmdmiesbalmis.utilities.ui.icons.Filled
import com.github.pmdmiesbalmis.utilities.validacion.Validacion

@Composable
fun TextFieldWithErrorState(
    modifier: Modifier = Modifier,
    label: String,
    textoState: String,
    textoPista: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    validacionState: Validacion,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier,
        value = textoState,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (validacionState.hayError) {
                Icon(
                    painter = Filled.getErrorIcon(),
                    contentDescription = "Error"
                )
            }
        },
        placeholder = {
            Text(
                text = textoPista,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        keyboardActions = keyboardActions
    )
}

@Composable
fun OutlinedTextFieldWithErrorState(
    modifier: Modifier = Modifier,
    label: String,
    textoState: String,
    textoPista: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    validacionState: Validacion,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = textoState,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = leadingIcon,
        trailingIcon = {
            if (validacionState.hayError) {
                Icon(
                    painter = Filled.getErrorIcon(),
                    contentDescription = "Error"
                )
            }
        },
        placeholder = {
            Text(
                text = textoPista,
                style = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f))
            )
        },
        label = { Text(label) },
        keyboardOptions = keyboardOptions,
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        keyboardActions = keyboardActions
    )
}

@Composable
fun TextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit,
    label: String = "Clave",
    labelShow: String = "Muestra clave",
    labelHide: String = "Oculta clave"
) {
    var passwordHidden by remember { mutableStateOf(true) }
    TextField(
        modifier = modifier,
        value = passwordState,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(label) },
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Filled.getVisibilityIcon()
                    else Filled.getVisibilityOffIcon()
                val description = if (passwordHidden) labelShow else labelHide
                Icon(painter = visibilityIcon, contentDescription = description)
            }
        },
        trailingIcon = {
            if (validacionState.hayError) {
                Icon(
                    painter = Filled.getErrorIcon(),
                    contentDescription = "Error"
                )
            }
        }
    )
}

@Composable
fun OutlinedTextFieldPassword(
    modifier: Modifier = Modifier,
    passwordState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit,
    label: String = "Clave",
    labelShow: String = "Muestra clave",
    labelHide: String = "Oculta clave"
) {
    var passwordHidden by remember { mutableStateOf(true) }
    OutlinedTextField(
        modifier = modifier,
        value = passwordState,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(label) },
        supportingText = {
            if (validacionState.hayError) {
                Text(text = validacionState.mensajeError!!)
            }
        },
        isError = validacionState.hayError,
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Filled.getVisibilityIcon()
                    else Filled.getVisibilityOffIcon()
                val description = if (passwordHidden) labelShow else labelHide
                Icon(painter = visibilityIcon, contentDescription = description)
            }
        },
        trailingIcon = {
            if (validacionState.hayError) {
                Icon(
                    painter = Filled.getErrorIcon(),
                    contentDescription = "Error"
                )
            }
        }
    )
}

@Composable
fun TextFieldPhone(
    modifier: Modifier = Modifier,
    label: String = "Teléfono",
    telefonoState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    TextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = telefonoState,
        textoPista = "999 99 99 99",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                painter = Filled.getPhoneEnabledIcon(),
                contentDescription = "Teléfono"
            )
        },
        validacionState = validacionState,
        onValueChange = {
            var text = it
            if (!validacionState.hayError) {
                try {
                    text = PhoneNumberUtils.formatNumber(it, "ES")
                } catch (e: Exception) {

                }
            }
            onValueChange(text)
        }
    )
}

@Composable
fun OutlinedTextFieldPhone(
    modifier: Modifier = Modifier,
    label: String = "Teléfono",
    telefonoState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = telefonoState,
        textoPista = "999999999",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        leadingIcon = {
            Icon(
                painter = Filled.getPhoneEnabledIcon(),
                contentDescription = "Teléfono"
            )
        },
        validacionState = validacionState,
        onValueChange = {
            var text = it
            if (!validacionState.hayError) {
                try {
                    text = PhoneNumberUtils.formatNumber(it, "ES")
                } catch (e: Exception) {

                }
            }
            onValueChange(text)
        }
    )
}

@Composable
fun TextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {

    TextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = emailState,
        textoPista = "ejemplo@correo.com",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                painter = Filled.getMailIcon(),
                contentDescription = "Email"
            )
        },
        validacionState = validacionState,
        onValueChange = { onValueChange(it) }
    )
}

@Composable
fun OutlinedTextFieldEmail(
    modifier: Modifier = Modifier,
    label: String = "Email",
    emailState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = emailState,
        textoPista = "ejemplo@correo.com",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                painter = Filled.getMailIcon(),
                contentDescription = "Email"
            )
        },
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldName(
    modifier: Modifier = Modifier,
    label: String = "Nombre",
    nameState: String,
    validacionState: Validacion,
    onValueChange: (String) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = nameState,
        textoPista = "Juan Perez",
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = {
            Icon(
                painter = Filled.getPersonIcon(),
                contentDescription = "Nombre"
            )
        },
        validacionState = validacionState,
        onValueChange = onValueChange
    )
}

@Composable
fun OutlinedTextFieldEntero(
    modifier: Modifier = Modifier,
    label: String,
    unidades: String? = null,
    valorState: Int,
    textoPista: String = "Introduce un valor entero",
    validacionState: Validacion,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (Int) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = valorState.toString(),
        textoPista = textoPista,
        leadingIcon = unidades?.let {
            @Composable {
                TextoUnidades(unidades = it)
            }
        },
        validacionState = validacionState,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = keyboardActions,
        onValueChange = {
            if (it.isNotEmpty()) {
                if (it.matches(Regex("^[+-]?[0-9]+$")))
                    onValueChange(it.toInt())
            } else {
                onValueChange(0)
            }
        }
    )
}

@Composable
private fun TextoUnidades(unidades: String) {
    Text(
        text = unidades,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun OutlinedTextFieldReal(
    modifier: Modifier = Modifier,
    label: String,
    valorState: Double,
    numeroDecimales: Int = 1,
    unidades: String? = null,
    textoPista: String = "Introduce un valor real",
    validacionState: Validacion,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (Double) -> Unit
) {
    OutlinedTextFieldWithErrorState(
        modifier = modifier,
        label = label,
        textoState = "%.${numeroDecimales}f".format(valorState),
        textoPista = textoPista,
        leadingIcon = unidades?.let {
            @Composable {
                TextoUnidades(unidades = it)
            }
        },
        validacionState = validacionState,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        keyboardActions = keyboardActions,
        onValueChange = {
            if (it.isNotEmpty()) {
                if (it.matches(Regex("^[+-]?[0-9]+(\\.[0-9]+)?$")))
                    onValueChange(it.toDouble())
            } else {
                onValueChange(0.0)
            }
        }
    )
}

