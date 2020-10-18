package com.zdesk.services

import com.zdesk.services.service.SpotifyService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MicroserviceApplicationTests {

    @Autowired
    lateinit var spotifyService: SpotifyService

    @Test
    fun contextLoads() {
    }

    @Test
    fun testGetTrackInfoFromSpotify() {
        val trackId = "11dFghVXANMlKmJXsNCbNl"
        val trackInfo = spotifyService.getTrackInfoFromSpotify(trackId)
        assert(trackInfo.name == "Cut To The Feeling")
        assert(trackInfo.artist == "Carly Rae Jepsen")
        assert(trackInfo.artworkUrl == "https://i.scdn.co/image/ab67616d0000b2737359994525d219f64872d3b1")
    }
}
