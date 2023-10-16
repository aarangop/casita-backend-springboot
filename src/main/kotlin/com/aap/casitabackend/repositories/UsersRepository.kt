package com.aap.casitabackend.repositories

import com.aap.casitabackend.entities.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UsersRepository : MongoRepository<User, String>