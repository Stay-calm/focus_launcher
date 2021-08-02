package com.easyapps.focusmode.launcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class OnBoardingPagerAdapter : PagerAdapter() {

    private val listOfLayouts = arrayOf(
        R.layout.onboard_info,
        R.layout.enable_notification_block,
        R.layout.enable_remind_focus_lost,
        R.layout.enable_default_launcher
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        return LayoutInflater.from(container.context)
            .inflate(listOfLayouts[position], container, false)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return false
    }

    override fun getCount(): Int {
        return listOfLayouts.size
    }
}