package com.example.m2tmdbapp2027

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.hardware.SensorEvent
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class SensorDemoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    // hold XML custom attributes
    var dialCaption: String = ""
        set(value) {
            field = value
            invalidate()
        }

    var dialColor = Color.BLUE // default color
        set(value) {
            field = value
            invalidate()
        }

    // Other public properties related to the sensor
    var sensorEvent: SensorEvent? = null
        set(value) {
            field = value
            invalidate()
        }

    // Hold private properties
    private val dialNeedleColors: IntArray = context.resources.getIntArray(R.array.dial_needle_colors)



}