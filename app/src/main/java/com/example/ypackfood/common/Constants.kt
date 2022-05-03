package com.example.ypackfood.common
import com.example.ypackfood.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

object Constants {
    const val ROOM_FAVORITES = "Favorites" // это название БД
    const val APP_NAME_RUS = "Упак.Еда"
    const val YPACK_ADDRESS = "Старая Станица, парк Лога"
    val TOOLBAR_HEIGHT = 48.dp
    const val START_DELIVERY = 2
    const val END_DELIVERY = 23

    val fontFamily = FontFamily(
        Font(R.font.beauty_font_one)
    )

    const val TIME_ORDER_MESSAGE = "Заказать можно с $START_DELIVERY:00 до $END_DELIVERY:00, минимальное время ожидания 45 минут (установится автоматически при попытке заказа за меньший интервал времени)."

    const val NAV_KEY__CONTENT_ID = "contentId"
    const val NAV_KEY__OFFER_ID = "offerId"
    const val NAV_KEY__ORDER_COST = "orderCost"

    const val DELIVERY_COST = 200 // ₽

    // --- temp
    val pizza = listOf("Пицца1", "Пицца2", "Пицца3")
    val roll = listOf("Ролл1", "Ролл2", "Ролл3")
    val salads = listOf("Салат1", "Салат2", "Салат3")
    val soups =  listOf("Суп1", "Суп2", "Суп3")
    val drink = listOf("Напиток1", "Напиток2", "Напиток3", "Напиток4")
    val mergedList = pizza+roll+salads+soups+drink

    const val BASE_URL = "https://super-food17.herokuapp.com/"
    val baseUrlPictureCategory = "https://sun9-26.userapi.com/impf/c849020/v849020562/12056a/xOiO0cHdtkk.jpg?size=604x604&quality=96&sign=2c11f0e48c64e290d0bde943da845fd6&type=album"
    val baseUrlPictureContent = "https://yesofcorsa.com/wp-content/uploads/2018/09/Chicken-Burger-Desktop-Wallpaper.jpg"
}