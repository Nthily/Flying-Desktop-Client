package nthily
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.MutableStateFlow



class UiState{

    var category by mutableStateOf(0)

    var myMessageList = MutableStateFlow<MutableList<String>>(mutableListOf())
}