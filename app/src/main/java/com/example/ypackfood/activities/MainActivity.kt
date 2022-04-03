package com.example.ypackfood.activities

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ypackfood.common.Constants.TOOLBAR_HEIGHT
import com.example.ypackfood.components.*
import com.example.ypackfood.sealedClasses.NetworkResult
import com.example.ypackfood.viewModels.MainViewModel


@Composable
fun MainScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    mainViewModel.listContentStateInit(rememberLazyListState())
    mainViewModel.listCategoryStateInit(rememberLazyListState())
    mainViewModel.scaffoldStateInit(rememberScaffoldState())

    val toolbarHeightPx = with(LocalDensity.current) { TOOLBAR_HEIGHT.roundToPx().toFloat() }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = mainViewModel.toolbarOffsetState + delta
                mainViewModel.setToolbarOffset(newOffset.coerceIn(-toolbarHeightPx, 0f))
                return Offset.Zero
            }
        }
    }

    val x = mainViewModel.contentResp.observeAsState().value

    Scaffold(
        scaffoldState = mainViewModel.scaffoldState,
        drawerContent = { DrawerComponent(navController) },
        content = {
            Box(
                Modifier
                    .fillMaxSize()
                    .nestedScroll(nestedScrollConnection)
            ) {
                if (!mainViewModel.contentResp.value?.data.isNullOrEmpty()) {
                    Log.d("networkAnswer", "Display data")
                    ContentListComponent(navController, mainViewModel)
                    CategoriesRowComponent(mainViewModel)
                }

                when (x) {
                    is NetworkResult.Loading<*> -> {
                        Log.d("networkAnswer", "Loading data")

                        Column {
                            Spacer(modifier = Modifier.height(TOOLBAR_HEIGHT + 15.dp))
                            LoadingBarComponent()
                        }
                    }
                    is NetworkResult.Success<*> -> {
                        Log.d("networkAnswer", "Success data")
                    }
                    is NetworkResult.Error<*> -> {
                        Log.d("networkAnswer", "Error loading data: " + x.message + " ||| " + x.data)
                        ShowErrorComponent(mainViewModel)
                    }
                    null -> TODO()
                }

                ToolBarComponent(mainViewModel)
            }
        }
    )
}