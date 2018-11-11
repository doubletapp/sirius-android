package com.doubletapp.sirius.data.trajectories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

const val TYPE_HEADER = 10
const val TYPE_ACHIEVEMENT = 11
const val TYPE_EVENT = 12
const val TYPE_GOAL = 13


data class Trajectory(

        var type: Int = 10,

        var text: String? = null,

        var title: String? = null,

        var visibleLeftTopDivider: Boolean = true,

        var visibleLeftBottomDivider: Boolean = true,

        var enabled: Boolean = true,

        var audience: String? = null,

        var date: String? = null

)