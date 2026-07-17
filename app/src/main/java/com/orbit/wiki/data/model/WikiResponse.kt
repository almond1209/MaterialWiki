package com.orbit.wiki.model

import com.google.gson.annotations.SerializedName

data class WikiSearchResponse(
    @SerializedName("query") val query: WikiQuery?
)

data class WikiQuery(
    @SerializedName("search") val search: List<WikiSearchResult>?
)

data class WikiSearchResult(
    @SerializedName("title") val title: String,
    @SerializedName("snippet") val snippet: String,
    @SerializedName("pageid") val pageId: Int
)