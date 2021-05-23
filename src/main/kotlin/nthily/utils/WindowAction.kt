package nthily.utils

import androidx.compose.desktop.AppManager
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.svgResource

@Composable
fun windowAction(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = {
            AppManager.focusedWindow?.minimize()
        }){
            Icon(svgResource("minimize.svg"), null)
        }
        IconButton(onClick = {
            AppManager.focusedWindow?.close()
        }){
            Icon(Icons.Filled.Close, null)
        }
    }
}