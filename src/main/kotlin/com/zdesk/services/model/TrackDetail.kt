package com.zdesk.services.model

data class TrackDetail(val name: String, val artist: String, val artworkUrl: String)
data class SpotifyToken(val access_token: String, val token_type: String, val expires_in: Int, val scope: String)
