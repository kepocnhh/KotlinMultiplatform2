package org.kepocnhh.km

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp

internal class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ComposeView(this)
        setContentView(view)
        view.setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                ) {
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .wrapContentSize(),
                        text = BuildConfig.APPLICATION_ID,
                    )
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .wrapContentSize(),
                        text = "${BuildConfig.VERSION_NAME}-${BuildConfig.VERSION_CODE}",
                    )
                    BasicText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .wrapContentSize(),
                        text = "${BuildConfig.FLAVOR}${BuildConfig.BUILD_TYPE.capitalize()}",
                    )
                }
            }
        }
    }
}
