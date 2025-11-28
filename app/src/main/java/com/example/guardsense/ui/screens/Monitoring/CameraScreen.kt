package com.example.guardsense.ui.screens.Monitoring

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun CameraScreen(
    cameraUrl: String,
    modifier: Modifier = Modifier
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    LaunchedEffect(cameraUrl) {
        withContext(Dispatchers.IO) {
            try {
                val url = URL(cameraUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.connect()

                val input: InputStream = connection.inputStream

                while (isActive) {
                    val frame = BitmapFactory.decodeStream(input)
                    if (frame != null) {
                        bitmap = frame
                    }
                }

                input.close()
                connection.disconnect()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    bitmap?.let {
        Image(
            bitmap = it.asImageBitmap(),
            contentDescription = "Camera Stream",
            modifier = modifier
        )
    }
}


