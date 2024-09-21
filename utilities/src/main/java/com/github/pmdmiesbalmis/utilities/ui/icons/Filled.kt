package com.github.pmdmiesbalmis.utilities.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.pmdmiesbalmis.R

object Filled {
    @Composable
    fun getAddIcon(): Painter = painterResource(R.drawable.add_24px)

    @Composable
    fun getArrowBackIosIcon(): Painter = painterResource(R.drawable.arrow_back_ios_24px)

    @Composable
    fun getArrowDropDownIcon(): Painter = painterResource(R.drawable.arrow_drop_down_24px)

    @Composable
    fun getCancelIcon(): Painter = painterResource(R.drawable.cancel_24px)

    @Composable
    fun getCheckIcon(): Painter = painterResource(R.drawable.check_24px)

    @Composable
    fun getDeleteIcon(): Painter = painterResource(R.drawable.delete_24px)

    @Composable
    fun getEditIcon(): Painter = painterResource(R.drawable.edit_24px)

    @Composable
    fun getErrorIcon(): Painter = painterResource(R.drawable.error_24px)

    @Composable
    fun getFace2Icon(): Painter = painterResource(R.drawable.face_2_24px)

    @Composable
    fun getFamilyIcon(): Painter = painterResource(R.drawable.family_restroom_24px)

    @Composable
    fun getFilterAltIcon(): Painter = painterResource(R.drawable.filter_alt_24px)

    @Composable
    fun getFilterListIcon(): Painter = painterResource(R.drawable.filter_list_24px)

    @Composable
    fun getFilterListOffIcon(): Painter = painterResource(R.drawable.filter_list_off_24px)

    @Composable
    fun getImageIcon(): Painter = painterResource(R.drawable.image_24px)

    @Composable
    fun getInfoIcon(): Painter = painterResource(R.drawable.info_24px)

    @Composable
    fun getLogoutIcon(): Painter = painterResource(R.drawable.logout_24px)

    @Composable
    fun getMailIcon(): Painter = painterResource(R.drawable.mail_24px)

    @Composable
    fun getMedicalServicesIcon(): Painter = painterResource(R.drawable.medical_services_24px)

    @Composable
    fun getPersonIcon(): Painter = painterResource(R.drawable.person_24px)

    @Composable
    fun getPhoneEnabledIcon(): Painter = painterResource(R.drawable.phone_enabled_24px)

    @Composable
    fun getPhotoCameraIcon(): Painter = painterResource(R.drawable.photo_camera_24px)

    @Composable
    fun getRefreshIcon(): Painter = painterResource(R.drawable.refresh_24px)

    @Composable
    fun getSaveIcon(): Painter = painterResource(R.drawable.save_24px)

    @Composable
    fun getSportsEsportsIcon(): Painter = painterResource(R.drawable.sports_esports_24px)

    @Composable
    fun getVisibilityIcon(): Painter = painterResource(R.drawable.visibility_24px)

    @Composable
    fun getVisibilityOffIcon(): Painter = painterResource(R.drawable.visibility_off_24px)

    @Composable
    fun getWorkIcon(): Painter = painterResource(R.drawable.work_24px)
}


@Composable
@Preview(showBackground = true)
fun IconosPreview() {
    val iconos = listOf(
        Filled.getAddIcon(),
        Filled.getArrowBackIosIcon(),
        Filled.getArrowDropDownIcon(),
        Filled.getCancelIcon(),
        Filled.getCheckIcon(),
        Filled.getDeleteIcon(),
        Filled.getEditIcon(),
        Filled.getErrorIcon(),
        Filled.getFace2Icon(),
        Filled.getFamilyIcon(),
        Filled.getFilterAltIcon(),
        Filled.getFilterListIcon(),
        Filled.getFilterListOffIcon(),
        Filled.getImageIcon(),
        Filled.getInfoIcon(),
        Filled.getLogoutIcon(),
        Filled.getMailIcon(),
        Filled.getMedicalServicesIcon(),
        Filled.getPersonIcon(),
        Filled.getPhoneEnabledIcon(),
        Filled.getPhotoCameraIcon(),
        Filled.getRefreshIcon(),
        Filled.getSaveIcon(),
        Filled.getSportsEsportsIcon(),
        Filled.getVisibilityIcon(),
        Filled.getVisibilityOffIcon(),
        Filled.getWorkIcon()
    )

    Surface {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(iconos) { icono ->
                Card {
                    Image(
                        painter = icono,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}