package ru.tpu.statappp.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun create(parent: ViewGroup, @LayoutRes layoutId: Int): View =
    LayoutInflater.from(parent.context)
        .inflate(layoutId, parent, false)