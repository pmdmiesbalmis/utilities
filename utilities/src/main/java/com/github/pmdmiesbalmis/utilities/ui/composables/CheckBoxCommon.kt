package com.github.pmdmiesbalmis.utilities.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Un componente que muestra un checkbox con una etiqueta.
 *
 * Puedes probarlo mediante el siguiente código:
 *
 * ```
 * @Preview(showBackground = true, name = "CheckBoxPreview")
 * @Composable
 * private fun CheckBoxTest() {
 *     var checkedState by remember { mutableStateOf(true) }
 *     Box {
 *         CheckboxWithLabel(
 *             label = "I Love Balmis",
 *             modifier = Modifier
 *                 .padding(12.dp)
 *                 .wrapContentWidth(),
 *             checkedState = checkedState,
 *             onStateChange = { checkedState = it }
 *         )
 *     }
 * }
 * ```
 *
 * @param label La etiqueta que se mostrará junto al checkbox.
 * @param modifier El modificador que se aplicará a este componente.
 * @param checkedState El estado del checkbox.
 * @param enabledState El estado de habilitación del checkbox.
 * @param onStateChange La acción que se ejecutará cuando el estado del checkbox cambie.
 */
@Composable
fun CheckboxWithLabel(
    label: String,
    modifier: Modifier = Modifier,
    checkedState: Boolean,
    enabledState: Boolean = true,
    onStateChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = onStateChange,
            enabled = enabledState,
        )
        Text(
            text = label,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true, name = "CheckBoxPreview")
@Composable
private fun CheckBoxTest() {
    var checkedState by remember { mutableStateOf(true) }
    Box {
        CheckboxWithLabel(
            label = "I Love Balmis",
            modifier = Modifier
                .padding(12.dp)
                .wrapContentWidth(),
            checkedState = checkedState,
            onStateChange = { checkedState = it }
        )
    }
}