package com.doubletapp.sirius.view.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.MotionEvent

class RangeView : BaseLineView {

    private var radius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6f,
            context.resources.displayMetrics)


    var minValue = 10
    var maxValue = 50
    var period = 3
    private var segmentWidth: Float = 0f

    private var isLeftDrag: Boolean = false
    private var isRightDrag: Boolean = false

    //random numbers
    private var leftCircleX = radius
    private var rightCircleX = radius * 15

    private var leftText: String = ""
    private var leftTextX: Float = 0f
    private var rightText: String = ""
    private var rightTextX: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        backgroundPaint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f,
                context.resources.displayMetrics)
        activePaint.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f,
                context.resources.displayMetrics)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("!!!", "onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        segmentWidth = (width / (maxValue - minValue)).toFloat()
        invalidateText()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d("!!!", "onLayout")
        segmentWidth = (width / (maxValue - minValue)).toFloat()
        invalidateText()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        //draw borders
        canvas?.drawCircle(leftCircleX, height -lineCenter, radius, activePaint)
        canvas?.drawCircle(rightCircleX, height -lineCenter, radius, activePaint)
        canvas?.drawRect(leftCircleX, height - lineCenter - lineHeight, rightCircleX, height - lineCenter + lineHeight, activePaint)

        //print text
        canvas?.drawText(leftText, leftTextX, height - lineCenter - radius * 2, activePaint)
        canvas?.drawText(rightText, rightTextX, height - lineCenter - radius * 2, activePaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x?.toInt()
        val y = event?.y?.toInt()
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                x?.let {
                    isLeftDrag = x > offset && x < leftCircleX + (rightCircleX - leftCircleX) / 2
                    isRightDrag = x > leftCircleX + (rightCircleX - leftCircleX) / 2 && x < width
                }
            }
            MotionEvent.ACTION_MOVE -> {
                x?.let {
                    invalidateCircles(it)
                    invalidateText()
                    postInvalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                x?.let {
                    invalidateCircles(it)
                    invalidateText()
                    postInvalidate()
                }
                isLeftDrag = false
                isRightDrag = false
            }
            MotionEvent.ACTION_CANCEL -> {
                isLeftDrag = false
                isRightDrag = false
            }
        }
        return true
    }

    private fun invalidateCircles(x: Int) {
        if (isLeftDrag) {
            val newPos = x - (x % segmentWidth)
            if (newPos > offset && newPos < rightCircleX - radius * 2) {
                leftCircleX = newPos
            }
        }
        if (isRightDrag) {
            val newPos = x - (x % segmentWidth)
            if (newPos > leftCircleX + radius * 2 && newPos < width) {
                rightCircleX = newPos
            }
        }
    }

    private fun invalidateText() {
        val divider = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8f,
                context.resources.displayMetrics)
        leftText = String.format("%.${period}f", leftCircleX / segmentWidth - 1 + minValue)
        val leftTextLength = activePaint.measureText(leftText)
        rightText = String.format("%.${period}f", rightCircleX / segmentWidth - 1 + minValue)
        val rightTextLength = activePaint.measureText(rightText)

        if (rightCircleX - leftCircleX > divider + leftTextLength /2 + rightTextLength /2) {
            //обычный случай
            leftTextX = if (leftCircleX - leftTextLength / 2 < 0) {
                0f
            } else {
                leftCircleX - leftTextLength / 2
            }
            rightTextX = if (rightCircleX + rightTextLength / 2 > width) {
                width - rightTextLength
            } else {
                rightCircleX - rightTextLength / 2
            }
        } else {
            //случай сближения
            leftTextX = leftCircleX + (rightCircleX - leftCircleX) / 2 - divider / 2 - leftTextLength
            rightTextX = leftCircleX + (rightCircleX - leftCircleX) / 2 + divider / 2
        }

    }
}
