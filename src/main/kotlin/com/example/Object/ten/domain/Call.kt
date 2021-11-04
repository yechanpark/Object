package com.example.Object.ten.domain

import java.time.Duration
import java.time.LocalDateTime

data class Call(
    val from: LocalDateTime,
    val to: LocalDateTime
) {
    fun getDuration() = Duration.between(from, to)
}