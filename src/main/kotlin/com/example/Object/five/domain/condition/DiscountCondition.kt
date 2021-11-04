package com.example.Object.five.domain.condition

import com.example.Object.five.domain.Screening
import com.example.Object.five.enum.DiscountConditionType

interface DiscountCondition {
    fun isSatisfiedBy(screening: Screening): Boolean
    fun getType(): DiscountConditionType
}