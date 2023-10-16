package com.aap.casitabackend

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.entities.User
import com.aap.casitabackend.repositories.HouseholdMembersRepository
import com.aap.casitabackend.repositories.HouseholdsRepository
import com.aap.casitabackend.repositories.UsersRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val usersRepository: UsersRepository,
    private val householdMembersRepository: HouseholdMembersRepository,
    private val householdsService: HouseholdsService,
    private val householdsRepository: HouseholdsRepository
) {

    @PostConstruct
    fun loadData() {
        // Load seed users
        val muergano = User(
            id = "elmuergano",
            name = "Andrés",
            lastName = "Arango Pérez",
            nickname = "Muergano",
            username = "muergano",
            password = "elmuergano"
        )

        usersRepository.save(muergano)

        // Load initial household
        var household = Household(
            id = "bluecher3a",
            street = "Blücherstr.",
            houseNumber = "3a",
            zipCode = "38102",
            city = "Braunschweig",
            country = "Germany"
        )
        household = householdsRepository.save(household)

        // Create householdMember for user `muergano`
        var householdMember = HouseholdMember("muergano_bluecher3a", muergano)
        householdMembersRepository.save(householdMember)

        householdsService.addHouseholdMember(household.id, householdMember)
    }
}