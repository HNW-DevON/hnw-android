package com.bestswlkh0310.sgx_components.component.set.tab

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bestswlkh0310.sgx_components.component.basic.Divider
import com.bestswlkh0310.sgx_components.component.basic.button.ButtonType
import com.bestswlkh0310.sgx_components.foundation.Text
import com.bestswlkh0310.sgx_components.theme.SgxColor
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.LocalContentColor

sealed interface DividerPosition {
    object Top : DividerPosition
    object Bottom : DividerPosition
    object Disable : DividerPosition
}

/**
 * Dodam top tab, just single tab, can use DodamTabs
 *
 * @param text name
 * @param selected state of tab
 * @param onClick action when click tab
 * @param modifier modifier
 * @param showLabel visible label
 * @param icon main icon
 * @param selectedColor color when select
 * @param interactionSource interactionSource
 */
@Composable
fun RowScope.SgxTab(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    showLabel: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    dividerPosition: DividerPosition = DividerPosition.Bottom,
    selectedColor: Color = SgxTheme.color.MainColor400,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {

    val contentColor = if (selected) {
        selectedColor
    } else {
        SgxTheme.color.Gray400
    }

    val selectedStyle = SgxTheme.typography.body1

    val unSelectStyle = SgxTheme.typography.body1.copy(
        fontWeight = FontWeight.Normal
    )

    CompositionLocalProvider(
        LocalContentColor provides contentColor
    ) {
        Column(
            modifier = modifier
                .selectable(
                    selected = selected,
                    onClick = onClick,
                    enabled = true,
                    role = Role.Tab,
                    interactionSource = interactionSource,
                    indication = rememberRipple(
                        color = SgxTheme.color.Gray300,
                        radius = 70.dp,
                        bounded = true
                    ),
                )
        ) {
            if (selected && dividerPosition == DividerPosition.Top) {
                Divider(
                    color = SgxColor.MainColor,
                    thickness = 2.dp,
                )
            }
            if (showLabel && (icon == null))
                LabelOnly(
                    text = text,
                    selected = selected,
                    selectedStyle = selectedStyle,
                    unSelectStyle = unSelectStyle
                )
            else if (!showLabel && (icon != null))
                IconOnly(icon = icon)
            else if (showLabel && icon != null)
                IconLabel(
                    icon = icon,
                    text = text,
                    selected = selected,
                    selectedStyle = selectedStyle,
                    unSelectStyle = unSelectStyle
                )
            if (selected && dividerPosition == DividerPosition.Bottom) {
                Divider(
                    color = SgxColor.MainColor,
                    thickness = 2.dp,
                )
            }
        }
    }
}

@Composable
private fun LabelOnly(
    text: String,
    selected: Boolean,
    selectedStyle: TextStyle,
    unSelectStyle: TextStyle,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 14.dp)
                .align(Alignment.CenterHorizontally),
            text = text,
            style = if (selected) selectedStyle else unSelectStyle,
        )
    }
}

@Composable
private fun IconOnly(
    icon: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 13.dp)
                .size(24.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            icon()
        }
    }
}

@Composable
private fun IconLabel(
    icon: @Composable () -> Unit,
    text: String,
    selected: Boolean,
    selectedStyle: TextStyle,
    unSelectStyle: TextStyle,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(7.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterHorizontally),
            ) {
                icon()
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                text = text,
                style =
                if (selected)
                    selectedStyle.copy(fontSize = 18.sp)
                else
                    unSelectStyle.copy(fontSize = 18.sp),
            )
        }
    }
}
