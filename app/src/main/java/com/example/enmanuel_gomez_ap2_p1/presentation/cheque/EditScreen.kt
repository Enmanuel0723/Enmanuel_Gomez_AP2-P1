package com.example.enmanuel_gomez_ap2_p1.presentation.cheque

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditScreen(
    chequeId: Int?,
    onNavigateBack: () -> Unit,
    viewModel: ChequeViewModel = hiltViewModel()
) {
    LaunchedEffect(chequeId) {
        viewModel.loadCheque(chequeId)
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = if (chequeId == null) "Nuevo Cheque" else "Editar Cheque",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = viewModel.numero,
                onValueChange = { viewModel.numero = it },
                label = { Text("Número de cheque *") },
                modifier = Modifier.fillMaxWidth(),
                isError = viewModel.numeroError != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            if (viewModel.numeroError != null) {
                Text(
                    text = viewModel.numeroError!!,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.beneficiario,
                onValueChange = { viewModel.beneficiario = it },
                label = { Text("Beneficiario *") },
                modifier = Modifier.fillMaxWidth(),
                isError = viewModel.beneficiarioError != null,
                singleLine = true
            )
            if (viewModel.beneficiarioError != null) {
                Text(
                    text = viewModel.beneficiarioError!!,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = viewModel.monto,
                onValueChange = { viewModel.monto = it },
                label = { Text("Monto *") },
                modifier = Modifier.fillMaxWidth(),
                isError = viewModel.montoError != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            if (viewModel.montoError != null) {
                Text(
                    text = viewModel.montoError!!,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { viewModel.save(onSuccess = onNavigateBack) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Guardar")
                }
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}
