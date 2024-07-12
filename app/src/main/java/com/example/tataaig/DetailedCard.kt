package com.example.tataaig

import android.view.View

data class DetailedCard(
    val card_id: Int,
    val dcard_top_left: String,
    val dcard_top_right: String,
    val dcard_heading: String,
    val max_card_progress: Int,
    val card_progress: Int,
    val progress_image: Int,
    val mid_left_heading: String = "Slab Target",
    val mid_right_heading: String = "Achieved",
    val mid_left_value: String = "75K",
    val mid_right_value: String = "20.5K",
    val group_visible: Int = View.GONE,
    val bottom_progress_visible: Int = View.GONE,
    val bottom_heading: String,
    val dcard_button: String,
    val card_bottom_value: String,
    val eligible_drawable: Int = R.drawable.eligible_green,
    val eligible_text: String = "Eligible"
)