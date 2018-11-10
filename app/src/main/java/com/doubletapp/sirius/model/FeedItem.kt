package com.doubletapp.sirius.model

data class FeedItem(val id: Int,
                    @FeedItemType val type: Int)