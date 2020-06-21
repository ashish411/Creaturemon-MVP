package com.raywenderlich.android.creaturemon.presenter

import com.raywenderlich.android.creaturemon.model.AttributeStore
import com.raywenderlich.android.creaturemon.model.AttributeType
import com.raywenderlich.android.creaturemon.model.AttributeType.ENDURANCE
import com.raywenderlich.android.creaturemon.model.AttributeType.INTELLIGENCE
import com.raywenderlich.android.creaturemon.model.AttributeType.STRENGTH
import com.raywenderlich.android.creaturemon.model.Creature
import com.raywenderlich.android.creaturemon.model.CreatureAttributes
import com.raywenderlich.android.creaturemon.model.CreatureGenerator
import com.raywenderlich.android.creaturemon.model.room.RoomRepository

class CreaturePresenter(private val creatureGenerator: CreatureGenerator = CreatureGenerator(),
    private val repository: RoomRepository = RoomRepository()) :
    BasePresenter<CreatureContract.View>(), CreatureContract.Presenter {

    private lateinit var creature: Creature

    private var name = ""
    private var intelligence = 0
    private var endurance = 0
    private var strength = 0
    private var drawable = 0

    override fun updateName(name: String) {
        this.name = name
        updateCreature()
    }

    override fun attributeSelected(attributeType: AttributeType, position: Int) {
        when (attributeType) {
            INTELLIGENCE -> this.intelligence = AttributeStore.INTELLIGENCE[position].value
            STRENGTH -> this.strength = AttributeStore.STRENGTH[position].value
            ENDURANCE -> this.endurance = AttributeStore.ENDURANCE[position].value
        }
        updateCreature()
    }

    override fun drawableSelected(drawable: Int) {
        this.drawable = drawable
        getView()?.showAvatarDrawable(drawable)
        updateCreature()
    }

    override fun isDrawableSelected(): Boolean {
        return drawable != 0
    }

    override fun saveCreature() {
        if (canSaveCreature()) {
            repository.saveCreature(creature)
            getView()?.showCreatureSaved()
        } else {
            getView()?.showCreatureSavedError()
        }
    }

    private fun updateCreature() {
        val attributes = CreatureAttributes(intelligence, strength, endurance)
        creature = creatureGenerator.generateCreature(attributes, name, drawable)
        getView()?.showHitPoints(creature.hitPoints.toString())
    }

    private fun canSaveCreature(): Boolean{
        return intelligence!=0 && endurance!=0 && strength!=0 && name.isNotEmpty() && drawable!=0
    }

}