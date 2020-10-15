package com.zdesk.services.properties

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SpotifyProperties {
    @Value("\${spotify.tokenurl}")
    lateinit var tokenurl: String
    @Value("\${spotify.trackurl}")
    lateinit var trackurl: String
    @Value("\${spotify.clientid}")
    lateinit var clientid: String
    @Value("\${spotify.clientsecret}")
    lateinit var clientsecret: String
    @Value("\${spotify.granttype}")
    lateinit var granttype: String
}
