package com.doubletapp.sirius.view.trajectories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.doubletapp.sirius.data.trajectories.Trajectory
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_trajectory_achivement.*

class TrajectoryAchvementViewHolder(

        override val containerView: View?

) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    fun bind(trajectoryAchievement: Trajectory) {

        itemTrajectoryHideView.visibility = when (trajectoryAchievement.enabled) {
            true -> View.GONE
            else -> View.VISIBLE
        }
        itemLeftDividerTop.visibility = when (trajectoryAchievement.visibleLeftTopDivider) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        itemLeftDividerBottom.visibility = when (trajectoryAchievement.visibleLeftBottomDivider) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        itemTrajectoryAchimevemntTitle.text = trajectoryAchievement.title
        itemTrajectoryAchimevemntType.text = trajectoryAchievement.text
        itemTrajectoryAchimevemntAudience.text = trajectoryAchievement.audience
        itemTrajectoryAchimevemntDate.text = trajectoryAchievement.date
    }
}