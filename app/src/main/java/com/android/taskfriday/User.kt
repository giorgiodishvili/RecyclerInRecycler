package com.android.taskfriday

import android.text.InputType

data class User(
    val field_id: Int,
    val hint: String,
    val field_type: String,
    val keyboard: String = "",
    val required: Boolean = false,
    val is_active: Boolean,
    val icon: String
)
