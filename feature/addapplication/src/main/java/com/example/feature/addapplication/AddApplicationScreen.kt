package com.example.feature.addapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddApplicationScreen(
    state: AddApplicationUiState,
    onName: (String) -> Unit,
    onPackage: (String) -> Unit,
    onDeveloper: (String) -> Unit,
    onDescription: (String) -> Unit,
    onSave: () -> Unit,
    onDone: (Long) -> Unit,
    onBack: () -> Unit,
) {
    LaunchedEffect(state.savedId) {
        state.savedId?.let(onDone)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Application") },
                navigationIcon = {
                    TextButton(onClick = onBack) { Text("Back") }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = onName,
                label = { Text("App name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.packageName,
                onValueChange = onPackage,
                label = { Text("Package name") },
                supportingText = { Text("Example: com.example.myapp") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.developer,
                onValueChange = onDeveloper,
                label = { Text("Developer") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.description,
                onValueChange = onDescription,
                label = { Text("Description") },
                minLines = 3,
                modifier = Modifier.fillMaxWidth()
            )

            state.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }
        }
    }
}
