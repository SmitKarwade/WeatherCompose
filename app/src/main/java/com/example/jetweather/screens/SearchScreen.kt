package com.example.jetweather.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetweather.R
import com.example.jetweather.widgets.WeatherAppBar

@Composable
fun SearchScreen(navController: NavController){

    val backColor = colorResource(id = R.color.back)
    val backlineColor = colorResource(id = R.color.backline)

    Scaffold(modifier = Modifier.background(Brush.verticalGradient(listOf(backColor, backlineColor)))
        .padding(6.dp),
        topBar = {
        WeatherAppBar(title = "",
            icon = ImageVector.vectorResource(R.drawable.back),
            isMainScreen = false
            ){
            navController.popBackStack()
        }
    },
        containerColor = Color.Transparent) { insidePadding ->
        Box(modifier = Modifier.padding(insidePadding),
            contentAlignment = Alignment.Center){

            SearchBar(onSearch = {
                navController.previousBackStackEntry?.savedStateHandle?.set("location", it)
                navController.popBackStack()
            }
            )

        }
    }
}

@Composable
fun SearchBar(
    onSearch: (String) -> Unit = {}
) {

    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column {
        CommonTextField(
            valueState = searchQueryState,
            placeholder = "Seattle",
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }
}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
        keyboardActions = onAction,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.back),
            cursorColor = Color.Gray,
            focusedIndicatorColor = Color.LightGray,
            unfocusedIndicatorColor = Color.Gray,
            unfocusedContainerColor = colorResource(R.color.back),
            focusedLabelColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedLabelColor = Color.Gray
        )
    )
}


