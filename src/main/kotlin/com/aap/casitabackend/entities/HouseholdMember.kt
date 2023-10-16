package com.aap.casitabackend.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document
data class HouseholdMember(
    @DocumentReference
    val user: User
)
