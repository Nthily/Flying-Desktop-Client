package nthily.utils

import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import java.awt.Cursor
import java.awt.event.MouseEvent


@Composable
fun Modifier.windowDraggable(): Modifier {
    val window = LocalAppWindow.current
    return pointerInput(Unit) {
        forEachGesture {
            awaitPointerEventScope {
                val firstEvent = awaitPointerEvent()
                val firstWindowPointer = firstEvent.mouseEvent?.point ?: return@awaitPointerEventScope

                var start = false

                while (true) {
                    val event = awaitPointerEvent()
                    if (!start) {
                        //window.window.cursor = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)
                        start = true
                    }

                    val displayPointer = event.mouseEvent?.locationOnScreen ?: break
                    window.setLocation(
                        (displayPointer.x - firstWindowPointer.x),
                        (displayPointer.y - firstWindowPointer.y),
                    )

                    when (event.mouseEvent?.id) {
                        null, MouseEvent.MOUSE_RELEASED -> {
                            window.window.cursor = Cursor.getDefaultCursor()
                            break
                        }
                    }
                }
            }
        }
    }
}

/*
@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit) =
    clickable(interactionSource = remember(MutableInteractionSource), indication = null, onClick = onClick)

 */