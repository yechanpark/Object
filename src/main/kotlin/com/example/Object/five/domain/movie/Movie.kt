package com.example.Object.five.domain.movie

import com.example.Object.five.domain.Money
import com.example.Object.five.domain.Screening
import com.example.Object.five.domain.condition.DiscountCondition
import java.time.Duration

abstract class Movie(
    open val title: String,
    open val runningTime: Duration,
    protected open val fee: Money,
    open val discountConditions: List<DiscountCondition>
) {

    fun calculateMovieFee(screening: Screening): Money {
        if (isDiscountable(screening)) {
            return fee.minus(calculateDiscountAmount())
        }
        return fee
    }

    private fun isDiscountable(screening: Screening): Boolean {
        return discountConditions.stream().anyMatch { it.isSatisfiedBy(screening) }
    }

    protected abstract fun calculateDiscountAmount(): Money
}