package dev.drumath2237.bookoffoodsxr.ui.components

import android.content.res.Configuration
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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.xr.compose.platform.LocalSession
import androidx.xr.compose.platform.LocalSpatialCapabilities
import androidx.xr.compose.spatial.Orbiter
import androidx.xr.compose.spatial.OrbiterEdge
import androidx.xr.compose.spatial.Subspace
import androidx.xr.compose.subspace.SpatialPanel
import androidx.xr.compose.subspace.layout.SubspaceModifier
import androidx.xr.compose.subspace.layout.offset
import androidx.xr.compose.subspace.layout.size
import androidx.xr.runtime.Session
import androidx.xr.runtime.math.Pose
import androidx.xr.runtime.math.Vector3
import androidx.xr.scenecore.GltfModel
import androidx.xr.scenecore.GltfModelEntity
import androidx.xr.scenecore.MovableComponent
import androidx.xr.scenecore.scene
import dev.drumath2237.bookoffoodsxr.R
import dev.drumath2237.bookoffoodsxr.data.FoodResource
import dev.drumath2237.bookoffoodsxr.data.foodsResources
import dev.drumath2237.bookoffoodsxr.ui.theme.AndroidXRBookOfFoodsTheme
import kotlinx.coroutines.guava.await

@Composable
fun BookOfFoods() {
    var foodIndex by remember { mutableIntStateOf(0) }
    val food = foodsResources[foodIndex]

    if (LocalSpatialCapabilities.current.isSpatialUiEnabled) {
        SpatialFoodScreen(food = food)
    } else {
        FoodScreen(
            food = food,
            onNextButtonClicked = { foodIndex = (foodIndex + 1) % foodsResources.size },
        )
    }
}

@Composable
fun FoodScreen(
    food: FoodResource,
    modifier: Modifier = Modifier,
    onNextButtonClicked: () -> Unit,
) {
    val session = LocalSession.current

    Surface {
        Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                FoodImage(food = food, session = session)
                FoodDetailSidePanel(food = food, onNextButtonClicked = onNextButtonClicked)
            }
        }
    }
}

@Composable
fun FoodImage(food: FoodResource, session: Session?) {
    Box(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(food.imageResourceId),
            contentDescription = food.name,
            modifier = Modifier
                .size(480.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(color = Color(0xFFEADAC6))
        )

        IconButton(
            onClick = { session?.scene?.spatialEnvironment?.requestFullSpaceMode() },
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

}

@Composable
fun FoodDetailSidePanel(
    food: FoodResource,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
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
            colors = ButtonColors(
                containerColor = Color(0xFFB09257),
                contentColor = Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = contentColorFor(Color.Gray)
            ), modifier = Modifier
                .padding(vertical = 16.dp)
                .width(120.dp)
                .height(56.dp)
        ) {
            Text("Next", fontSize = TextUnit(22f, TextUnitType.Sp))
        }

    }
}

@Composable
fun SpatialFoodScreen(
    food: FoodResource
) {
    Subspace {
        val xrSession = checkNotNull(LocalSession.current)
        var gltfModelEntity = remember<GltfModelEntity?> { null }

        SpatialPanel(
            modifier = SubspaceModifier
                .size(256.dp)
                .offset(y = (-120).dp)
        ) {
            LaunchedEffect(key1 = Unit) {
                val gltfModel = GltfModel.create(xrSession, name = food.glbModelPath).await()
                gltfModelEntity = GltfModelEntity.create(
                    session = xrSession,
                    model = gltfModel,
                    pose = Pose(translation = Vector3(0f, -0.15f, 0f))
                )
                gltfModelEntity.addComponent(MovableComponent.create(session = xrSession))
            }

            DisposableEffect(Unit) {
                onDispose {
                    gltfModelEntity?.dispose()
                }
            }

            Orbiter(position = OrbiterEdge.Bottom) {
                Button(onClick = { xrSession.scene.spatialEnvironment.requestHomeSpaceMode() }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_home_space_mode_switch),
                            contentDescription = "return to 2d"
                        )
                        Text("Return to 2D")
                    }
                }
            }
        }
    }
}

@Preview(
    widthDp = 1024, heightDp = 720,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Preview(
    widthDp = 1024, heightDp = 720,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
fun BookOfFoodsPreview() {
    AndroidXRBookOfFoodsTheme {
        BookOfFoods()
    }
}