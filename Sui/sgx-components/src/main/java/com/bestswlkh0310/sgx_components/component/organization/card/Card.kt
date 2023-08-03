package com.bestswlkh0310.sgx_components.component.organization.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.bestswlkh0310.sgx_components.component.basic.Surface
import com.bestswlkh0310.sgx_components.theme.SgxTheme

/**
 * Sgx basic base card
 *
 * @param modifier modifier
 * @param onClick action when click card
 * @param shape shape of card
 * @param background background color of card
 * @param enable card active state
 * @param content composable contents in card
 */
@Composable
fun Card(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    shape: Shape = SgxTheme.shape.large,
    background: Color = SgxTheme.color.White,
    enable: Boolean = true,
    content: @Composable () -> Unit
) {
    Surface(
        onClick = if (enable) onClick else null,
        modifier = modifier,
        shape = shape,
        color = background,
    ) {
        content()
    }
}
