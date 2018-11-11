package com.doubletapp.sirius.view.trajectories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.doubletapp.sirius.data.trajectories.Trajectory
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_trajectory_event.*

class TrajectoryEventViewHolder(

        override val containerView: View?

) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    fun bind(trajectoryEvent: Trajectory) {

        itemTrajectoryHideView.visibility = when (trajectoryEvent.enabled) {
            true -> View.GONE
            else -> View.VISIBLE
        }
        itemLeftDividerTop.visibility = when (trajectoryEvent.visibleLeftTopDivider) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        itemLeftDividerBottom.visibility = when (trajectoryEvent.visibleLeftBottomDivider) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        itemTrajectoryEventText.text = trajectoryEvent.text
    }
}