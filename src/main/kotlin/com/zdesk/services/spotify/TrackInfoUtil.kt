package com.zdesk.services.spotify

import com.google.gson.Gson
import com.zdesk.services.model.SpotifyToken
import com.zdesk.services.properties.SpotifyProperties
import com.zdesk.services.spotify.track.SpotifyTrack
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

class TrackInfoUtil(private var spotifyProperties: SpotifyProperties) {

    fun getSpotifyToken(): SpotifyToken {
        val authStringBase64 = getBase64AuthorizationHeader()
        val httpclient: CloseableHttpClient = HttpClients.createDefault()
        val httpPost: HttpPost = HttpPost(spotifyProperties.tokenurl)
        val params: ArrayList<NameValuePair> = ArrayList<NameValuePair>()
        params.add(BasicNameValuePair("grant_type", spotifyProperties.granttype))
        httpPost.setEntity(UrlEncodedFormEntity(params))
        httpPost.addHeader("Authorization", "Basic ".plus(authStringBase64))
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded")
        val httpresponse: HttpResponse = httpclient.execute(httpPost)
        val responseBody: String = EntityUtils.toString(httpresponse.getEntity(), StandardCharsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(responseBody, SpotifyToken::class.java)
    }

    fun getBase64AuthorizationHeader(): String {
        val authString = spotifyProperties.clientid.plus(":").plus(spotifyProperties.clientsecret)
        val encodedString: String = Base64.getEncoder().encodeToString(authString.toByteArray())
        return encodedString
    }

    fun getTrackInfo(trackId: String, accessToken: String): SpotifyTrack {
        val trackUri = spotifyProperties.trackurl.plus(trackId)
        val httpclient: CloseableHttpClient = HttpClients.createDefault()
        val httpPost: HttpGet = HttpGet(trackUri)
        httpPost.addHeader("Authorization", "Bearer ".plus(accessToken))
        val httpresponse: HttpResponse = httpclient.execute(httpPost)
        val responseBody: String = EntityUtils.toString(httpresponse.getEntity(), StandardCharsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(responseBody, SpotifyTrack::class.java)
    }
}
