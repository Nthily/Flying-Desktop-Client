package nthily.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.svgResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nthily.viewModel

@ExperimentalMaterialApi
@Composable
fun menu(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .background(Color(0xFF242426))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
            text(
                text = "Flying",
                style = MaterialTheme.typography.caption,
                color = Color.Gray,
                fontFamily = FontFamily(
                    Font("fonts/msyhbd.ttf")
                ),
                modifier = Modifier.padding(15.dp).weight(1f),
                fontWeight = FontWeight.W100
            )
            IconButton(onClick = {

            }){
                Icon(Icons.Filled.Menu, null, tint = Color.Gray)
            }
        }
        ListItem(
            modifier = Modifier.clickable {
                viewModel.category = 2
            }
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Surface(
                    shape = CircleShape,
                    modifier = Modifier.size(40.dp)
                ){
                    Image(imageResource("ava.jpg"), null)
                }
                Spacer(Modifier.padding(horizontal = 5.dp))
                Column {
                    text("Nthily", color = Color.Gray, style =
                    TextStyle(
                        fontSize = 14.sp,
                        letterSpacing = 1.5.sp,
                        fontFamily = FontFamily(
                            Font("fonts/segoeUILight.ttf")
                        ),
                        fontWeight = FontWeight.W900
                    )
                    )
                    text(
                        "nthily@outlook.com",
                        color = Color.Gray,
                        style = TextStyle(
                            fontSize = 14.sp,
                            letterSpacing = 0.5.sp,
                            fontFamily = FontFamily(
                                Font("fonts/segoeUILight.ttf")
                            )
                        ),
                        fontWeight = FontWeight.W100,
                    )
                }
            }
        }
        Spacer(Modifier.padding(vertical = 10.dp))
        ListItem(
            modifier = Modifier.clickable {
                viewModel.category = 0
            }
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(svgResource("chat.svg"), null, modifier = Modifier.padding(end = 12.dp), tint = Color(0xFF8BD3CE))
                text("聊天", color = Color.White, fontSize = 14.sp)
            }
        }

        ListItem(
            modifier = Modifier.clickable {
                viewModel.category = 1
            }
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(svgResource("grade.svg"), null, modifier = Modifier.padding(end = 12.dp), tint = Color(0xFFDFA7B2))
                text("重要", color = Color.White, fontSize = 14.sp)
            }
        }

        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ){
            ListItem(
                modifier = Modifier.clickable {
                    viewModel.category = 2
                }.weight(2f)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(Icons.Filled.Settings, null, modifier = Modifier.padding(end = 12.dp), tint = Color.Gray)
                    text("设置", color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}