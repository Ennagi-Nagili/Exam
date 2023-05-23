package com.annaginagili.exam

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("movie/popular")
    fun getMovies(@Query("api_key") key: String): Call<Result>

    @GET("search/movie")
    fun getMovieByName(@Query("api_key") key: String, @Query("query") query: String): Call<Result>

    @GET("genre/movie/list")
    fun getGenres(@Query("api_key") key: String): Call<Genres>

    @GET("movie/upcoming")
    fun getComings(@Query("api_key") key: String): Call<Result>
}