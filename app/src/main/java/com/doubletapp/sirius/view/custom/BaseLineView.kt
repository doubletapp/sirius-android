package com.doubletapp.sirius.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.doubletapp.sirius.R

open class BaseLineView : View {

    protected var backgroundPaint: Paint = Paint()
    protected var activePaint: Paint = Paint()

    protected var offset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3f,
            context.resources.displayMetrics)
    protected var lineCenter = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
            context.resources.displayMetrics)
    protected var lineHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f,
            context.resources.displayMetrics) //in real it's height/2

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        activePaint.color = ContextCompat.getColor(context, R.color.colorAccent)
        activePaint.style = Paint.Style.FILL
        backgroundPaint.color = ContextCompat.getColor(context, R.color.text_light_gray)
        backgroundPaint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        //draw background view
        canvas?.drawCircle(offset, height - lineCenter, lineHeight, backgroundPaint)
        canvas?.drawCircle(width - offset, height - lineCenter, lineHeight, backgroundPaint)
        canvas?.drawRect(offset, height - lineCenter - lineHeight, width.toFloat() - offset, height - lineCenter + lineHeight, backgroundPaint)
    }
}
