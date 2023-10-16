package com.aap.casitabackend.repositories

import com.aap.casitabackend.entities.Household
import org.springframework.data.mongodb.repository.MongoRepository

interface HouseholdsRepository : MongoRepository<Household, String>