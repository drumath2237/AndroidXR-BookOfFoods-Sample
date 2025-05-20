package dev.drumath2237.bookoffoodsxr.data

import dev.drumath2237.bookoffoodsxr.R

data class FoodResource(
    val name: String,
    val detail: String,
    val imageResourceId: Int,
    val glbModelPath: String
)

private const val burgerDetail =
    "A burger is a popular fast food consisting of a cooked meat patty placed inside a sliced bun. It often comes with lettuce, tomato, cheese, pickles, and condiments like ketchup or mustard. Burgers are loved worldwide for their rich flavor and endless customization options."
private const val donutDetail =
    "Donut sprinkles are colorful sugar decorations often added to glazed donuts for extra sweetness and visual appeal. They come in various shapes and colors, making donuts fun and festive. Kids and adults alike enjoy the cheerful look and crunchy texture of sprinkle-covered donuts."
private const val panStewDetail =
    "Pan stew is a hearty dish made by simmering ingredients like meat, vegetables, and broth in a single pan. It's warm, comforting, and often eaten during colder months. Each culture has its own version, adding unique flavors and local ingredients."
private const val tacoDetail =
    "A taco is a traditional Mexican dish made by folding a tortilla around a filling such as meat, beans, cheese, or vegetables. It's known for its bold flavors and spicy toppings like salsa or jalapeños. Tacos are popular for both street food and home meals due to their versatility."

val foodsResources = listOf(
    FoodResource("Burger", burgerDetail, R.drawable.burger, "burger.glb"),
    FoodResource("Donut", donutDetail, R.drawable.donut, "donut-sprinkles.glb"),
    FoodResource("Pan Stew", panStewDetail, R.drawable.panstew, "pan-stew.glb"),
    FoodResource("Taco", tacoDetail, R.drawable.taco, "taco.glb"),
)