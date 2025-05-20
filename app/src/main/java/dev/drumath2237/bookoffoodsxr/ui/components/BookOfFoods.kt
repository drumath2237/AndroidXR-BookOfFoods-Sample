package dev.drumath2237.bookoffoodsxr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.drumath2237.bookoffoodsxr.data.FoodResource
import dev.drumath2237.bookoffoodsxr.data.foodsResources
import dev.drumath2237.bookoffoodsxr.ui.theme.AndroidXRBookOfFoodsTheme

@Composable
fun BookOfFoods() {
    var foodIndex by remember { mutableIntStateOf(0) }
    val food = foodsResources[foodIndex]

    FoodScreen(
        food = food,
        onNextButtonClicked = { foodIndex = (foodIndex + 1) % foodsResources.size }
    )

}

@Composable
fun FoodScreen(
    food: FoodResource,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = food.name, modifier = Modifier.padding(16.dp))

                Image(
                    painter = painterResource(food.imageResourceId),
                    contentDescription = food.name,
                    modifier = Modifier
                        .size(250.dp) // ← ここが幅（高さも同じになる）
                        .clip(RoundedCornerShape(16.dp))
                        .background(color = Color(217, 194, 163, 255))
                )

                Button(onClick = onNextButtonClicked, modifier = Modifier.padding(16.dp)) {
                    Text("Next")
                }
            }
        }
    }
}

@Preview(widthDp = 1024, heightDp = 720)
@Composable
fun BookOfFoodsPreview() {
    AndroidXRBookOfFoodsTheme {
        BookOfFoods()
    }
}