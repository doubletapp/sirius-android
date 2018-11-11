package com.doubletapp.sirius.data.trajectories

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Trajectory(

        var visibleLeftTopDivider: Boolean = true,

        var visibleLeftBottomDivider: Boolean = true,

        var enabled: Boolean = true

) : Parcelable

@Parcelize
data class TrajectoryHeader(

        @SerializedName("title")
        var title: String? = null

) : Trajectory(), Parcelable

@Parcelize
data class TrajectoryAchievement(

        @SerializedName("title")
        var title: String? = null,

        @SerializedName("type")
        var type: String? = null,

        @SerializedName("audience")
        var audience: String? = null,

        @SerializedName("date")
        var date: String? = null

) : Trajectory(), Parcelable

@Parcelize
data class TrajectoryEvent(

        @SerializedName("text")
        var text: String? = null

) : Trajectory(), Parcelable

@Parcelize
data class TrajectoryGoal(

        @SerializedName("text")
        var text: String? = null

) : Trajectory(), Parcelable