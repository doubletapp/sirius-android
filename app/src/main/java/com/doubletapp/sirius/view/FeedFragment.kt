package com.doubletapp.sirius.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.doubletapp.sirius.R
import com.doubletapp.sirius.extensions.showFragment
import com.doubletapp.sirius.model.FeedItem
import com.doubletapp.sirius.model.FeedItemType
import com.doubletapp.sirius.util.DecorationUtil
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FeedAdapter(this)
        feedRecycler.addItemDecoration(DecorationUtil.SiriusItemDecoration.builder
                .setColor(android.R.color.transparent)
                .setContext(view.context)
                .setHeaderListener(adapter)
                .setOffset(R.dimen.feed_item_offset)
                .setSidesOffset(R.dimen.feed_item_sides_offset)
                .build())
        feedRecycler.adapter = adapter
        adapter.listener = object : FeedAdapter.OnFilterClickListener {
            override fun onFilterClick() {
                (activity as MainActivity).showFragment(android.R.id.content,
                        FeedFilterFragment(), FeedFilterFragment.TAG, true)
            }
        }
        val list = ArrayList<FeedItem>()
        list.add(FeedItem(0, FeedItemType.TYPE_STORIES))
        list.add(FeedItem(1, FeedItemType.TYPE_HEADER))
        list.add(FeedItem(2, FeedItemType.TYPE_FEED_CARD))
        list.add(FeedItem(3, FeedItemType.TYPE_FEED_CARD))
        list.add(FeedItem(4, FeedItemType.TYPE_FEED_CARD_2))
        list.add(FeedItem(5, FeedItemType.TYPE_FEED_CARD))
        list.add(FeedItem(6, FeedItemType.TYPE_FEED_CARD_2))
        list.add(FeedItem(7, FeedItemType.TYPE_FEED_CARD))
        list.add(FeedItem(8, FeedItemType.TYPE_FEED_CARD))
        adapter.submitList(list)
    }
}
