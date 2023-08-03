package com.bestswlkh0310.sgx_components.component.organization.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.sgx_components.foundation.Text
import com.bestswlkh0310.sgx_components.theme.Body3
import com.bestswlkh0310.sgx_components.theme.SgxTheme
import com.bestswlkh0310.sgx_components.theme.IcDinner3D
import com.bestswlkh0310.sgx_components.theme.IcOut3D
import com.bestswlkh0310.sgx_components.theme.IcRightArrow
import com.bestswlkh0310.sgx_components.theme.contentColorFor
import com.bestswlkh0310.sgx_components.utlis.SgxDimen

/**
 * Dodam Item Card, set title, subtitle, icon
 *
 * @param title card title
 * @param subTitle card subTitle
 * @param modifier modifier
 * @param onClick action when click card
 * @param icon card icon
 * @param shape card shape, basic is large
 * @param background card background color
 * @param titleStyle title text style
 * @param subTitleStyle subTitle text style
 * @param enable card active state
 */
@Composable
fun SgxItemCard(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    icon: (@Composable () -> Unit)? = null,
    shape: Shape = SgxTheme.shape.large,
    background: Color = SgxTheme.color.White,
    titleStyle: TextStyle = SgxTheme.typography.label1,
    subTitleStyle: TextStyle = SgxTheme.typography.body3,
    enable: Boolean = true,
) {
    Card(
        onClick = if (enable) onClick else null,
        modifier = modifier.width(120.dp),
        shape = shape,
        background = background,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = SgxDimen.CardSidePadding,
                    vertical = SgxDimen.CardSidePadding
                )
                .fillMaxWidth(),
        ) {
            Text(text = subTitle, style = subTitleStyle.copy(color = SgxTheme.color.Gray500))
            Spacer(modifier = Modifier.height(2.dp))
            Text(text = title, style = titleStyle.copy(color = contentColorFor(backgroundColor = background)))
            icon?.let {
                Spacer(modifier = Modifier.height(14.dp))
                Box(modifier = Modifier.align(Alignment.End)) {
                    it()
                }
            }
        }
    }
}

/**
 * Dodam Content Card, set title, contents
 *
 * @param title card title
 * @param modifier modifier
 * @param onClick action when click card
 * @param hasLinkIcon link to other page icon
 * @param shape card shape, basic is large
 * @param background card background color
 * @param titleStyle title text style
 * @param enable card active state
 * @param content composable content in card
 */
@Composable
fun DodamContentCard(
    title: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    hasLinkIcon: Boolean = false,
    shape: Shape = SgxTheme.shape.large,
    background: Color = SgxTheme.color.White,
    titleStyle: TextStyle = SgxTheme.typography.title2,
    enable: Boolean = true,
    content: (@Composable () -> Unit)? = null
) {
    Card(
        onClick = if (enable) onClick else null,
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        background = background,
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = SgxDimen.CardSidePadding,
                    vertical = SgxDimen.CardSidePadding
                )
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = title,
                    style = titleStyle.copy(contentColorFor(backgroundColor = background)),
                    modifier = Modifier.weight(1f)
                )
                if (hasLinkIcon) {
                    Spacer(modifier = Modifier.height(14.dp))
                    IcRightArrow(
                        modifier = Modifier
                            .size(14.dp)
                            .align(Alignment.CenterVertically),
                        tint = SgxTheme.color.Gray400,
                        contentDescription = null
                    )
                }
            }
            content?.let {
                Spacer(modifier = Modifier.height(SgxDimen.CardSidePadding))
                it()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDodamCard() {
    val scroll = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(SgxDimen.ScreenSidePadding)
            .verticalScroll(scroll)
    ) {
        Row {
            SgxItemCard(
                title = "외출 / 외박",
                subTitle = "신청",
            )

            Spacer(modifier = Modifier.width(10.dp))

            SgxItemCard(
                title = "외출 / 외박",
                subTitle = "신청",
                icon = { IcOut3D(contentDescription = null) }
            )

            Spacer(modifier = Modifier.width(10.dp))

            SgxItemCard(
                title = "외출 / 외박",
                subTitle = "신청",
                icon = { IcOut3D(contentDescription = null) },
                onClick = {}
            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        DodamContentCard(title = "오늘의 석식")

        Spacer(modifier = Modifier.height(10.dp))
        DodamContentCard(title = "오늘의 석식", hasLinkIcon = true)

        Spacer(modifier = Modifier.height(10.dp))
        DodamContentCard(title = "오늘의 석식", hasLinkIcon = true) {
            Row(modifier = Modifier.fillMaxWidth()) {
                IcDinner3D(contentDescription = null, modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Body3(text = "오늘은 급식이 없네요!", modifier = Modifier.align(Alignment.CenterVertically))
            }
        }
    }
}
