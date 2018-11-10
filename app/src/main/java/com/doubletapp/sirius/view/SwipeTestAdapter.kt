package com.doubletapp.sirius.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import kotlinx.android.extensions.LayoutContainer

class SwipeTestAdapter : RecyclerView.Adapter<SwipeTestAdapter.SwipeTextViewHolder>() {

    companion object {
        const val TOP_LEVEL_ITEM = 0
        const val LOW_LEVEL_ITEM = 1
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SwipeTextViewHolder =
        when (p1) {
            TOP_LEVEL_ITEM -> SwipeTextViewHolder(
                    LayoutInflater.from(p0.context).inflate(R.layout.swipe_test_card, p0, false)
            )
            else -> SwipeTextViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.swipe_test_top_card, p0, false)
            )
        }

    override fun getItemViewType(position: Int): Int =
        when (position) {
            0 -> LOW_LEVEL_ITEM
            else -> TOP_LEVEL_ITEM
        }

    override fun getItemCount(): Int = 6

    override fun onBindViewHolder(p0: SwipeTextViewHolder, p1: Int) {
         p0.bind()
    }

    inner class SwipeTextViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {
        fun bind() {}
    }
}