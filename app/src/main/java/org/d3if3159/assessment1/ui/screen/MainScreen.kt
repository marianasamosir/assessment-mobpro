package org.d3if3159.assessment1.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3159.assessment1.R
import org.d3if3159.assessment1.ui.theme.Assessment1Theme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFFAE367), //latar belakang
                    titleContentColor = Color(0xFF574611) //text judul
                )
            )
        }
    ) {padding ->
            ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {

    val radioOptions = listOf(
        stringResource(id = R.string.tn),
        stringResource(id = R.string.ny),
        stringResource(id = R.string.nn)
    )
    var gelar by remember { mutableStateOf(radioOptions[0]) }

    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(
            text = stringResource(id = R.string.app_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier.padding(top = 6.dp).border(1.dp, Color.Gray, RoundedCornerShape(5.dp))

        ) {
            radioOptions.forEach { text ->
                GelarOption(
                    label = text,
                    isSelected = gelar == text,
                    modifier = Modifier.selectable(
                        selected = gelar == text,
                        onClick = { gelar = text },
                        role = Role.RadioButton
                    )
                        .weight(1f)
                        .padding(13.dp)
                )
            }
        }

    }
}

@Composable
fun GelarOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center //buat Text nya berada di center dalam row
    ){
        RadioButton(selected = isSelected, onClick = null,)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    Assessment1Theme {
        MainScreen()
    }
}