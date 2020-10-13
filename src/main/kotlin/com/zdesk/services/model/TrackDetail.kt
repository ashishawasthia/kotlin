package com.zdesk.services.model

data class TrackDetail(val spotifyTrack: Any, val name: String)
data class SpotifyToken(val access_token: String, val token_type: String, val expires_in: Int, val scope: String)
