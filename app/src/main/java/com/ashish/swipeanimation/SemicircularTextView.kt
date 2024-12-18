package com.ashish.swipeanimation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class SemicircularTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFFFFFFFF.toInt()
        textSize = 120f
        style = Paint.Style.FILL_AND_STROKE
        textAlign = Paint.Align.CENTER
        letterSpacing = 0.05f

        setShadowLayer(25f, 0f, 0f, 0xFFFFFFFFF.toInt())
    }

    private val textPath = Path()
    private var text = ""

    init {
        text = context.resources.getString(R.string.headline)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val radius = (minOf(w, h) / 2f) - 20
        textPath.reset()
        textPath.addArc(
            w / 2f - radius,
            h / 2f - radius - 300,
            w / 2f + radius,
            h / 2f + radius,
            -150f,
            120f
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawTextOnPath(text, textPath, 0f, 0f, textPaint)
    }
}
