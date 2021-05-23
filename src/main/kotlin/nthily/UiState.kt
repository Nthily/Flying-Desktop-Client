package nthily
import androidx.compose.animation.Animatable
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow



class UiState{

    var category by mutableStateOf(0)

    var myMessageList = MutableStateFlow<MutableList<String>>(mutableListOf())

    var menuCategory by mutableStateOf(0)

    var themeBackground = MutableStateFlow(Animatable(Color.White))


}