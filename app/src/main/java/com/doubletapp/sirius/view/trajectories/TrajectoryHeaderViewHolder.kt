package com.doubletapp.sirius.view.trajectories

import android.support.v7.widget.RecyclerView
import android.view.View
import com.doubletapp.sirius.data.trajectories.TrajectoryHeader
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_trajectory_header.*

class TrajectoryHeaderViewHolder(

        override val containerView: View?

) : RecyclerView.ViewHolder(containerView!!), LayoutContainer {

    fun bind(trajectoryHeader: TrajectoryHeader) {

        itemTrajectoryHeaderTitle.text = trajectoryHeader.title
    }
}