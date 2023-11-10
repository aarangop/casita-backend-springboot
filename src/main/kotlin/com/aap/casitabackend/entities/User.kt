package com.aap.casitabackend.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
    @Id
    val id: String,
    val name: String,
    val lastName: String,
    val nickname: String,
    val username: String,
    @JsonIgnore
    private val password: String?,
    @JsonIgnore
    val email: String
)
