package com.raywenderlich.android.creaturemon.presenter

import androidx.lifecycle.LiveData
import com.raywenderlich.android.creaturemon.model.Creature

interface AllCreatureContract {

    interface Presenter{
        fun getAllCreatures(): LiveData<List<Creature>>
        fun clearAllCreatures()
    }

    interface View{
        fun showCreatureCleared()
    }

}