package com.doubletapp.sirius.view.trajectories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.doubletapp.sirius.data.trajectories.Trajectory
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_trajectory_goal.*

class TrajectoryGoalViewHolder(

        override val containerView: View?

) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    fun bind(trajectoryGoal: Trajectory) {

        itemTrajectoryHideView.visibility = when (trajectoryGoal.enabled) {
            true -> View.GONE
            else -> View.VISIBLE
        }
        itemLeftDividerTop.visibility = when (trajectoryGoal.visibleLeftTopDivider) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        itemLeftDividerBottom.visibility = when (trajectoryGoal.visibleLeftBottomDivider) {
            true -> View.VISIBLE
            else -> View.INVISIBLE
        }
        itemTrajectoryGoalText.text = trajectoryGoal.text
    }
}