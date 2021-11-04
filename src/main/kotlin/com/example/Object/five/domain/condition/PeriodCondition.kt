package com.example.Object.five.domain.condition

import com.example.Object.five.domain.Screening
import com.example.Object.five.enum.DiscountConditionType
import java.time.DayOfWeek
import java.time.LocalTime

class PeriodCondition(
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime,
    private val endTime: LocalTime
): DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean {
        return (dayOfWeek == screening.whenScreened.dayOfWeek)
                && (startTime <= screening.whenScreened.toLocalTime())
                && endTime.isAfter(screening.whenScreened.toLocalTime())
    }
    override fun getType() = DiscountConditionType.PERIOD
}