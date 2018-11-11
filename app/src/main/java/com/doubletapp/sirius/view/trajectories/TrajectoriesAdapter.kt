package com.doubletapp.sirius.view.trajectories

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.data.trajectories.*
import java.lang.IllegalArgumentException

class TrajectoriesAdapter : ListAdapter<Trajectory, RecyclerView.ViewHolder>(TrajectoriesItemCallback()) {

    class TrajectoriesItemCallback : DiffUtil.ItemCallback<Trajectory>() {
        override fun areItemsTheSame(p0: Trajectory, p1: Trajectory): Boolean = p0.title == p1.title

        override fun areContentsTheSame(p0: Trajectory, p1: Trajectory): Boolean = p0 == p1
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position).type) {
        TYPE_HEADER -> Type.HEADER.ordinal
        TYPE_ACHIEVEMENT -> Type.ACHIVEMENT.ordinal
        TYPE_EVENT -> Type.EVENT.ordinal
        TYPE_GOAL -> Type.GOAL.ordinal
        else -> throw IllegalArgumentException("Unknown viewType")
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (getItemViewType(position)) {
            Type.HEADER.ordinal -> TrajectoryHeaderViewHolder(layoutInflater.inflate(R.layout.item_trajectory_header, parent, false))
            Type.ACHIVEMENT.ordinal -> TrajectoryAchvementViewHolder(layoutInflater.inflate(R.layout.item_trajectory_achivement, parent, false))
            Type.EVENT.ordinal -> TrajectoryEventViewHolder(layoutInflater.inflate(R.layout.item_trajectory_event, parent, false))
            Type.GOAL.ordinal -> TrajectoryGoalViewHolder(layoutInflater.inflate(R.layout.item_trajectory_goal, parent, false))
            else -> throw IllegalArgumentException("Unknown viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("!!!", "item ${getItemViewType(position)}")
        when (holder) {
            is TrajectoryHeaderViewHolder -> holder.bind(getItem(position))
            is TrajectoryAchvementViewHolder -> holder.bind(getItem(position))
            is TrajectoryEventViewHolder -> holder.bind(getItem(position))
            is TrajectoryGoalViewHolder -> holder.bind(getItem(position))
            else -> throw IllegalArgumentException("Unknown viewType")
        }
    }

    enum class Type {
        HEADER, ACHIVEMENT, EVENT, GOAL
    }
}