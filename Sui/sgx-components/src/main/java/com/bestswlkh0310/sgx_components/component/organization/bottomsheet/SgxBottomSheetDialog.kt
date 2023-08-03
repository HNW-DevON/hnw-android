package com.bestswlkh0310.sgx_components.component.organization.bottomsheet

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.component.basic.Divider
import com.bestswlkh0310.sgx_components.component.organization.card.SgxItemCard
import com.bestswlkh0310.sgx_components.theme.SgxTheme

/**
 * Bottom Sheet Dialog, can up down motion (use material)
 *
 * @param sheetTopContent composable content placed top in column scope
 * @param sheetBottomContent composable content placed bottom in column scope
 * @param modifier modifier
 * @param sheetElevation elevation, shadow
 * @param sheetShape shape of sheet, 20.dp
 * @param sheetBackgroundColor color of sheet background
 * @param sheetPickHeight height when down motion
 * @param content composable contents out of sheet
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SgxBottomSheetDialog(
    sheetTopContent: @Composable ColumnScope.() -> Unit,
    sheetBottomContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    sheetElevation: Dp = 0.dp,
    sheetShape: Shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
    sheetBackgroundColor: Color = SgxTheme.color.Background,
    sheetPickHeight: Dp = 70.dp,
    content: @Composable (sheetState: BottomSheetState) -> Unit,
) {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                BottomSheetBar()
                sheetTopContent()
            }
            Divider(color = SgxTheme.color.Gray200)
            sheetBottomContent()
        },
        sheetPeekHeight = sheetPickHeight,
        sheetBackgroundColor = sheetBackgroundColor,
        sheetShape = sheetShape,
        sheetElevation = sheetElevation,
    ) {
        content(sheetState)
    }
}

@Composable
fun ColumnScope.BottomSheetBar() {
    Box(
        modifier = Modifier
            .width(26.dp)
            .height(3.dp)
            .background(
                color = SgxTheme.color.Gray200,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .align(Alignment.CenterHorizontally)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
private fun PreviewBottomSheetDialog() {
    val context = LocalContext.current
    SgxBottomSheetDialog(
        sheetTopContent = {
            Spacer(modifier = Modifier.height(18.dp))
        },
        sheetBottomContent = {
            SgxItemCard(title = "TestTitle", subTitle = "title")
        },
    ) { sheetState ->
    }
}
