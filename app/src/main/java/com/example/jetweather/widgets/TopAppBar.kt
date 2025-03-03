package com.example.jetweather.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweather.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    navController: NavController? = null,
    //favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
//    val showIt = remember {
//        mutableStateOf(false)
//    }
//    val context = LocalContext.current
//
    if (showDialog.value) {
        if (navController != null) {
            ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
        }
    }

    TopAppBar(modifier = Modifier.height(100.dp).padding(bottom = 2.dp, top = 20.dp),
        title = {
        Text(modifier = Modifier.padding(top = 10.dp),
            text = title,
            color = MaterialTheme.colorScheme.onSecondary,
            style = TextStyle(fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp))
    },
        actions = {
            if (isMainScreen){
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "search icon",
                        tint = Color.White)

                }
                IconButton(onClick = {
                    showDialog.value = true
                }) {
                    Icon(imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More Icon" ,
                        tint = Color.White)

                }
            }else Box {}

        },
        navigationIcon = {
            if(icon != null) {
                Icon(imageVector = icon, contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })
            }
//            if (isMainScreen) {
//                val isAlreadyFavList = favoriteViewModel
//                    .favList.collectAsState().value.filter { item ->
//                        (item.city == title.split(",")[0])
//                    }
//
//                if (isAlreadyFavList.isNullOrEmpty()) {
//
//                    Icon(imageVector = Icons.Default.Favorite,
//                        contentDescription = "Favorite icon",
//                        modifier = Modifier
//                            .scale(0.9f)
//                            .clickable {
//                                val dataList = title.split(",")
//                                favoriteViewModel.insertFavorite(
//                                    Favorite(
//                                        city = dataList[0], // city name
//                                        country = dataList[1] // country code
//                                    )).run {
//                                    showIt.value = true
//                                }
//                            },
//                        tint = Color.Red.copy(alpha = 0.6f))
//                }else {
//                    showIt.value = false
//                    Box{}
//                }
//
//                ShowToast(context = context, showIt)
//
//            }

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent))
    }

//@Composable
//fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
//    if (showIt.value) {
//        Toast.makeText(context, " Added to Favorites",
//            Toast.LENGTH_SHORT).show()
//    }
//}

@Composable
fun ShowSettingDropDownMenu(showDialog: MutableState<Boolean>,
                            navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    val items = listOf("About", "Favorites", "Settings")
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopEnd)
        .absolutePadding(top = 45.dp, right = 20.dp)) {
        DropdownMenu(expanded = expanded ,
            onDismissRequest = { expanded = false},
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)) {
            items.forEachIndexed { index, text ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        showDialog.value = false
                    },
                    text = { Text(text = text) },
                    leadingIcon = { Icon(imageVector = when (text) {
                        "About" -> Icons.Default.Info
                        "Favorites" -> Icons.Default.FavoriteBorder
                        else -> Icons.Default.Settings
                    }, contentDescription = "about") })
                    }
            }

        }

    }

