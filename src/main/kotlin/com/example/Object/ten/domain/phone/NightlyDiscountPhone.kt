package com.example.Object.ten.domain.phone

import com.example.Object.five.domain.Money
import java.time.Duration
import java.util.*

class NightlyDiscountPhone(
    private val nightlyAmount: Money,
    private val regularAmount: Money,
    private val seconds: Duration
): Phone(regularAmount, seconds) {
    private val LATE_NIGHT_HOUR: Int = 22

    override fun calculateFee(): Money {
        Stack
        val result = super.calculateFee()

        var nightlyFee = Money.ZERO

        calls.forEach {
            if (it.from.hour >= LATE_NIGHT_HOUR) {
                nightlyFee = nightlyFee.plus(
                    super.amount.minus(nightlyAmount).times(
                        it.getDuration().seconds / seconds.seconds
                    )
                )
            }
        }
        return result.minus(nightlyFee)
    }
}