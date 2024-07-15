data class NavigationMenuGroup(
    val icon: Int,
    val text: String,
    val items: List<String>,
    var isExpanable : Boolean = false
)