package com.aap.casitabackend.repositories

import com.aap.casitabackend.entities.HouseholdMember
import org.springframework.data.mongodb.repository.MongoRepository

interface HouseholdMembersRepository : MongoRepository<HouseholdMember, String>