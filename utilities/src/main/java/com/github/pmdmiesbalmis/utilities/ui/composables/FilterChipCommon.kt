package com.github.pmdmiesbalmis.utilities.ui.composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

/**
 * Un componente que muestra un FilterChip con una etiqueta y un icono.
 *
 * Puedes probarlo mediante el siguiente código:
 *
 * ```
 * @Preview(
 *     showBackground = true,
 * )
 * @Composable
 * private fun FilterChipTest() {
 *     var selected by remember { mutableStateOf(true) }
 *     FilterChipWithIcon(
 *         seleccionadoState = selected,
 *         textoState = "Filtro",
 *         iconState = Icons.Filled.FilterAlt,
 *         onClick = { selected = !selected }
 *     )
 * }
 * ```
 *
 * @param seleccionadoState El estado del FilterChip.
 * @param textoState La etiqueta que se mostrará en el FilterChip.
 * @param iconState El icono que se mostrará en el FilterChip.
 * @param onClick La acción que se ejecutará cuando el FilterChip se haga clic.
 */

@Composable
fun FilterChipWithIcon(
    modifier: Modifier = Modifier,
    seleccionadoState: Boolean = true,
    textoState : String = "Etiqueta",
    iconState : ImageVector? = null,
    onClick : () -> Unit = {}
) {
    FilterChip(
        modifier = modifier.then(Modifier.height(FilterChipDefaults.Height)),
        selected = seleccionadoState,
        onClick = onClick,
        label = { Text(textoState) },
        leadingIcon = {
            if (seleccionadoState) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Icono seleccionado",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            } else {
                iconState?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = "Icono asociado a la etiqueta",
                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                    )
                }
            }
        }
    )
}


@Preview(
    showBackground = true,
)
@Composable
private fun FilterChipTest() {
    var selected by remember { mutableStateOf(true) }
    FilterChipWithIcon(
        seleccionadoState = selected,
        textoState = "Filtro",
        iconState = Icons.Filled.FilterAlt,
        onClick = { selected = !selected }
    )
}