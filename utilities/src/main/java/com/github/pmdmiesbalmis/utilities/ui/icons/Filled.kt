package com.github.pmdmiesbalmis.utilities.ui.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.github.pmdmiesbalmis.R

object Filled {
    @Composable
    fun getVisibilityIcon(): Painter = painterResource(R.drawable.visibility_24px)
    @Composable
    fun getVisibilityOffIcon(): Painter = painterResource(R.drawable.visibility_off_24px)
    @Composable
    fun getCheckIcon(): Painter = painterResource(R.drawable.check_24px)
    @Composable
    fun getErrorIcon(): Painter = painterResource(R.drawable.error_24px)
    @Composable
    fun getCancelIcon(): Painter = painterResource(R.drawable.cancel_24px)
    @Composable
    fun getFilterAltIcon(): Painter = painterResource(R.drawable.filter_alt_24px)
    @Composable
    fun getPhoneEnabledIcon(): Painter = painterResource(R.drawable.phone_enabled_24px)
    @Composable
    fun getMailIcon(): Painter = painterResource(R.drawable.mail_24px)
    @Composable
    fun getPersonIcon(): Painter = painterResource(R.drawable.person_24px)
    @Composable
    fun getInfoIcon(): Painter = painterResource(R.drawable.info_24px)
}