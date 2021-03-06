package com.example.calendartask.domain.model

import com.example.calendartask.data.database.model.ColorDbModel

/**
 * Model class for one Color
 */
data class ColorModel(
    val id: Long,
    val name: String,
    val hex: String
) {

    companion object {

        val DEFAULT = with(ColorDbModel.DEFAULT_COLOR) { ColorModel(id, name, hex) }
    }
}