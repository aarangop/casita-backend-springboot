package com.aap.casitabackend

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.entities.HouseholdMember
import com.aap.casitabackend.entities.User
import com.aap.casitabackend.services.HouseholdMembersService
import com.aap.casitabackend.services.HouseholdsService
import com.aap.casitabackend.services.UsersService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val usersService: UsersService,
    private val householdMembersService: HouseholdMembersService,
    private val householdsService: HouseholdsService,
) {

    @PostConstruct
    fun loadData() {
        // Save seed user
        val muergano = User(
            id = "elmuergano",
            name = "Andrés",
            lastName = "Arango Pérez",
            nickname = "Muergano",
            username = "muergano",
            password = "elmuergano"
        )
        usersService.saveUser(muergano)

        // Save initial household
        var household = Household(
            id = "bluecher3a",
            street = "Blücherstr.",
            houseNumber = "3a",
            zipCode = "38102",
            city = "Braunschweig",
            country = "Germany"
        )
        householdsService.saveHousehold(household)

        // Create householdMember for user `muergano`
        var householdMember = HouseholdMember("muergano_bluecher3a", muergano)
        householdMembersService.saveHouseholdMember(householdMember)

        // Update household's members by adding the household member just created.
        householdsService.addHouseholdMember(household.id, householdMember)
    }
}