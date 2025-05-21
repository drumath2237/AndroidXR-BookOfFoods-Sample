package dev.drumath2237.bookoffoodsxr

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.drumath2237.bookoffoodsxr.ui.components.BookOfFoods
import dev.drumath2237.bookoffoodsxr.ui.theme.AndroidXRBookOfFoodsTheme

class MainActivity : ComponentActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AndroidXRBookOfFoodsTheme {
                BookOfFoods()
            }
        }
    }
}