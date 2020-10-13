package com.zdesk.services.spotify

import com.google.gson.Gson
import com.zdesk.services.model.SpotifyToken
import com.zdesk.services.model.TrackDetail
import java.nio.charset.StandardCharsets
import java.util.ArrayList
import java.util.Base64
import org.apache.http.HttpResponse
import org.apache.http.NameValuePair
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpGet
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils

class TrackInfoUtil() {

    fun getTrackInfoFromSpotify(trackId: String): TrackDetail {
        val token = getSpotifyToken()
        val trackDetails = getTrackInfo(trackId, token.access_token)
        return TrackDetail(trackDetails, trackId)
    }

    fun getSpotifyToken(): SpotifyToken {
        val spotifyTokenUri = "https://accounts.spotify.com/api/token"
        val authStringBase64 = getBase64AuthorizationHeader()
        val httpclient: CloseableHttpClient = HttpClients.createDefault()
        val httpPost: HttpPost = HttpPost(spotifyTokenUri)
        val params: ArrayList<NameValuePair> = ArrayList<NameValuePair>()
        params.add(BasicNameValuePair("grant_type", "client_credentials"))
        httpPost.setEntity(UrlEncodedFormEntity(params))
        httpPost.addHeader("Authorization", "Basic ".plus(authStringBase64))
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded")
        val httpresponse: HttpResponse = httpclient.execute(httpPost)
        val responseBody: String = EntityUtils.toString(httpresponse.getEntity(), StandardCharsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(responseBody, SpotifyToken::class.java)
    }

    fun getBase64AuthorizationHeader(): String {
        val authString = "80a887484bae44fda8d1e66546b21adc:2705fb33625d44b68510802f4069b7e7"
        val encodedString: String = Base64.getEncoder().encodeToString(authString.toByteArray())
        return encodedString
    }

    fun getTrackInfo(trackId: String, accessToken: String): Any {
        val trackUri = "https://api.spotify.com/v1/tracks/".plus(trackId)
        val httpclient: CloseableHttpClient = HttpClients.createDefault()
        val httpPost: HttpGet = HttpGet(trackUri)
        httpPost.addHeader("Authorization", "Bearer ".plus(accessToken))
        val httpresponse: HttpResponse = httpclient.execute(httpPost)
        val responseBody: String = EntityUtils.toString(httpresponse.getEntity(), StandardCharsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(responseBody, Object::class.java)
    }
}
