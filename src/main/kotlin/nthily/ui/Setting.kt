package nthily.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import nthily.utils.text

@Composable
fun setting(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Surface(
            elevation = 10.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(450.dp)
        ){
            Column(
                modifier = Modifier.padding(15.dp),
            ){
                Surface(
                    shape = CircleShape,
                    modifier = Modifier.size(50.dp)
                ){
                    Image(imageResource("ava.jpg"), null)
                }
                Spacer(Modifier.padding(vertical = 10.dp))
                Row(verticalAlignment = Alignment.CenterVertically){
                    text("名字：Nthily", modifier = Modifier.weight(1f))
                    IconButton(onClick = {

                    }){
                        Icon(Icons.Filled.Edit, null)
                    }
                }
                Spacer(Modifier.padding(vertical = 10.dp))
                Row(verticalAlignment = Alignment.CenterVertically){
                    text("邮箱：nthily@outlook.com", modifier = Modifier.weight(1f))
                    IconButton(onClick = {

                    }){
                        Icon(Icons.Filled.Edit, null)
                    }
                }
            }
        }
    }
}