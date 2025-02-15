package com.example.noteappcompose.login

import android.view.ViewTreeObserver
import android.view.WindowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsCompat.*

@Composable

//küçük ekranlı cihazlar için
fun isSmallScreenHeight() : Boolean {
    val conf = LocalConfiguration.current
    return conf.screenHeightDp <= 786
}

@Composable
fun rememberImeState() : State<Boolean>{
    val imeState = remember {
        mutableStateOf(false)
    }
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(Type.ime()) ?: true
            imeState.value = isKeyboardOpen
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}
