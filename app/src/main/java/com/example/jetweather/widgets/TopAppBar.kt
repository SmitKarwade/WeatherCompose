package com.example.jetweather.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    //navController: NavController,
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
//    if (showDialog.value) {
//        ShowSettingDropDownMenu(showDialog = showDialog, navController = navController)
//    }

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
