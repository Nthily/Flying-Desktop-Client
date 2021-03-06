package nthily.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nthily.utils.text
import nthily.viewModel


@Composable
fun chatWindow(){

    var text by remember { mutableStateOf("") }

    val myMessage = viewModel.myMessageList.collectAsState().value
    val background = viewModel.themeBackground.collectAsState().value.value
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ){
            LazyColumn(
                modifier = Modifier
                    .padding(end = 25.dp, start = 25.dp)
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
                        text("?????????????????????")
                    }
                )
            }
        }
    }
}

