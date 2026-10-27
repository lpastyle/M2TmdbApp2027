package com.example.m2tmdbapp2027

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes

// default values, shared by the property declarations and the XML attribute parsing
private const val DEFAULT_SCORE_MAX = 100f
private const val DEFAULT_SCORE_VALUE = 75f
private const val DEFAULT_SCORE_LABEL = ""
private const val DEFAULT_SCORE_COLOR = Color.GREEN

class ScoreGaugeView@JvmOverloads constructor (
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var scoreMax : Float = DEFAULT_SCORE_MAX
    var scoreValue : Float = DEFAULT_SCORE_VALUE
    var scoreLabel : String =DEFAULT_SCORE_LABEL
    var scoreColor : Int = DEFAULT_SCORE_COLOR

    init {
       // context.withStyledAttributes(

//        )
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        }



}