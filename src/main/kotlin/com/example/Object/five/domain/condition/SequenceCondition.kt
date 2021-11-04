package com.example.Object.five.domain.condition

import com.example.Object.five.domain.Screening
import com.example.Object.five.enum.DiscountConditionType

class SequenceCondition(private val sequence: Int): DiscountCondition {
    override fun isSatisfiedBy(screening: Screening): Boolean {
        return sequence == screening.sequence
    }
    override fun getType() = DiscountConditionType.SEQUENCE
}