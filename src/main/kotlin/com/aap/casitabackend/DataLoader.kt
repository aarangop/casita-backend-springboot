package com.aap.casitabackend

import com.aap.casitabackend.entities.Household
import com.aap.casitabackend.entities.User
import com.aap.casitabackend.services.HouseholdsService
import com.aap.casitabackend.services.UsersService
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

@Component
class DataLoader(
    private val usersService: UsersService,
    private val householdsService: HouseholdsService,
) {

    @PostConstruct
    fun loadData() {
        // Save seed user
        val users = listOf(
            User(
                id = "elmuergano",
                name = "Andrés",
                lastName = "Arango Pérez",
                nickname = "Muergano",
                username = "muergano",
                password = "elmuergano",
                email = "elmuergano@casita.com"
            ),
            User(
                id = "muergana",
                name = "Natalia",
                lastName = "Sandoval Goebel",
                nickname = "La Muergana",
                username = "muergana",
                password = "lamuergana",
                email = "lamuergana@casita.com"
            )
        )
        usersService.saveUsers(users)

        // Save initial household
        val household = Household(
            id = "bluecher3a",
            street = "Blücherstr.",
            houseNumber = "3a",
            zipCode = "38102",
            city = "Braunschweig",
            country = "Germany"
        )
        householdsService.saveHousehold(household)

        // Update household's members by adding the household member just created.
        householdsService.updateHouseholdMembers(household.id!!, listOf(users[0].id))
    }
}