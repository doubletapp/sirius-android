package com.doubletapp.sirius.view.survey

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.doubletapp.sirius.R
import kotlinx.android.synthetic.main.recycler_item_areas_of_interest.view.*

enum class Sphere(val title: String) {
    SCIENCE("Наука"),
    ART("Искусство"),
    LITERATURE("Литературное творчество"),
    SPORT("Спорт"),
    UNDEFINED("")
}

class AreasAdapter : RecyclerView.Adapter<AreasAdapter.AreaViewHolder>() {

    var singleSelection = true
    var checkedIdx = -1
    var checked: MutableList<String> = mutableListOf()
    var listener: OnSelectionListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AreaViewHolder {
        return AreaViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_areas_of_interest, parent, false))
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    fun getCurrentSphere(): Int {
        return checkedIdx
    }

    override fun onBindViewHolder(p0: AreaViewHolder, p1: Int) {
        p0.bind(items[p1], p1)
    }

    var items: MutableList<Pair<String, Int>> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class AreaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Pair<String, Int>, position: Int) {
            if (singleSelection)
                itemView.isSelected = position == checkedIdx
            else
                itemView.isSelected = checked.contains(item.first)
            itemView.areasOfInterestMainTitle.text = item.first
            itemView.areasOfInterestMainTitle.setCompoundDrawablesWithIntrinsicBounds(0, item.second, 0, 0)
            itemView.areasOfInterestIndicator.visibility = if (itemView.isSelected) {
                VISIBLE
            } else {
                GONE
            }

            //cause selector not working
            itemView.areasOfInterestMainTitle.setTextColor(itemView.context.getColor(if (itemView.isSelected) {
                R.color.white
            } else {
                R.color.text_dark_gray
            }))

            itemView.setOnClickListener {
                if (singleSelection) {
                    checkedIdx = position
                    listener?.itemSelected(checkedIdx)
                } else {
                    if (itemView.isSelected) {
                        checked.remove(item.first)
                    } else {
                        checked.add(item.first)
                    }
                }
                notifyDataSetChanged()
            }
        }
    }

    public interface OnSelectionListener {
        fun itemSelected(position: Int)
    }
}
