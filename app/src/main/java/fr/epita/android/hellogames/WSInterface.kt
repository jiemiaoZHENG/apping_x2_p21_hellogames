package fr.epita.android.hellogames

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WSInterface {
    @GET("list")
    fun getlistGames(): Call<List<listGames>>
}
