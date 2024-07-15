package com.example.tataaig

import android.view.View

object CardsDataPump {
    val data : List<DetailedCard>
        get() {
            val cards: MutableList<DetailedCard> = ArrayList<DetailedCard>()
            cards.add(
                DetailedCard(
                    card_id = R.id.gaint_step_card,
                    dcard_top_left = "FY 24- 25",
                    dcard_top_right = "As on 12 Jun'24",
                    dcard_heading = "",
                    max_card_progress = 100,
                    card_progress = 35,
                    progress_image = 0,
                    mid_left_heading = "Target Premium",
                    mid_right_heading = "Earned Premium",
                    mid_left_value = "$1.1Cr",
                    mid_right_value = "$12.5L",
                    group_visible = View.GONE,
                    bottom_heading = "1.95 Away From Diamond Club",
                    bottom_progress_visible = View.VISIBLE,
                    dcard_button = "View Incentive Details",
                    card_bottom_value = "",
                    eligible_text = "Not Eligible",
                    eligible_drawable = R.drawable.ineligible
                )
            )
            cards.add(
                DetailedCard(
                    card_id = R.id.health_card,
                    dcard_top_left = "Quaterly \n02 May - 02 Aug'24",
                    dcard_top_right = "As on 12 Jun'24",
                    dcard_heading = "Health Quaterly Campaign",
                    max_card_progress = 750,
                    card_progress = 205,
                    progress_image = R.drawable.ic_health_blue,
                    mid_left_heading = "Slab Target\n(Wtd. GWP)",
                    mid_right_heading = "Achieved\n(Wtd.GWP)",
                    mid_left_value = "75K",
                    mid_right_value = "20.5K",
                    group_visible = View.GONE,
                    bottom_heading = "Upcoming Slab Target (Wtd. GWP)",
                    bottom_progress_visible = View.GONE,
                    dcard_button = "View Campaign",
                    card_bottom_value = "1.5L"

                )
            )

            cards.add(
                DetailedCard(
                    card_id = R.id.travel_card,
                    dcard_top_left = "Quaterly \n02 May - 02 Aug'24",
                    dcard_top_right = "As on 12 Jun'24",
                    dcard_heading = "Travel 24 Campaign",
                    max_card_progress = 750,
                    card_progress = 205,
                    progress_image = R.drawable.ic_travel_blue,
                    mid_left_heading = "Slab Target\n(Wtd. GWP)",
                    mid_right_heading = "Achieved\n(Wtd.GWP)",
                    mid_left_value = "75K",
                    mid_right_value = "20.5K",
                    group_visible = View.GONE,
                    bottom_heading = "Upcoming Slab Target (Wtd. GWP)",
                    bottom_progress_visible = View.GONE,
                    dcard_button = "View Campaign",
                    card_bottom_value = "1.5L"
                )
            )
            cards.add(
                DetailedCard(
                    card_id = R.id.comm_line_card,
                    dcard_top_left = "Quaterly \n02 May - 02 Aug'24",
                    dcard_top_right = "As on 12 Jun'24",
                    dcard_heading = "Comm.Lines Quarterly Campaign",
                    max_card_progress = 750,
                    card_progress = 550,
                    progress_image = R.drawable.ic_comm_lines_blue,
                    mid_left_heading = "Slab Target",
                    mid_right_heading = "Achieved",
                    mid_left_value = "75K",
                    mid_right_value = "55K    ",
                    group_visible = View.VISIBLE,
                    bottom_heading = "Upcoming Slab Target",
                    bottom_progress_visible = View.GONE,
                    dcard_button = "View Campaign",
                    card_bottom_value = "25L"
                )
            )

            return cards
        }
}