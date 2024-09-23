package com.pmdmiesbalmis.ui.features.formejemplo

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.utilities.device.*
import com.github.pmdmiesbalmis.utilities.imagetools.base64ToImageBitmap
import com.github.pmdmiesbalmis.utilities.imagetools.imageBitmapFromRerouceId
import com.github.pmdmiesbalmis.utilities.imagetools.resize
import com.github.pmdmiesbalmis.utilities.imagetools.toBase64
import com.pmdmiesbalmis.R

@Composable
fun ImageToolsDemo() {
    val BYTES_PER_KB = 1024
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var ladoLargoPx by remember { mutableStateOf(200) }
    var tamanoImagenKB = remember { derivedStateOf { imageBitmap?.toBase64()?.length?.div(BYTES_PER_KB) ?: 0 } }
    var base64String by remember { 
        mutableStateOf("")
    }

    val launcherSelectorImagenes = registroSelectorDeImagenesConGetContent {
        imageBitmap = it
    }
    val hacerFoto = registroHacerFotoConTakePicture {
        imageBitmap = it
    }
    val hacerFotoIntent = registroHacerFotoConIntent {
        imageBitmap = it
    }

    val seleccionarContacto = registroSelectorTelefonoContacto {
        // Get contact image and display it in imageBitmap


    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageDisplay(imageBitmap)
        Text("Dimensiones: ${imageBitmap?.width ?: 0} x ${imageBitmap?.height ?: 0}")
        Text("Tamaño: ${tamanoImagenKB.value} KB")
        Spacer(modifier = Modifier.height(16.dp))
        LoadImageButton(launcherSelectorImagenes)
        Spacer(modifier = Modifier.height(8.dp))
        LoadImageFromResourceButton(R.drawable.imagen) { imageBitmap = it }
        Spacer(modifier = Modifier.height(8.dp))
        LoadImageFromBase64Button(ImagenBase64.data) { imageBitmap = it }
        Spacer(modifier = Modifier.height(8.dp))
        ConvertToBase64Button(imageBitmap) { base64String = it }
        Text(base64String, maxLines = 4, overflow = TextOverflow.Ellipsis)
        Spacer(modifier = Modifier.height(8.dp))
        TakePhotoButton(hacerFoto)
        Spacer(modifier = Modifier.height(8.dp))
        TakePhotoIntentButton(hacerFotoIntent)
        Spacer(modifier = Modifier.height(8.dp))

        ResizeImageSlider(ladoLargoPx) { ladoLargoPx = it }
        Text("Lado largo: ${ladoLargoPx} px")
        ResizeImageButton(imageBitmap, ladoLargoPx) { imageBitmap = it }

        Spacer(modifier = Modifier.height(8.dp))
        SaveImageButton(imageBitmap)

        Spacer(modifier = Modifier.height(8.dp))
        CallPhoneDemo()
        Spacer(modifier = Modifier.height(8.dp))
        SendEmailDemo()
        Spacer(modifier = Modifier.height(8.dp))
        SelectContactButton(seleccionarContacto)
    }
}

@Composable
fun LoadImageFromResourceButton(resourceId: Int, onImageLoaded: (ImageBitmap?) -> Unit) {
    val context = LocalContext.current

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            val imageBitmap = context.imageBitmapFromRerouceId(resourceId)
            onImageLoaded(imageBitmap)
        }
    ) {
        Text("Load Image from Resource")
    }
}

@Composable
fun LoadImageFromBase64Button(base64String: String, onImageLoaded: (ImageBitmap?) -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            val imageBitmap = base64String.base64ToImageBitmap()
            onImageLoaded(imageBitmap)
        },
        enabled = base64String.isNotEmpty()
    ) {
        Text("Load Image from Base64")
    }
}

@Composable
fun ImageDisplay(imageBitmap: ImageBitmap?) {
    imageBitmap?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }
}

@Composable
fun LoadImageButton(launcherSelectorImagenes: ActivityResultLauncher<String>) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { launcherSelectorImagenes.launch("image/*") }) {
        Text("Load Image from Gallery")
    }
}

@Composable
fun TakePhotoButton(hacerFoto: ActivityResultLauncher<String>) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { hacerFoto.launch(android.Manifest.permission.CAMERA) }) {
        Text("Take Photo (TakePicture)")
    }
}

@Composable
fun TakePhotoIntentButton(hacerFotoIntent: ActivityResultLauncher<String>) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { hacerFotoIntent.launch(android.Manifest.permission.CAMERA) }) {
        Text("Take Photo (Intent)")
    }
}

@Composable
fun SelectContactButton(seleccionarContacto: ActivityResultLauncher<String>) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { seleccionarContacto.launch(android.Manifest.permission.READ_CONTACTS) }) {
        Text("Select Contact")
    }
}

@Composable
fun ResizeImageSlider(nuevoTamano: Int, onValueChange: (Int) -> Unit) {
    Slider(
        value = nuevoTamano.toFloat(),
        onValueChange = { onValueChange(it.toInt()) },
        valueRange = 100f..500f,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ResizeImageButton(
    imageBitmap: ImageBitmap?,
    nuevoTamano: Int,
    onResize: (ImageBitmap?) -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onResize(imageBitmap?.resize(nuevoTamano)) },
        enabled = imageBitmap != null
    ) {
        Text("Redimensionar")
    }
}

@Composable
fun ConvertToBase64Button(imageBitmap: ImageBitmap?, onConvert: (String) -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onConvert(imageBitmap?.toBase64() ?: "") },
        enabled = imageBitmap != null
    ) {
        Text("Convertir a Base64")
    }
}

@Composable
fun SaveImageButton(imageBitmap: ImageBitmap?) {
    val context = LocalContext.current
    val contentResolver = context.contentResolver

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            imageBitmap?.let { bitmap ->
                val imageUri = saveImageToMediaStore(bitmap, contentResolver)
                if (imageUri != null) {
                    Toast.makeText(context, "Image saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
                }
            }
        },
        enabled = imageBitmap != null
    ) {
        Text("Guardar Imagen")
    }
}

private fun saveImageToMediaStore(bitmap: ImageBitmap, contentResolver: ContentResolver): Uri? {
    val imageDetails = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "saved_image.png")
        put(MediaStore.Images.Media.MIME_TYPE, "image/png")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }
    }

    val imageUri =
        contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, imageDetails)
    imageUri?.let {
        contentResolver.openOutputStream(it)?.use { outputStream ->
            bitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            imageDetails.clear()
            imageDetails.put(MediaStore.Images.Media.IS_PENDING, 0)
            contentResolver.update(it, imageDetails, null, null)
        }
    }
    return imageUri
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallPhoneDemo() {
    var showDialog by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf("") }
    val callPhoneLauncher = registroLlamarPorTelefonoIntent(phoneNumber)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showDialog = true }
        ) {
            Text("Call Phone (Dialog)")
        }

        if (showDialog) {
            BasicAlertDialog(
                onDismissRequest = { showDialog = false },
                content = {
                    Surface(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(10.dp),
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = AlertDialogDefaults.TonalElevation
                    ) {
                        Column {
                            TextField(
                                value = phoneNumber,
                                onValueChange = { phoneNumber = it },
                                label = { Text("Enter phone number") },
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row {
                                Button(
                                    onClick = { showDialog = false },
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                ) {
                                    Text("Cancel")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        callPhoneLauncher.launch(android.Manifest.permission.CALL_PHONE)
                                        showDialog = false
                                    },
                                    enabled = phoneNumber.isNotEmpty(),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth()
                                ) {
                                    Text("Call")
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendEmailDemo() {
    var showDialog by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("Sin asunto") }
    var message by remember { mutableStateOf("Sin texto") }
    var forceChooser by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { showDialog = true }) {
            Text("Enviar Email (Diálogo)")
        }

        if (showDialog) {
            BasicAlertDialog(
                onDismissRequest = { showDialog = false },
                content = {
                    Surface(
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .padding(10.dp),
                        shape = MaterialTheme.shapes.large,
                        tonalElevation = AlertDialogDefaults.TonalElevation
                    ) {
                        Column {
                            TextField(
                                value = email,
                                onValueChange = { email = it },
                                label = { Text("Dirección de correo") }
                            )
                            TextField(
                                value = subject,
                                onValueChange = { subject = it },
                                label = { Text("Asunto") }
                            )
                            TextField(
                                value = message,
                                onValueChange = { message = it },
                                label = { Text("Mensaje") }
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = forceChooser,
                                    onCheckedChange = { forceChooser = it }
                                )
                                Text("Forzar selector")
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Row {
                                Button(
                                    onClick = { showDialog = false },
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                ) {
                                    Text("Cancelar")
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = {
                                        context.enviarMail(
                                            correo = email,
                                            asunto = subject,
                                            texto = message,
                                            forzarEleccion = forceChooser
                                        )
                                        showDialog = false
                                    },
                                    enabled = email.isNotEmpty(),
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxWidth(),
                                ) {
                                    Text("Enviar")
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}