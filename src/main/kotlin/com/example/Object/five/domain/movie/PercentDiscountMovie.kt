package com.example.Object.five.domain.movie

import com.example.Object.five.domain.Money
import com.example.Object.five.domain.condition.DiscountCondition
import java.time.Duration

class PercentDiscountMovie(
    private val percent: Double,
    override val title: String,
    override val runningTime: Duration,
    override val fee: Money,
    override val discountConditions: List<DiscountCondition>
): Movie(title, runningTime, fee, discountConditions) {
    override fun calculateDiscountAmount(): Money {
        return fee.times(percent)
    }
}