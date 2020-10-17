package com.zdesk.services.controller

import com.zdesk.services.model.TrackDetail
import com.zdesk.services.service.SpotifyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SpotifyTrackController() {
    @Autowired
    lateinit var spotifyService: SpotifyService

    @GetMapping("/track/{trackId}")
    fun getTrackInfo(@PathVariable trackId: String): TrackDetail {
        return spotifyService.getTrackInfoFromSpotify(trackId)
    }
}
