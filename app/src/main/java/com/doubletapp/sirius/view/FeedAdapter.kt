package com.doubletapp.sirius.view

import android.view.View
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.doubletapp.sirius.R
import com.doubletapp.sirius.model.FeedItem
import com.doubletapp.sirius.model.FeedItemType.Companion.TYPE_HEADER
import com.doubletapp.sirius.model.FeedItemType.Companion.TYPE_STORIES
import com.doubletapp.sirius.util.DecorationUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.feed_header_layout.view.*
import kotlinx.android.synthetic.main.feed_item_layout.*

class FeedAdapter : ListAdapter<FeedItem, ViewHolder>(FeedItemDiffCallback()), DecorationUtil.StickyHeaderInterface {

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = -1
        for (i in itemPosition downTo 0) {
            if (isHeader(i)) {
                headerPosition = i
                break
            }
        }
        return headerPosition
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        if (headerPosition == -1) {
            return -1
        }
        return R.layout.feed_header_layout
    }

    override fun isHeader(itemPosition: Int): Boolean = getItem(itemPosition).type == TYPE_HEADER

    override fun bindHeaderData(header: View, headerPosition: Int) {
        header.feedFilter.setOnClickListener {  }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        when (p1) {
            TYPE_STORIES -> FeedStoriesViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.feed_stories_layout, p0, false)
            )
            TYPE_HEADER -> FeedHeaderViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.feed_header_layout, p0, false)
            )
            else -> FeedViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.feed_item_layout, p0, false)
            )
        }

    override fun getItemViewType(position: Int): Int = getItem(position).type

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) =
        when (p0) {
            is FeedStoriesViewHolder -> p0.bind(getItem(p1))
            is FeedHeaderViewHolder -> p0.bind(getItem(p1))
            else -> (p0 as FeedViewHolder).bind(getItem(p1))
        }

    class FeedItemDiffCallback : DiffUtil.ItemCallback<FeedItem>() {
        override fun areItemsTheSame(p0: FeedItem, p1: FeedItem): Boolean = p0.id == p1.id

        override fun areContentsTheSame(p0: FeedItem, p1: FeedItem): Boolean = p0 == p1
    }

    inner class FeedStoriesViewHolder(override val containerView: View?) : ViewHolder(containerView!!),
        LayoutContainer {
        fun bind(feedItem: FeedItem) {

        }
    }

    inner class FeedHeaderViewHolder(override val containerView: View?) : ViewHolder(containerView!!),
        LayoutContainer {
        fun bind(feedItem: FeedItem) {

        }
    }

    inner class FeedViewHolder(override val containerView: View?) : ViewHolder(containerView!!),
        LayoutContainer {
        fun bind(feedItem: FeedItem) {
            Glide.with(itemView)
                    .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSujyiyG_Ux0QW7RYhbFvM-3bojbUdkcqRCjuKrbiLWrfG2BKjVGw")
                    .into(feedItemImage)
        }
    }
}