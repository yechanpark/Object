package com.example.Object.five.logic

import com.example.Object.five.domain.Screening
import com.example.Object.five.enum.DiscountConditionType
import java.time.DayOfWeek
import java.time.LocalTime

class DiscountCondition(
    private val type: DiscountConditionType,
    private val sequence: Int,
    private val dayOfWeek: DayOfWeek,
    private val startTime: LocalTime,
    private val endTime: LocalTime
) {
    // 할인 전략에 따라 할인이 가능한지 확인
    fun isDiscountable(screening: Screening): Boolean {
        if (type == DiscountConditionType.PERIOD) return isSatisfiedByPeriod(screening)
        return isSatisfiedBySequence(screening)
    }

    // 기간 할인 정책을 만족하는지 확인
    private fun isSatisfiedByPeriod(screening: Screening): Boolean {
        return (screening.whenScreened.dayOfWeek == dayOfWeek)
                && (startTime <= screening.whenScreened.toLocalTime())
                && (endTime >= screening.whenScreened.toLocalTime())
    }

    // 순번 할인 정책을 만족하는지 확인
    private fun isSatisfiedBySequence(screening: Screening): Boolean {
        return sequence == screening.sequence
    }

}