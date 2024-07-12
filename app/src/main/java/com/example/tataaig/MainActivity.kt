package com.example.tataaig

import NavMenuListAdapter
import NavigationMenuGroup
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import androidx.core.view.marginEnd
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tataaig.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.journaldev.expandablelistview.ExpandableListDataPump


class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: NavMenuListAdapter
    private lateinit var expandableListDetail: List<NavigationMenuGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.main)

        expandableListView = findViewById<ExpandableListView>(R.id.expandableListView)
        expandableListDetail = ExpandableListDataPump.data


        expandableListAdapter = NavMenuListAdapter(this, expandableListDetail )
        expandableListView.setAdapter(expandableListAdapter)
        expandableListAdapter.groupCount
        repeat(expandableListAdapter.groupCount){
            expandableListView.expandGroup(it)
        }
//        expandableListView.setOnGroupExpandListener { groupPosition ->
//            animateExpand(groupPosition)
//        }
//
//        expandableListView.setOnGroupCollapseListener { groupPosition ->
//            animateCollapse(groupPosition)
//        }



        drawerLayout = findViewById(R.id.main)
        findViewById<ImageButton>(R.id.nav_close).setOnClickListener{
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout,R.string.nav_open,R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        setSupportActionBar(findViewById(R.id.nav_toolbar))

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addQuickActions()
        addHeaders()
        addQuoteImages()
        addCards()
        val textView: TextView = findViewById<TextView?>(R.id.health_header)


        textView.setOnClickListener {

            Log.d("mytag","inside listener")
            showTooltip(it)
            true
        }
    }

    private fun showTooltip(anchorView: View) {
        // Inflate the custom tooltip layout
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.toolbar_tip, null)


        val popupWindow = PopupWindow(tooltipView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        // Show the popup window
        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        val xoffSet = (anchorView.width - anchorView.paddingEnd -anchorView.marginEnd -10 )/2
        val yoffSet = -1 * anchorView.height + 10
        popupWindow.showAsDropDown(anchorView,xoffSet,yoffSet)
    }

    private fun animateExpand(groupPosition: Int) {
        val childCount = expandableListAdapter.getChildrenCount(groupPosition)
        for (i in 0 until childCount) {
            val childView = expandableListAdapter.getChildView ( groupPosition, i,
                false,
                null,
                null)
            childView?.let {
                val animator = ValueAnimator.ofFloat(0f, 1f).apply {
                    duration = 300
                    addUpdateListener { animation ->
                        val value = animation.animatedValue as Float
                        it.alpha = value
                        it.scaleX = value
                        it.scaleY = value
                    }
                }
                animator.start()
            }
        }
    }

    private fun animateCollapse(groupPosition: Int) {
        val childCount = expandableListAdapter.getChildrenCount(groupPosition)
        for (i in 0 until childCount) {
            val childView = expandableListAdapter.getChildView ( groupPosition, i,
            false,
            null,
            null)
            childView?.let {
                val animator = ValueAnimator.ofFloat(1f, 0f).apply {
                    duration = 300
                    addUpdateListener { animation ->
                        val value = animation.animatedValue as Float
                        it.alpha = value
                        it.scaleX = value
                        it.scaleY = value
                    }
                }
                animator.start()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun addHeaders() {
        findViewById<TextView>(R.id.business_text).apply {
            setText("BUSINESS SUMMARY")
            setCompoundDrawables(null, null, null, null)
        }
        findViewById<TextView>(R.id.quick_text).apply {
            setCompoundDrawables(null, null, null, null)
        }
        findViewById<TextView>(R.id.quote_text).apply {
            setText("QUICK QUOTE")
            setCompoundDrawables(null, null, null, null)
        }
        findViewById<TextView>(R.id.gaint_step_text).text = "GAIN STEPS"
    }

    private fun addQuickActions() {
        findViewById<CardView>(R.id.claims).apply {
            findViewById<TextView>(R.id.action_count).text = "02"
            findViewById<TextView>(R.id.action_name).text = "Claims"
            findViewById<TextView>(R.id.action_warning).visibility = View.INVISIBLE
        }
        findViewById<CardView>(R.id.payment).apply {
            findViewById<TextView>(R.id.action_count).text = "08"
            findViewById<TextView>(R.id.action_name).text = "Payments"
            findViewById<TextView>(R.id.action_warning).visibility = View.INVISIBLE
        }

    }

    fun addQuoteImages() {
        findViewById<LinearLayout>(R.id.health_icon).apply {
            findViewById<ImageView>(R.id.quote_icon).setImageResource(R.drawable.health)
            findViewById<TextView>(R.id.quote_text).setText("Health")
        }
        findViewById<LinearLayout>(R.id.comm_lines_icon).apply {
            findViewById<ImageView>(R.id.quote_icon).setImageResource(R.drawable.comm_lines)
            findViewById<TextView>(R.id.quote_text).setText("Comm. Lines")
        }
        findViewById<LinearLayout>(R.id.travel_icon).apply {
            findViewById<ImageView>(R.id.quote_icon).setImageResource(R.drawable.travel)
            findViewById<TextView>(R.id.quote_text).setText("Travel")
        }

    }

    fun addCards() {
        var cards: MutableList<DetailedCard> = ArrayList<DetailedCard>()
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
                card_id = R.id.motor_card,
                dcard_top_left = "Quaterly \n02 May - 02 Aug'24",
                dcard_top_right = "As on 12 Jun'24",
                dcard_heading = "Motor Quaterly Campaign",
                max_card_progress = 750,
                card_progress = 205,
                progress_image = R.drawable.ic_motor_blue,
                mid_left_heading = "Slab Target\n(Wtd. GWP)",
                mid_right_heading = "Achieved\n(Wtd.GWP)",
                mid_left_value = "75K",
                mid_right_value = "20.5K",
                group_visible = View.GONE,
                bottom_heading = "Upcoming Slab Target (Wtd. GWP)",
                bottom_progress_visible = View.GONE,
                dcard_button = "View Campaign",
                card_bottom_value = "1.5L",
                eligible_text = "Not Eligible",
                eligible_drawable = R.drawable.ineligible
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

        cards.forEach { detailedCard ->
            findViewById<CardView>(detailedCard.card_id).apply {
                findViewById<TextView>(R.id.dcard_top_left).apply {
                    text = detailedCard.dcard_top_left
                }
                findViewById<TextView>(R.id.dcard_top_right).apply {
                    text = detailedCard.dcard_top_right
                }
                findViewById<TextView>(R.id.dcard_heading).apply {
                    if(detailedCard.dcard_heading == "") {visibility = View.GONE}
                    text = detailedCard.dcard_heading
                }
                findViewById<CircularProgressIndicator>(R.id.card_progress).apply {
                    max = detailedCard.max_card_progress
                    progress = detailedCard.card_progress
                }
                findViewById<ImageView>(R.id.progress_image).apply {
                    setImageResource(detailedCard.progress_image)
                }
                findViewById<Group>(R.id.group_bottom_text).visibility = detailedCard.group_visible
                findViewById<TextView>(R.id.mid_left_heading).text = detailedCard.mid_left_heading
                findViewById<TextView>(R.id.mid_right_heading).apply {
                    text = detailedCard.mid_right_heading
                }
                findViewById<TextView>(R.id.mid_left_value).text = detailedCard.mid_left_value
                findViewById<TextView>(R.id.mid_right_value).text = detailedCard.mid_right_value
                findViewById<Group>(R.id.bottom_progress_group).visibility = detailedCard.bottom_progress_visible
                findViewById<TextView>(R.id.card_bottom_heading).text = detailedCard.bottom_heading
                findViewById<TextView>(R.id.card_bottom_value).apply {
                    if (detailedCard.card_bottom_value != "") {
                        text = detailedCard.card_bottom_value
                    } else {
                        visibility = View.GONE
                    }
                }
                findViewById<Button>(R.id.dcard_button).text = detailedCard.dcard_button
                findViewById<TextView>(R.id.eligible_text).apply {
                    text = detailedCard.eligible_text
                    val draw = ContextCompat.getDrawable(this@MainActivity,detailedCard.eligible_drawable)
                    this.setCompoundDrawablesWithIntrinsicBounds(draw, null, null, null)
                }
            }
        }
        binding.campaignText.quickText2.apply{
            text = "Campaign"
            setCompoundDrawablesWithIntrinsicBounds(null,null,null,null)
        }
        binding.motorHeader.subtitle.text = "Motor"
        binding.travelHeader.subtitle.text = "Travel"
        binding.commLineHeader.subtitle.text = "Comm. Lines"
    }



}

