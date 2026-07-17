package com.orbit.wiki.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Html
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.orbit.wiki.model.WikiSearchResult
import androidx.core.text.HtmlCompat

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(onSearch: (String) -> List<WikiSearchResult>) {
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf(listOf<WikiSearchResult>()) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("MaterialWiki") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { 
                    query = it
                    if (it.isNotBlank()) { results = onSearch(it) }
                },
                label = { Text("Search Wikipedia...") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(results) { result ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = result.title, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            // Simple strip of HTML tags from Wikipedia snippets
                            val cleanSnippet = HtmlCompat.fromHtml(result.snippet, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                            Text(text = cleanSnippet, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}