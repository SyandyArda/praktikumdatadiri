package com.example.praktikumdatadiri

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val HeaderPurple = Color(0xFF9333EA)
val BackgroundLavender = Color(0xFFF3E8FF)
val FormWhite = Color.White
val LabelGray = Color.Gray

@Composable
fun ActivitasPertama(modifier: Modifier = Modifier) {
    var namaLengkap by remember { mutableStateOf("") }
    var alamat by remember { mutableStateOf("") }

    val jenisKelaminOptions = listOf("Laki-laki", "Perempuan")
    var selectedJenisKelamin by remember { mutableStateOf("") } // Awalnya kosong

    val statusPerkawinanOptions = listOf("Janda", "Lajang", "Duda")
    var selectedStatusPerkawinan by remember { mutableStateOf("") } // Awalnya kosong
    // 2. Struktur Layout Utama
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundLavender) // Latar belakang utama (lavender muda)


    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(HeaderPurple)
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Formulir Pendaftaran",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        // Konten form akan ditambahkan di sini
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) // Agar bisa di-scroll jika form panjang
            ) {
                // 5. Card Putih untuk menampung field
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = FormWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        // Semua field input (TextField, Radio) akan masuk di sini
                        FormTextField(
                            label = "NAMA LENGKAP",
                            value = namaLengkap,
                            onValueChange = { namaLengkap = it },
                            placeholder = "Isian nama lengkap"
                        )

                        FormRadioGroup(
                            label = "JENIS KELAMIN",
                            options = jenisKelaminOptions,
                            selectedOption = selectedJenisKelamin,
                            onOptionSelected = { selectedJenisKelamin = it }
                        )
                    }
                } // Akhir Card
            }
        }
        @Composable
        fun FormTextField(
            label: String,
            value: String,
            onValueChange: (String) -> Unit,
            placeholder: String
        ) {
            Column {
                Text(
                    text = label,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = LabelGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    label = { Text(placeholder) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
        @Composable
        fun FormRadioGroup(
            label: String,
            options: List<String>,
            selectedOption: String,
            onOptionSelected: (String) -> Unit
        ) {
            Column {
                Text(
                    text = label,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = LabelGray
                )
                // Loop untuk setiap opsi
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOptionSelected(option) } // Membuat seluruh baris bisa diklik
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = { onOptionSelected(option) }
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}