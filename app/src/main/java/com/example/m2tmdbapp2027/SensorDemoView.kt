package com.example.m2tmdbapp2027

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.hardware.SensorEvent
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private const val DIAL_SCALE = 0.9f

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

    // Paint styles used for rendering are initialized here to improve performance,
    // since onDraw() is called for every screen refresh.
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 4f
        textAlign = Paint.Align.CENTER
        color = dialColor
        textSize = context.resources.getDimension(R.dimen.sensor_view_caption)
        typeface = Typeface.create(null as String?, Typeface.NORMAL)
    }

    // compute dimensions to be used for drawing text
    private val fontMetrics = paint.fontMetrics
    // ascent is negative (above the baseline) and descent positive (below it)
    private val textHeight = fontMetrics.descent - fontMetrics.ascent
    private val captionHeight = textHeight * 2f

    // size wanted for the dial itself, computed once
    private val dialSize = context.resources.getDimensionPixelSize(R.dimen.sensor_view_dial_size)

    init {
        // Load attributes
        context.withStyledAttributes(attrs, R.styleable.SensorDemoView, defStyleAttr, 0) {
            dialCaption = getString(R.styleable.SensorDemoView_dialCaption) ?:
                    if (isInEditMode) context.getString(R.string.sensor_unknown) else ""
            dialColor = getColor(R.styleable.SensorDemoView_dialColor, dialColor)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        // size wanted by the view itself, independent of what the parent offers:
        // a square dial with the caption above it
        val desiredWidth = dialSize
        val desiredHeight = dialSize + captionHeight.toInt()

        // resolveSize() applies the measure spec rules:
        // EXACTLY -> spec size, AT_MOST -> min(desired, spec), UNSPECIFIED -> desired
        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // compute circle radius and center coordinates
        val cx = measuredWidth * 0.5f
        val cy = (measuredHeight + captionHeight) * 0.5f
        val radius = min(measuredWidth.toFloat(), measuredHeight - captionHeight) * 0.5f * DIAL_SCALE

        // draw the dial circle
        paint.style = Paint.Style.STROKE
        paint.color = dialColor
        canvas.drawCircle(cx, cy, radius, paint)

        sensorEvent?.let {
            val maxRange = it.sensor.maximumRange
            if (maxRange > 0f) {
                for (i in 0..<min(it.values.size, dialNeedleColors.size)) {
                    paint.color = dialNeedleColors[i]
                    val angle = ((it.values[i] * 2 * PI) / maxRange)
                    canvas.drawLine(
                        cx,
                        cy,
                        cx + radius * sin(angle).toFloat(),
                        cy - radius * cos(angle).toFloat(),
                        paint)
                }
            }
        }

        // draw dial caption
        paint.style = Paint.Style.FILL
        paint.color = dialColor
        canvas.drawText(
            dialCaption,
            cx,
            textHeight * 1.5f,
            paint
        )

    }

}