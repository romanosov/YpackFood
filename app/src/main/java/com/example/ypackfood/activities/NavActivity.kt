package com.example.ypackfood.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ypackfood.common.Constants
import com.example.ypackfood.sealedClasses.Screens
import com.example.ypackfood.ui.theme.YpackFoodTheme
import com.example.ypackfood.viewModels.*

class NavActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val signViewModel = ViewModelProvider(this).get(SignInUpViewModel::class.java)
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val offerViewModel = ViewModelProvider(this).get(OfferViewModel::class.java)
        val shoppingCartViewModel = ViewModelProvider(this).get(ShoppingCartViewModel::class.java)
        val orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)
        val favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        val roomViewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        val datastoreViewModel = ViewModelProvider(this).get(DatastoreViewModel::class.java)


        setContent {
            YpackFoodTheme(darkTheme = false) {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screens.SignInUp.route
                ) {
                    composable(route = Screens.Main.route) { MainScreen(navController, mainViewModel) }
                    composable(
                        route = Screens.DetailContent.route,
                        arguments = listOf(
                            navArgument(Constants.NAV_KEY__CONTENT_ID) { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt(Constants.NAV_KEY__CONTENT_ID)?.let { contentId ->
                            DetailContentScreen(navController, detailViewModel, roomViewModel, contentId)
                        }
                    }
                    composable(route = Screens.SignInUp.route) { SignInUpScreen(signViewModel, datastoreViewModel) }
                    composable(
                        route = Screens.Offers.route,
                        arguments = listOf(
                            navArgument(Constants.NAV_KEY__OFFER_ID) { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt(Constants.NAV_KEY__OFFER_ID)?.let { offerId ->
                            OffersScreen(navController, offerViewModel, offerId)
                        }
                    }
                    //composable(route = Screens.ShoppingCart.route) { ShoppingCartScreen(navController, shoppingCartViewModel, roomViewModel) }
                    composable(route = Screens.ShoppingCart.route) { ShoppingCartScreen(navController, shoppingCartViewModel, orderViewModel, roomViewModel) }
                    composable(
                        route = Screens.Order.route,
                        arguments = listOf(
                            navArgument(Constants.NAV_KEY__ORDER_COST) { type = NavType.IntType }
                        )
                    ) { backStackEntry ->
                        backStackEntry.arguments?.getInt(Constants.NAV_KEY__ORDER_COST)?.let { orderId ->
                            OrderScreen(navController, orderViewModel, shoppingCartViewModel, orderId)
                        }
                    }
                    composable(route = Screens.History.route) { HistoryScreen(navController) }
                    composable(route = Screens.Profile.route) { ProfileScreen(navController) }
                    composable(route = Screens.Favorites.route) { FavoritesScreen(navController, favoritesViewModel, roomViewModel) }
                }
            }
        }
    }
}
