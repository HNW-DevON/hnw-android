package com.bestswlkh0310.sgx_components.component.organization.calendar.schedule

import com.bestswlkh0310.sgx_components.component.organization.calendar.SgxBasicCalendarCategory
import java.time.LocalDateTime

data class Schedule(
    val title: String,
    val category: SgxBasicCalendarCategory,
    val startDateTime: LocalDateTime,
    val endDateTime: LocalDateTime,
    val location: String? = null,
)
