package com.zdesk.services.service

import com.zdesk.services.model.TrackDetail

interface SpotifyService {
    fun getTrackInfoFromSpotify(trackId: String): TrackDetail
}
