package com.example.Object.five.logic

import com.example.Object.five.domain.Customer
import com.example.Object.five.domain.Money
import com.example.Object.five.domain.Reservation
import com.example.Object.five.domain.Screening
import com.example.Object.five.domain.condition.DiscountCondition
import com.example.Object.five.domain.movie.Movie
import com.example.Object.five.enum.DiscountConditionType
import com.example.Object.five.enum.MovieType
import java.lang.IllegalArgumentException

class ReservationAgency{
    // 예약
    fun reserve(screening: Screening, customer: Customer, audienceCount: Int): Reservation {
        val discountable = checkDiscountable(screening)
        val fee = calculateFee(screening, discountable, audienceCount)
        return createReservation(screening, customer, audienceCount, fee)
    }

    // 할인을 확인
    private fun checkDiscountable(screening: Screening): Boolean {
        return screening.movie.discountConditions.stream().anyMatch{
            isDiscountable(it, screening)
        }
    }

    // 할인 전략에 따라 할인이 가능한지 확인
    private fun isDiscountable(condition: DiscountCondition, screening: Screening): Boolean {
        if (condition.getType() == DiscountConditionType.PERIOD) return isSatisfiedByPeriod(condition, screening)
        return isSatisfiedBySequence(condition, screening)
    }

    // 기간 할인 정책을 만족하는지 확인
    private fun isSatisfiedByPeriod(condition: DiscountCondition, screening: Screening): Boolean {
        return (screening.whenScreened.dayOfWeek == condition.dayOfWeek)
                && (condition.startTime <= screening.whenScreened.toLocalTime())
                && (condition.endTime >= screening.whenScreened.toLocalTime())
    }

    // 순번 할인 정책을 만족하는지 확인
    private fun isSatisfiedBySequence(condition: DiscountCondition, screening: Screening): Boolean {
        return condition.sequence == screening.sequence
    }

    // 요금을 계산
    private fun calculateFee(screening: Screening, discountable: Boolean, audienceCount: Int): Money {
        if (discountable) {
            return screening.movie.fee.minus(calculateDiscountedFee(screening.movie)).times(audienceCount)
        }
        return screening.movie.fee
    }

    // 영화 타입에 따른 할인된 요금 계산
    private fun calculateDiscountedFee(movie: Movie): Money {
        return when (movie.movieType) {
            MovieType.AMOUNT_DISCOUNT  -> calculateAmountDiscountedFee(movie)
            MovieType.PERCENT_DISCOUNT -> calculatePercentDiscountedFee(movie)
            MovieType.NONE_DISCOUNT    -> calculateNoneDiscountedFee(movie)
            else -> throw IllegalArgumentException()
        }
    }

    private fun calculateAmountDiscountedFee(movie: Movie): Money {
        return movie.discountAmount
    }

    private fun calculatePercentDiscountedFee(movie: Movie): Money {
        return movie.fee.times(movie.discountPercent)
    }

    private fun calculateNoneDiscountedFee(movie: Movie): Money {
        return movie.fee
    }

    // 예약 객체 생성
    private fun createReservation(screening: Screening, customer: Customer, audienceCount: Int, fee: Money): Reservation {
        return Reservation(customer, screening, fee, audienceCount)
    }
}