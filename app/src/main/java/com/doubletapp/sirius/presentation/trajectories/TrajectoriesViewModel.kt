package com.doubletapp.sirius.presentation.trajectories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.doubletapp.sirius.base.BaseViewModel
import com.doubletapp.sirius.data.trajectories.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrajectoriesViewModel
@Inject
constructor(
) : BaseViewModel() {

    companion object {

        private const val TAG = "TrajectoriesViewModel"
    }

    private val mutableGoalTrajectories = MutableLiveData<List<Trajectory>>()
    private val mutableYearTrajectories = MutableLiveData<List<Trajectory>>()

    val goalTrajectories: LiveData<List<Trajectory>> = mutableGoalTrajectories
    val yearTrajectories: LiveData<List<Trajectory>> = mutableYearTrajectories

    init { loadTrajectories() }

    private fun loadTrajectories() {

        disposables.add(Single
                .fromCallable { Pair(getGoal(), getYear()) }
                .subscribeOn(Schedulers.io())
                .subscribe({ pair ->
                    mutableGoalTrajectories.postValue(pair.first)
                    mutableYearTrajectories.postValue(pair.second)
                }, { throwable ->
                    Log.e(TAG, "loadTrajectories(): ", throwable)
                }))
    }

    private fun getGoal(): List<Trajectory> {

        return mutableListOf<Trajectory>().apply {
            add(TrajectoryHeader("6 класс"))
            add(TrajectoryAchievement(
                title = "Центр Дополнительного Математического образования г. Курган",
                type = "Кружок",
                audience = "6-11 классы",
                date = "Ближайшая дата 2-8 декабря 2018"
            ).apply { enabled = false })
            add(TrajectoryAchievement(
                    title = "МОУ СОШ №53",
                    type = "Маткурсы",
                    audience = "8-11 классы",
                    date = "Ближайшая дата 11 января 2019"
            ).apply { enabled = false })
            add(TrajectoryEvent("Впереди еще 10 и 11 классы, так что не расслабляйся!"))
            add(TrajectoryGoal("Поступить в МГУ имени М.В. Ломоносова").apply { visibleLeftBottomDivider = false })
        }
    }

    private fun getYear(): List<Trajectory> {

        return mutableListOf<Trajectory>().apply {
            add(TrajectoryHeader("6 класс"))
            add(TrajectoryAchievement(
                title = "Центр Дополнительного Математического образования г. Курган",
                type = "Кружок",
                audience = "6-11 классы",
                date = "Ближайшая дата 2-8 декабря 2018"
                ).apply { enabled = false })
            add(TrajectoryEvent("Впереди еще 10 и 11 классы, так что не расслабляйся!"))
            add(TrajectoryGoal("Поступить в МГУ имени М.В. Ломоносова").apply { visibleLeftBottomDivider = false })
        }
    }
}