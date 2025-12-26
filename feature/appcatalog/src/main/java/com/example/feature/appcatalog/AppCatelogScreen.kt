package com.example.feature.appcatalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppItem
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCatalogScreen(
    state: AppCatalogUiState,
    onQueryChange: (String) -> Unit,
    onOpenApp: (Long) -> Unit,
    onAddApp: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Catalog") },
                actions = {
                    TextButton(onClick = onAddApp) { Text("Add") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = state.query,
                onValueChange = onQueryChange,
                label = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            )

            if (state.filteredApps.isEmpty()) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No apps found")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    items(state.filteredApps, key = { it.id }) { app ->
                        AppRow(app = app, onClick = { onOpenApp(app.id) })
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun AppRow(app: AppItem, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedAppIcon(seed = app.iconSeed, name = app.name)
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(app.name, fontWeight = FontWeight.SemiBold)
            Text(app.developer, style = MaterialTheme.typography.bodySmall)
            Text(app.packageName, style = MaterialTheme.typography.bodySmall)
        }
        AssistChip(
            onClick = { /* non-interactive here */ },
            label = { Text(if (app.installed) "Installed" else "Not installed") },
            enabled = false
        )
    }
}
