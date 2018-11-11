package com.doubletapp.sirius.model

data class FeedItem(val id: Int,
                    @FeedItemType val type: Int,
                    val text: String = "",
                    val image: String = "",
                    val rating: Int = 0,
                    val comments: Int = 0)