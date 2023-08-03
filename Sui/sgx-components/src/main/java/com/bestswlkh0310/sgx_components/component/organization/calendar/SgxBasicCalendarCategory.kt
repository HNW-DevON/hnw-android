package com.bestswlkh0310.sgx_components.component.organization.calendar

import androidx.compose.ui.graphics.Color

val sgxBasicCalendarCategories = listOf(
    SgxBasicCalendarCategory.FirstGrade(),
    SgxBasicCalendarCategory.SecondGrade(),
    SgxBasicCalendarCategory.ThirdGrade(),
    SgxBasicCalendarCategory.AllGrade(),
    SgxBasicCalendarCategory.Others(),
)

sealed class SgxBasicCalendarCategory(val color: Color, val name: String) {

    class FirstGrade(
        color: Color = Color(0x70F0AC3C),
        name: String = "1학년",
    ) : SgxBasicCalendarCategory(color, name)

    class SecondGrade(
        color: Color = Color(0x7064BAE1),
        name: String = "2학년",
    ) : SgxBasicCalendarCategory(color, name)

    class ThirdGrade(
        color: Color = Color(0x709856DA),
        name: String = "3학년",
    ) : SgxBasicCalendarCategory(color, name)

    class AllGrade(
        color: Color = Color(0x70F09771),
        name: String = "전학년",
    ) : SgxBasicCalendarCategory(color, name)

    class Others(
        color: Color = Color(0x700067BC),
        name: String = "기타",
    ) : SgxBasicCalendarCategory(color, name)
}
