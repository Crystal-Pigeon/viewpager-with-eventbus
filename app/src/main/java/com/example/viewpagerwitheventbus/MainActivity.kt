package com.example.viewpagerwitheventbus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import com.example.viewpagerwitheventbus.adapter.SearchViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus

class MainActivity : AppCompatActivity() {

    lateinit var searchViewPagerAdapter: SearchViewPagerAdapter
    private var selectedTab: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchViewPagerAdapter =
            SearchViewPagerAdapter(this, supportFragmentManager)

        et_search.doAfterTextChanged {
            val searchEvent = SearchEvent(
                et_search.text.toString(),
                searchViewPagerAdapter.getPageTitle(vp_search.currentItem)
            )
            EventBus.getDefault().post(searchEvent)
        }

        tl_search.setupWithViewPager(vp_search)
        vp_search.offscreenPageLimit = 2
        vp_search.adapter = searchViewPagerAdapter
        tl_search.getTabAt(selectedTab)?.select()
        tl_search.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(currentTab: TabLayout.Tab?) {
                selectedTab = currentTab?.position ?: 0
            }
        })
    }
}