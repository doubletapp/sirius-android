package com.doubletapp.sirius.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.swipe_test_card.*

class SwipeTestAdapter : RecyclerView.Adapter<SwipeTestAdapter.SwipeTextViewHolder>() {

    companion object {
        const val TOP_LEVEL_ITEM = 0
        const val LOW_LEVEL_ITEM = 1
    }

    val items = mutableListOf(Pair("", 0),
            Pair("Является ли это уравнение квадратным?", R.drawable.ic_code_1),
            Pair("Произведение корней уравнения равно 6", R.drawable.ic_code_2),
            Pair("Верно ли утверждение, что", R.drawable.ic_code_3),
            Pair("Если p — простое число и a — любое целое число, то", R.drawable.ic_code_4))

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): SwipeTextViewHolder =
            when (viewType) {
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

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(p0: SwipeTextViewHolder, position: Int) {
        if (position > 0)
            p0.bind(items[position])
    }

    inner class SwipeTextViewHolder(override val containerView: View?) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {
        fun bind(item: Pair<String, Int>) {
            swipeCardText.text = item.first
            swipeCardImage.setImageDrawable(containerView?.context?.getDrawable(item.second))
        }
    }
}