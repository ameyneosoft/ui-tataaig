package com.example.tataaig

import NavigationMenuGroup
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.marginEnd
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.tataaig.databinding.ActivityMainBinding
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.journaldev.expandablelistview.ExpandableListDataPump


class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var expandableListDetail: List<NavigationMenuGroup>
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuListAdapter: MenuListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(binding.main)
        expandableListDetail = ExpandableListDataPump.data

        menuRecyclerView = binding.menuRecycleView
        menuRecyclerView.layoutManager = LinearLayoutManager(this, VERTICAL,false)
        menuListAdapter = MenuListAdapter(expandableListDetail)
        menuRecyclerView.adapter = menuListAdapter


        val dotRec = binding.motorRecView.dotRecyclerView
        dotRec.layoutManager  =LinearLayoutManager(this, HORIZONTAL,false)
        val li = listOf(true, false, false,false)
        val dotViewRecyclerAdapter = DotViewRecyclerAdapter(li)
        dotRec.adapter = dotViewRecyclerAdapter


        val imageRecycler = binding.imagesRecyclerView
        imageRecycler.layoutManager = LinearLayoutManager(this, HORIZONTAL,false)
        val list = listOf<Int>(1,2,3)
        val imageRecyclerAdapter = ImageRecycler(list)
        imageRecycler.adapter =imageRecyclerAdapter

        val motorRecycler = binding.motorRecView.motorRecyclerView
        motorRecycler.layoutManager =LinearLayoutManager(this, HORIZONTAL,false)
        val motorList = listOf<Int>(1,2,3,4)
        val MotorAdapter = MotorAdapter(motorList)
        motorRecycler.adapter = MotorAdapter
        PagerSnapHelper().attachToRecyclerView(motorRecycler)

        motorRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                for(i in  0..3){
                    if (firstVisibleItemPosition == i) {
                        (dotRec.layoutManager as LinearLayoutManager).getChildAt(i)?.findViewById<ImageView>(R.id.dot_view)?.setImageResource(R.drawable.active_dot)

                    } else {
                        (dotRec.layoutManager as LinearLayoutManager).getChildAt(i)?.findViewById<ImageView>(R.id.dot_view)?.setImageResource(R.drawable.inactive_dot)

                    }
                }
            }
        })

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
            showTooltip(it)
        }

        val spanView = binding.motorDetailedHeader
        val spanString = SpannableString("You are Not Eligible for 2/4 Campaign")
        spanString.setSpan(ForegroundColorSpan(getColor(R.color.warning)),8,21,Spannable.SPAN_EXCLUSIVE_INCLUSIVE )
        spanView.text = spanString

    }

    private fun showTooltip(anchorView: View) {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val tooltipView = inflater.inflate(R.layout.toolbar_tip, null)


        val popupWindow = PopupWindow(tooltipView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        popupWindow.isOutsideTouchable = true
        popupWindow.isFocusable = true
        val xoffSet = (anchorView.width - anchorView.paddingEnd -anchorView.marginEnd - 40 )/2
        val yoffSet = -1 * anchorView.height + 20
        popupWindow.showAsDropDown(anchorView,xoffSet,yoffSet)
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
       val cards = CardsDataPump.data

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

