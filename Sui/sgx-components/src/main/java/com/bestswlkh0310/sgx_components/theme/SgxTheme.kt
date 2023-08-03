package com.bestswlkh0310.sgx_components.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun SgxTheme(
    color: SgxColor = SgxTheme.color,
    typography: SgxTypography = SgxTheme.typography,
    shape: SgxShape = SgxTheme.shape,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColor provides color,
        LocalTypography provides typography,
        LocalShape provides shape,
        content = content
    )
}

object SgxTheme {
    val color: SgxColor
        @Composable
        @ReadOnlyComposable
        get() = LocalColor.current

    val typography: SgxTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shape: SgxShape
        @Composable
        @ReadOnlyComposable
        get() = LocalShape.current
}