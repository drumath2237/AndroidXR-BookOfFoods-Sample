package dev.drumath2237.bookoffoodsxr.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import dev.drumath2237.bookoffoodsxr.R
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
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,
    onOpenInFullButtonClicked: () -> Unit = {},
) {
    Surface {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Box(modifier = Modifier.padding(8.dp)) {
                    Image(
                        painter = painterResource(food.imageResourceId),
                        contentDescription = food.name,
                        modifier = Modifier
                            .size(480.dp)
                            .clip(RoundedCornerShape(32.dp))
                            .background(color = Color(217, 194, 163, 255))
                    )

                    IconButton(
                        onClick = onOpenInFullButtonClicked,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)

                    ) {
                        Icon(
                            painter = painterResource(R.drawable.outline_open_in_full_24),
                            contentDescription = "open in full",
                            tint = Color.White,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(32.dp))
                                .background(color = Color(0xFFB09257))
                                .padding(8.dp)

                        )
                    }
                }

                Column {
                    Text(
                        text = food.name,
                        fontSize = TextUnit(45f, TextUnitType.Sp),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Text(
                        food.detail,
                        fontSize = TextUnit(22f, TextUnitType.Sp),
                        modifier = Modifier.padding(vertical = 16.dp)
                    )

                    Button(
                        onClick = onNextButtonClicked,
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .width(120.dp)
                            .height(56.dp)
                    ) {
                        Text("Next", fontSize = TextUnit(22f, TextUnitType.Sp))
                    }
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