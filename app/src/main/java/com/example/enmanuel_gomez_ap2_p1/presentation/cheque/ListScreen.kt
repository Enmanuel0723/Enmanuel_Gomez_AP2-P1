package com.example.enmanuel_gomez_ap2_p1.presentation.cheque

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.enmanuel_gomez_ap2_p1.domain.model.Cheque

@Composable
fun ListScreen(
    onNavigateToEdit: (Int?) -> Unit,
    viewModel: ChequeViewModel = hiltViewModel()
) {
    val cheques by viewModel.chequesFiltrados.collectAsStateWithLifecycle()
    var chequeAEliminar by remember { mutableStateOf<Cheque?>(null) }

    chequeAEliminar?.let { cheque ->
        AlertDialog(
            onDismissRequest = { chequeAEliminar = null },
            title = { Text("Confirmar eliminación") },
            text = { Text("¿Eliminar cheque #${cheque.numero} — ${cheque.beneficiario}?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.delete(cheque)
                    chequeAEliminar = null
                }) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { chequeAEliminar = null }) { Text("Cancelar") }
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToEdit(null) }) {
                Icon(Icons.Default.Add, contentDescription = "Nuevo cheque")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Gestión de Cheques",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            OutlinedTextField(
                value = viewModel.filtroNumero,
                onValueChange = { viewModel.filtroNumero = it },
                label = { Text("Filtrar por número") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                singleLine = true
            )

            OutlinedTextField(
                value = viewModel.filtroBeneficiario,
                onValueChange = { viewModel.filtroBeneficiario = it },
                label = { Text("Filtrar por beneficiario") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                singleLine = true
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cheques, key = { it.chequeId ?: 0 }) { cheque ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "# ${cheque.numero}  —  ${cheque.beneficiario}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "${"%.2f".format(cheque.monto)}",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        IconButton(onClick = { onNavigateToEdit(cheque.chequeId) }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = { chequeAEliminar = cheque }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                    HorizontalDivider()
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val totalMonto = cheques.sumOf { it.monto }
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "Total de cheques emitidos: ${cheques.size}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Suma total: ${"%.2f".format(totalMonto)}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
