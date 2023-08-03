package com.bestswlkh0310.sgx_components.component.organization.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.organization.card.SgxItemCard
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.IcSong

/**
 * Dodam Bottom Sheet
 *
 * @param sheetContent composable sheet contents in column scope
 * @param modifier modifier
 * @param sheetElevation sheet Elevation, shadow
 * @param sheetBackgroundColor color of sheet background
 * @param sheetShape shape of sheet, basic is 20.dp
 * @param content composable content out of sheet
 */
@Composable
fun SgxBottomSheet(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    sheetElevation: Dp = 0.dp,
    sheetBackgroundColor: Color = SgxTheme.color.Background,
    sheetShape: Shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    content: @Composable () -> Unit,
) {
    Box(modifier) {
        BottomSheetStack(body = {
            Column(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = sheetElevation, shape = sheetShape, clip = false)
                    .clip(sheetShape)
            ) {
                Column(
                    Modifier
                        .background(sheetBackgroundColor)
                        .fillMaxWidth()
                ) {
                    sheetContent()
                }
            }
        }
    }
}

@Composable
private fun BottomSheetStack(
    body: @Composable () -> Unit,
    bottomSheet: @Composable () -> Unit,
) {
    Layout(
        content = {
            body()
            bottomSheet()
        }
    ) { measurables, constraints ->
        val placeable = measurables.first().measure(constraints)

        layout(placeable.width, placeable.height) {
            placeable.placeRelative(0, 0)

            val (sheetPlaceable) = measurables.drop(1).map {
                it.measure(constraints.copy(minWidth = 0, minHeight = 0))
            }

            sheetPlaceable.placeRelative(0, constraints.maxHeight - sheetPlaceable.height)
        }
    }
}

@Preview
@Composable
private fun PreviewBottomSheet() {
    SgxBottomSheet(
        sheetContent = { SheetSample() },
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(SgxTheme.color.MainColor400)
        ) {
            IcSong(contentDescription = null)
        }
    }
}

@Composable
private fun SheetSample() {
    Column {
        SgxItemCard(title = "Hello", subTitle = "test")
        SgxItemCard(title = "Hello", subTitle = "test")
        SgxItemCard(title = "Hello", subTitle = "test")
    }
}
