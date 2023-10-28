package com.aap.casitabackend.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef

data class Household(
    @Id
    val id: String,
    val street: String,
    val houseNumber: String,
    val zipCode: String,
    val city: String,
    val country: String,
    @DBRef
    var householdMembers: MutableList<User> = mutableListOf()
)