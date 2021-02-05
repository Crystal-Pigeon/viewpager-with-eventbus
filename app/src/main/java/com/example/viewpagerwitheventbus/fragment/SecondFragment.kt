package com.example.viewpagerwitheventbus.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.viewpagerwitheventbus.R
import com.example.viewpagerwitheventbus.SearchEvent
import com.example.viewpagerwitheventbus.adapter.ContentAdapter
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.textView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

class SecondFragment: Fragment(R.layout.fragment_second) {

    private lateinit var adapter: ContentAdapter
    private val content = arrayListOf("Crvena Zvezda", "Dinamo Zagreb", "Chelsea", "Tottenham", "Leicester")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ContentAdapter(content)
        rv_second.adapter = adapter
        rv_second.layoutManager = LinearLayoutManager(context)
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onSearchEvent(searchEvent: SearchEvent) {
        val searchList = arrayListOf<String>()
        content.forEach { item ->
            if (item.toLowerCase(Locale.getDefault()).contains(searchEvent.query.toLowerCase(Locale.getDefault()))) {
                searchList.add(item)
            }
        }
        if (searchList.size > 0) {
            textView.visibility = View.GONE
            rv_second.visibility = View.VISIBLE
            adapter = ContentAdapter(searchList)
            rv_second.adapter = adapter
            rv_second.layoutManager = LinearLayoutManager(context)
        } else {
            textView.visibility = View.VISIBLE
            rv_second.visibility = View.GONE
        }
    }
}