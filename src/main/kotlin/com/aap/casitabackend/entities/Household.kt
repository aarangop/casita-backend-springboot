package com.aap.casitabackend.entities

import org.springframework.data.annotation.Id

data class Household(
    @Id
    val id: String,
    val street: String,
    val houseNumber: String,
    val zipCode: String,
    val city: String,
    val country: String,
    var householdMembers: List<HouseholdMember>? = null
)