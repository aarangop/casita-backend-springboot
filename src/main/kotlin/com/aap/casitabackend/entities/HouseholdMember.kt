package com.aap.casitabackend.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class HouseholdMember(
    @Id
    val id: String,
    @DBRef
    val user: User
)
