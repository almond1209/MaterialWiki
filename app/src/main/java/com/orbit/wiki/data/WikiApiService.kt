package com.orbit.wiki.data

import com.orbit.wiki.model.WikiSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WikiApiService {
    @GET("w/api.php")
    suspend fun searchArticles(
        @Query("action") action: String = "query",
        @Query("format") format: String = "json",
        @Query("list") list: String = "search",
        @Query("utf8") utf8: Int = 1,
        @Query("srsearch") searchQuery: String
    ): WikiSearchResponse
}