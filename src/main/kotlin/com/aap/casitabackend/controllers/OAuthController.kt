package com.aap.casitabackend.controllers

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
class OAuthController(
    private val clientService: OAuth2AuthorizedClientService
) {
    @GetMapping("/login/google")
    fun googleLoginSuccess(authenticationToken: OAuth2AuthenticationToken): RedirectView {
        var client = clientService.loadAuthorizedClient<OAuth2AuthorizedClient>(
            authenticationToken.authorizedClientRegistrationId,
            authenticationToken.name
        )
        return RedirectView("login-success")
    }

}