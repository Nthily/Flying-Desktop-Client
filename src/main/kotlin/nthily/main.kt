
package nthily

import androidx.compose.animation.Crossfade
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.svgResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.KeyStroke
import androidx.compose.ui.window.Menu
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.MenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import nthily.ui.chatWindow
import nthily.ui.setting
import nthily.utils.menu
import nthily.utils.text
import nthily.utils.windowAction
import nthily.utils.windowDraggable
import java.awt.FontMetrics


val viewModel = UiState()

@ExperimentalMaterialApi
fun main() = Window(
    size = IntSize(1280, 940),
    icon = null,
    undecorated = true
){

    MaterialTheme {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .border(BorderStroke(1.dp, Color.Black))
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ){
                menu()
                Crossfade(
                    targetState = viewModel.category
                ){
                    when(it){
                        0 -> {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .windowDraggable(),
                                    contentAlignment = Alignment.TopEnd
                                ){
                                    windowAction()
                                }
                                chatWindow()
                            }
                        }
                        1 -> {
                            Image(imageResource("wall.jpg"), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                        2 -> setting()
                    }
                }
            }
        }
    }
}