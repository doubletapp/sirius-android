package com.doubletapp.sirius.view.trajectories

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.doubletapp.sirius.R
import com.doubletapp.sirius.data.trajectories.*
import java.lang.IllegalArgumentException

class TrajectoriesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<Trajectory>()

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is TrajectoryHeader -> Type.HEADER.ordinal
        is TrajectoryAchievement -> Type.ACHIVEMENT.ordinal
        is TrajectoryEvent -> Type.EVENT.ordinal
        is TrajectoryGoal -> Type.GOAL.ordinal
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

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("!!!", "item ${getItemViewType(position)}")
        when (getItemViewType(position)) {
            Type.HEADER.ordinal -> (holder as TrajectoryHeaderViewHolder).bind(items[position] as TrajectoryHeader)
            Type.ACHIVEMENT.ordinal -> (holder as TrajectoryAchvementViewHolder).bind(items[position] as TrajectoryAchievement)
            Type.EVENT.ordinal -> (holder as TrajectoryEventViewHolder).bind(items[position] as TrajectoryEvent)
            Type.GOAL.ordinal -> (holder as TrajectoryGoalViewHolder).bind(items[position] as TrajectoryGoal)
            else -> throw IllegalArgumentException("Unknown viewType")
        }
    }

    fun updateItems(list: List<Trajectory>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    enum class Type {

        HEADER, ACHIVEMENT, EVENT, GOAL
    }
}