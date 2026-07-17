package com.orbit.wiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.rememberCoroutineScope
import com.orbit.wiki.data.WikiApiService
import com.orbit.wiki.ui.screen.SearchScreen
import com.orbit.wiki.ui.theme.MaterialWikiTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    
    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://en.wikipedia.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WikiApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialWikiTheme {
                SearchScreen(onSearch = { query ->
                    // Note: Production code should use a proper ViewModel + LiveData/StateFlow 
                    // Running blocking network for simplicity of setup demonstration
                    runBlocking(Dispatchers.IO) {
                        try {
                            apiService.searchArticles(searchQuery = query).query?.search ?: emptyList()
                        } catch (e: Exception) {
                            emptyList()
                        }
                    }
                })
            }
        }
    }
}