package com.example.m2tmdbapp2027

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.abs

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
    var scoreLabel : String = DEFAULT_SCORE_LABEL
    var scoreColor : Int = DEFAULT_SCORE_COLOR

    // Paint styles used for rendering are initialized here to improve performance,
    // since onDraw() is called for every screen refresh.
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //  stroke width for rectangles
    private val so = 4f
    // compute dimensions to be used for drawing text
    private val fontMetrics = paint.fontMetrics
    private val textHeight = abs(paint.fontMetrics.ascent + fontMetrics.descent)

    init {
        context.withStyledAttributes(attrs, R.styleable.ScoreGaugeView) {
            scoreMax = getFloat(R.styleable.ScoreGaugeView_scoreMax, DEFAULT_SCORE_MAX)
            scoreValue = getFloat(R.styleable.ScoreGaugeView_scoreValue, DEFAULT_SCORE_VALUE)
            scoreColor = getColor(R.styleable.ScoreGaugeView_scoreColor, DEFAULT_SCORE_COLOR)
            scoreLabel = getString(R.styleable.ScoreGaugeView_scoreLabel) ?: DEFAULT_SCORE_LABEL

        }
    }



    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val margin = 4 * so  // room taken by the inset rectangle and its stroke on both sides

        // The gauge fills the available width; it only falls back on the content width when
        // the parent gives no constraint (UNSPECIFIED).
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val desiredWidth = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.UNSPECIFIED) {
            paddingLeft + paddingRight + margin +
                    paint.measureText(" ${scoreLabel ?: ""}") + paint.measureText("100% ")
        } else {
            widthSize.toFloat()
        }

        // Height is driven by the font: one text line plus paddings and margins.
        val desiredHeight = paddingTop + paddingBottom + margin +
                (fontMetrics.descent - fontMetrics.ascent)

        setMeasuredDimension(
            resolveSize(desiredWidth.toInt(), widthMeasureSpec),
            resolveSize(desiredHeight.toInt(), heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Set pencil style
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = so * 1.5f
        paint.color = Color.BLACK
        canvas.drawRect(
            paddingLeft.toFloat() + so,
            paddingTop.toFloat() + so,
            width.toFloat() - paddingRight - so,
            height.toFloat() -paddingBottom - so,
            paint
        )

    }



}