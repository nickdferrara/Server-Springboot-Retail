package com.nickdferrara.retailstore.store.domain

import jakarta.persistence.*
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
@Table(name = "hours_of_operation")
data class HoursOfOperation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Enumerated(EnumType.STRING)
    val dayOfWeek: DayOfWeek,
    
    val openingTime: LocalTime,
    val closingTime: LocalTime
)
