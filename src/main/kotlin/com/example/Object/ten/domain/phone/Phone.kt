package com.example.Object.ten.domain.phone

import com.example.Object.five.domain.Money
import com.example.Object.ten.domain.Call
import java.time.Duration

open class Phone(
    protected val amount: Money,
    private val seconds: Duration,
    protected val calls: ArrayList<Call> = arrayListOf()
) {
    fun call(call: Call) = calls.add(call)
    open fun calculateFee(): Money {
        var result = Money.ZERO
        calls.forEach {
            result = result.plus(amount.times(it.getDuration().seconds / seconds.seconds))
        }
        return result
    }
}