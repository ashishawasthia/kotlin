package com.zdesk.services.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spotify")
class SpotifyProperties {
    lateinit var tokenurl: String
    lateinit var clientid: String
    lateinit var clientsecret: String
    lateinit var granttype: String
}
