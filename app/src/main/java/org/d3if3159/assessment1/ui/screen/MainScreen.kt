package org.d3if3159.assessment1.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Message
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3159.assessment1.R
import org.d3if3159.assessment1.navigation.Screen
import org.d3if3159.assessment1.ui.theme.Assessment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFFAE367), //latar belakang
                    titleContentColor = Color(0xFF574611) //text judul
                ),
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.About.route)}
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = Color(0xFF574611)
                        )
                    }
                }
            )
        }
    ) {padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {

    val superBurgerLabel = stringResource(id = R.string.super_burger)
    val smallBurgerLabel = stringResource(id = R.string.small_burger)

    val ukuranList = listOf(
        superBurgerLabel,
        smallBurgerLabel
    )

    val gelarList = listOf(
        stringResource(id = R.string.tn),
        stringResource(id = R.string.ny),
        stringResource(id = R.string.nn)
    )

    var gelar by rememberSaveable { mutableStateOf(gelarList[0])}

    var nama by rememberSaveable { mutableStateOf("") }
    var namaError by rememberSaveable { mutableStateOf(false) }

    var ukuran by rememberSaveable {  mutableStateOf(ukuranList[0]) }

    val hargaSuperBurger = 50000
    val hargaSmallBurger = 30000

    var hargaOutput by rememberSaveable { mutableStateOf("") }
    var namaOutput by rememberSaveable { mutableStateOf("") }
    var ukuranOutput by rememberSaveable { mutableStateOf("") }

    var cekHarga by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current
    var showBagikan by rememberSaveable { mutableStateOf(false) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(id = R.string.app_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            gelarList.forEach { text ->
                GelarOption(
                    label = text,
                    isSelected = gelar == text,
                    modifier = Modifier
                        .selectable(
                            selected = gelar == text,
                            onClick = { gelar = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text(text = stringResource(id = R.string.nama_pelanggan)) },
            isError = namaError,
            trailingIcon = { IconPicker(isError = namaError, unit = "")},
            supportingText = { ErrorHint(isError = namaError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            ukuranList.forEach { text ->
                UkuranOption(
                    label = text,
                    isSelected = ukuran == text,
                    modifier = Modifier
                        .selectable(
                            selected = ukuran == text,
                            onClick = {
                                ukuran = text
                            },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

        ){
            Button(
                onClick = {
                    namaError = (nama == "" || nama == "0")
                    if (namaError) return@Button

                    cekHarga = true

                    if(nama.isNotBlank()) {
                        val ukuranId = when (ukuran) {
                            superBurgerLabel -> R.string.super_burger
                            smallBurgerLabel -> R.string.small_burger
                            else -> 0
                        }
                        val harga = when (ukuranId) {
                            R.string.super_burger -> hargaSuperBurger
                            R.string.small_burger -> hargaSmallBurger
                            else -> 0
                        }
                        namaOutput = "${gelar} $nama"
                        ukuranOutput= "${ukuran}"
                        hargaOutput = "${harga}"
                    }
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 22.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF866A0E))
            ) {
                Text(text = stringResource(id = R.string.cek_harga))
            }

            Button(
                onClick = {
                    gelar = (gelarList[0])
                    nama = ""
                    ukuran = (ukuranList[0])
                    hargaOutput = ""
                    namaOutput = ""
                    ukuranOutput = ""
                    showBagikan = false
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 35.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF866A0E))
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
        }

        if (cekHarga) {
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = namaOutput,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Start,
            )
            Text(
                text = ukuranOutput,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Start
            )
            Text(
                text = hargaOutput,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Start
            )
            Button(
                onClick = {
                    shareData(
                        context = context,
                        message = context.getString(R.string.bagikan_template, gelar,nama, ukuran, hargaOutput)
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 35.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF866A0E)),
            ) {
                Text(
                    text = stringResource(id = R.string.bagikan)
                )
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String){
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if(isError){
        Text(text = stringResource(id = R.string.textfield_invalid))
    }
}

@Composable
fun GelarOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center //buat Text nya berada di center dalam row
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun UkuranOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp, top = 4.dp)
        )
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Assessment1Theme {
        MainScreen(rememberNavController())
    }
}