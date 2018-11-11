package com.doubletapp.sirius.model

import android.support.annotation.IntDef
import com.doubletapp.sirius.model.FeedItemType.Companion.TYPE_FEED_CARD
import com.doubletapp.sirius.model.FeedItemType.Companion.TYPE_HEADER
import com.doubletapp.sirius.model.FeedItemType.Companion.TYPE_STORIES

@IntDef(TYPE_STORIES, TYPE_HEADER, TYPE_FEED_CARD)
@Retention(AnnotationRetention.SOURCE)
annotation class FeedItemType {
    companion object {
        const val TYPE_STORIES = 11
        const val TYPE_HEADER = 12
        const val TYPE_FEED_CARD = 13
        const val TYPE_FEED_CARD_2 = 14
    }
}