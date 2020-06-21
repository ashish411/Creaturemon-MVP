package com.raywenderlich.android.creaturemon.presenter

import androidx.lifecycle.LiveData
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class AllCreaturePresenter(private val repository: RoomRepository = RoomRepository()):
    BasePresenter<AllCreatureContract.View>(), AllCreatureContract.Presenter{

    override fun getAllCreatures(): LiveData<List<Creature>> {
        return repository.getAllCreature()
    }

    override fun clearAllCreatures() {
        repository.clearAllCreatures()
        getView()?.showCreatureCleared()
    }


}