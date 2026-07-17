package com.orbit.wiki.data

import com.orbit.wiki.model.WikiSearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WikiRepository(private val apiService: WikiApiService) {

    suspend fun search(query: String): List<WikiSearchResult> {
        return withContext(Dispatchers.IO) {
            try {
                if (query.isBlank()) return@withContext emptyList()
                
                val response = apiService.searchArticles(searchQuery = query)
                response.query?.search ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}