package com.rawlin.bottomsheet.models

import androidx.annotation.ColorInt

data class EMICard(
    val id: Int,
    @ColorInt
    val cardColor: Int,
    val perMonthPrice: Int,
    val duration: Int,
    var isSelected: Boolean
)
