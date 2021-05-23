
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
                        0 -> chatWindow()
                        1 -> {
                            Image(imageResource("wall.jpg"), null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
                        }
                        2 -> setting()
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .windowDraggable(),
            contentAlignment = Alignment.TopEnd
        ){
            windowAction()
        }
    }
}


@Composable
fun chatWindow(){
    var text by remember { mutableStateOf("") }

    val myMessage = viewModel.myMessageList.collectAsState().value
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 100.dp, end = 25.dp, start = 25.dp)
                    .weight(1f),
                state = listState
            ){
                items(myMessage.size){ index ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .height(40.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Surface(
                            color = Color(0xFF1E6EFF),
                            shape = RoundedCornerShape(5.dp)
                        ){
                            Text(myMessage[index],modifier = Modifier.padding(10.dp), color = Color.White, maxLines = 500)
                        }
                        Spacer(Modifier.padding(horizontal = 8.dp))
                        Surface(
                            shape = CircleShape
                        ){
                            Image(imageResource("ava.jpg"), null)
                        }
                    }
                    Spacer(Modifier.padding(vertical = 10.dp))
                }
            }
            Row(

                verticalAlignment = Alignment.Bottom
            ){
                OutlinedTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                        .padding(25.dp)
                        .fillMaxWidth()
                        .height(100.dp),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W700,
                        fontSize = 20.sp,

                        fontFamily = FontFamily(
                            Font(
                                resource = "fonts/PingFang Bold.ttf",
                                style = FontStyle.Normal
                            )
                        )
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color(0xFF0079D3),
                        focusedBorderColor = Color(0xFF0079D3)
                    ),
                    trailingIcon = {
                        IconButton(onClick = {
                            myMessage.add(text.replace("\\n", "\n"))
                            text = ""
                            scope.launch(Dispatchers.IO){
                                listState.animateScrollToItem(myMessage.size)
                            }
                        }){
                            Icon(Icons.Filled.Send, null, tint = Color(0xFF0079D3))
                        }
                    },
                    placeholder = {
                        text("说说你的心里话")
                    }
                )
            }
        }
    }
}

