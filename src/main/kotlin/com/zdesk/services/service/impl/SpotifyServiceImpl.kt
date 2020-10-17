package com.zdesk.services.service.impl

import com.zdesk.services.model.TrackDetail
import com.zdesk.services.properties.SpotifyProperties
import com.zdesk.services.service.SpotifyService
import com.zdesk.services.spotify.TrackInfoUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SpotifyServiceImpl : SpotifyService {

    @Autowired
    lateinit var properties: SpotifyProperties

    override fun getTrackInfoFromSpotify(trackId: String): TrackDetail {
        val trackInfoUtil = TrackInfoUtil(properties)
        val token = trackInfoUtil.getSpotifyToken()
        val trackDetails = trackInfoUtil.getTrackInfo(trackId, token.access_token)
        return TrackDetail(trackDetails.name,
                            trackDetails.album.artists.get(0).name,
                            trackDetails.album.images.get(0).url)
    }
}
