package com.example.feature.applicationdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.domain.model.AppItem
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationDetailScreen(
    state: ApplicationDetailUiState,
    onToggleInstall: () -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App Details") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }
            )
        }
    ) { padding ->
        val app = state.app
        if (app == null) {
            Box(Modifier.padding(padding).fillMaxSize()) {
                Text("App not found", modifier = Modifier.padding(12.dp))
            }
            return@Scaffold
        }

        DetailContent(
            modifier = Modifier.padding(padding),
            app = app,
            onToggleInstall = onToggleInstall
        )
    }
}

@Composable
private fun DetailContent(
    modifier: Modifier,
    app: AppItem,
    onToggleInstall: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(12.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(app.name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
        Text("By ${app.developer}", style = MaterialTheme.typography.bodyMedium)
        Text(app.packageName, style = MaterialTheme.typography.bodySmall)

        HorizontalDivider()

        Text("Description", fontWeight = FontWeight.SemiBold)
        Text(if (app.description.isBlank()) "No description" else app.description)

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onToggleInstall,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (app.installed) "Uninstall" else "Install")
        }
    }
}
