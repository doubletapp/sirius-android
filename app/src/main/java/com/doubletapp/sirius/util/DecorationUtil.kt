package com.doubletapp.sirius.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class DecorationUtil {

    interface StickyHeaderInterface {

        fun getHeaderPositionForItem(itemPosition: Int): Int

        fun getHeaderLayout(headerPosition: Int): Int

        fun isHeader(itemPosition: Int): Boolean

        fun bindHeaderData(header: View, headerPosition: Int)
    }

    /**
     * Utility class for adding spaces and dividers between RecyclerView items
     */
    class SiriusItemDecoration private constructor(private val mListener: StickyHeaderInterface?) :
        RecyclerView.ItemDecoration() {

        private val mPaint = Paint()
        private var mOffset: Int = 0
        private var mSidesOffset: Int = 0
        var mStickyHeaderHeight: Int = 0

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val childs = parent.adapter!!.itemCount
            if (parent.layoutManager is GridLayoutManager) {
                when ((parent.layoutManager as GridLayoutManager).orientation) {
                    RecyclerView.HORIZONTAL -> if (position < 2) {
                        outRect.set(mSidesOffset, mOffset / 2, mOffset / 2, mOffset / 2)
                    } else if (childs % 2 == 0 && position > childs - 3 || childs % 2 == 1 && position > childs - 2) {
                        outRect.set(mOffset / 2, mOffset / 2, mSidesOffset, mOffset / 2)
                    } else {
                        outRect.set(mOffset / 2, mOffset / 2, mOffset / 2, mOffset / 2)
                    }
                    RecyclerView.VERTICAL -> if (position < 2) {
                        outRect.set(mOffset / 2, mSidesOffset, mOffset / 2, mOffset / 2)
                    } else if (childs % 2 == 0 && position > childs - 3 || childs % 2 == 1 && position > childs - 2) {
                        outRect.set(mOffset / 2, mOffset / 2, mOffset / 2, mSidesOffset)
                    } else {
                        outRect.set(mOffset / 2, mOffset / 2, mOffset / 2, mOffset / 2)
                    }
                }

            } else if (parent.layoutManager is LinearLayoutManager) {
                when ((parent.layoutManager as LinearLayoutManager).orientation) {
                    RecyclerView.HORIZONTAL -> if (position == 0) {
                        outRect.set(mSidesOffset, 0, mOffset, 0)
                    } else if (position == childs - 1) {
                        outRect.set(0, 0, mSidesOffset, 0)
                    } else {
                        outRect.set(0, 0, mOffset, 0)
                    }
                    RecyclerView.VERTICAL -> if (position == 0) {
                        outRect.set(0, mSidesOffset, 0, mOffset)
                    } else if (position == childs - 1) {
                        outRect.set(0, 0, 0, mSidesOffset)
                    } else {
                        outRect.set(0, 0, 0, mOffset)
                    }
                }

            }
        }

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            if (parent.layoutManager is GridLayoutManager) {
                when ((parent.layoutManager as GridLayoutManager).orientation) {
                    RecyclerView.HORIZONTAL -> drawGridHorizontal(c, parent)
                    RecyclerView.VERTICAL -> drawGridVertical(c, parent)
                }
            } else if (parent.layoutManager is LinearLayoutManager) {
                when ((parent.layoutManager as LinearLayoutManager).orientation) {
                    RecyclerView.HORIZONTAL -> drawHorizontal(c, parent)
                    RecyclerView.VERTICAL -> drawVertical(c, parent)
                }

            }
        }

        override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDrawOver(c, parent, state)
            if (mListener != null) {
                val topChild = parent.getChildAt(0) ?: return

                val topChildPosition = parent.getChildAdapterPosition(topChild)
                if (topChildPosition == RecyclerView.NO_POSITION) {
                    return
                }

                val headerPos = mListener.getHeaderPositionForItem(topChildPosition)
                val currentHeader = getHeaderViewForItem(headerPos, parent)
                if (currentHeader != null) {
                    fixLayoutSize(parent, currentHeader)
                    val contactPoint = currentHeader.bottom
                    val childInContact = getChildInContact(parent, contactPoint, headerPos)

                    if (childInContact != null && mListener.isHeader(parent.getChildAdapterPosition(childInContact))) {
                        moveHeader(c, currentHeader, childInContact)
                        return
                    }
                    drawHeader(c, currentHeader)
                }
            }
        }

        private fun drawHeader(c: Canvas, header: View) {
            c.save()
            c.translate(0f, 0f)
            header.draw(c)
            c.restore()
        }

        private fun moveHeader(
            c: Canvas,
            currentHeader: View,
            nextHeader: View
        ) {
            c.save()
            c.translate(0f, (nextHeader.top - currentHeader.height).toFloat())
            currentHeader.draw(c)
            c.restore()
        }

        private fun getHeaderViewForItem(headerPosition: Int, parent: RecyclerView): View? {
            val layoutResId = mListener!!.getHeaderLayout(headerPosition)
            if (layoutResId == -1) {
                return null
            }
            val header = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
            mListener.bindHeaderData(header, headerPosition)
            return header
        }

        private fun fixLayoutSize(parent: ViewGroup, view: View) {

            // Specs for parent (RecyclerView)
            val widthSpec = View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY)
            val heightSpec = View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.UNSPECIFIED)

            // Specs for children (headers)
            val childWidthSpec = ViewGroup.getChildMeasureSpec(
                widthSpec,
                parent.paddingLeft + parent.paddingRight,
                view.layoutParams.width
            )
            val childHeightSpec = ViewGroup.getChildMeasureSpec(
                heightSpec,
                parent.paddingTop + parent.paddingBottom,
                view.layoutParams.height
            )

            view.measure(childWidthSpec, childHeightSpec)

            view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        }

        private fun getChildInContact(
            parent: RecyclerView,
            contactPoint: Int,
            currentHeaderPos: Int
        ): View? {
            var childInContact: View? = null
            for (i in 0 until parent.childCount) {
                var heightTolerance = 0
                val child = parent.getChildAt(i)

                //measure height tolerance with child if child is another header
                if (currentHeaderPos != i) {
                    val isChildHeader = mListener!!.isHeader(parent.getChildAdapterPosition(child))
                    if (isChildHeader) {
                        heightTolerance = mStickyHeaderHeight - child.height
                    }
                }

                //add heightTolerance if child top be in display area
                val childBottomPosition: Int
                if (child.top > 0) {
                    childBottomPosition = child.bottom + heightTolerance
                } else {
                    childBottomPosition = child.bottom
                }

                if (childBottomPosition > contactPoint) {
                    if (child.top <= contactPoint) {
                        // This child overlaps the contactPoint
                        childInContact = child
                        break
                    }
                }
            }
            return childInContact
        }

        private fun drawGridVertical(canvas: Canvas, parent: RecyclerView) {
            val itemCount = parent.childCount
            val offset = mOffset / 2

            for (i in 0 until itemCount) {
                val child = parent.getChildAt(i)
                val left = child.left - offset
                var top = child.top - offset
                val right = child.right + offset
                var bottom = child.bottom + offset
                if (parent.getChildAdapterPosition(child) < 2) {
                    top = child.top
                }
                if (parent.adapter!!.itemCount % 2 == 0 && parent.getChildAdapterPosition(child) > parent.adapter!!.itemCount - 3 || parent.adapter!!.itemCount % 2 == 1 && parent.getChildAdapterPosition(
                        child
                    ) > parent.adapter!!.itemCount - 2
                ) {
                    bottom = child.bottom
                } else if (parent.adapter!!.itemCount % 2 == 1 && parent.getChildAdapterPosition(child) > parent.adapter!!.itemCount - 2) {
                    bottom = child.bottom
                }
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }

        private fun drawGridHorizontal(canvas: Canvas, parent: RecyclerView) {
            val itemCount = parent.childCount
            val offset = mOffset / 2
            for (i in 0 until itemCount) {
                val child = parent.getChildAt(i)
                var left = child.left - offset
                val top = child.top - offset
                var right = child.right + offset
                val bottom = child.bottom + offset
                if (parent.getChildAdapterPosition(child) < 2) {
                    left = child.left
                }
                if (parent.adapter!!.itemCount % 2 == 0 && parent.getChildAdapterPosition(child) > parent.adapter!!.itemCount - 3) {
                    right = child.right
                } else if (parent.adapter!!.itemCount % 2 == 1 && parent.getChildAdapterPosition(child) > parent.adapter!!.itemCount - 2) {
                    right = child.right
                }
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }

        private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
            val left = parent.paddingLeft
            val right = parent.width - parent.paddingRight

            val itemCount = parent.childCount

            for (i in 0 until itemCount) {
                val child = parent.getChildAt(i)
                val top = child.bottom
                var bottom = child.bottom + mOffset

                if (parent.getChildAdapterPosition(child) == parent.adapter!!.itemCount - 1) {
                    bottom = child.bottom
                }
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }

        private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
            val top = parent.paddingTop
            val bottom = parent.height - parent.paddingBottom

            val itemCount = parent.childCount

            for (i in 0 until itemCount) {
                val child = parent.getChildAt(i)
                val left = child.right
                var right = child.right + mOffset

                if (parent.getChildAdapterPosition(child) == parent.adapter!!.itemCount - 1) {
                    right = child.right
                }
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }

        private fun setOffset(offset: Int) {
            mOffset = offset
        }

        private fun setSidesOffset(sidesOffset: Int) {
            mSidesOffset = sidesOffset
        }

        private fun setColor(@ColorInt color: Int) {
            mPaint.color = color
        }

        class Builder {

            @ColorRes
            private var mColor = -1
            private var mContext: Context? = null
            @DimenRes
            private var mOffset = -1
            @DimenRes
            private var mSidesOffset = -1
            private var mListener: StickyHeaderInterface? = null

            /**
             * Sets a color of the divider
             *
             * @param color Color of the divider (can be transparent)
             */
            fun setColor(@ColorRes color: Int): Builder {
                mColor = color
                return this
            }

            fun setContext(context: Context): Builder {
                mContext = context
                return this
            }

            /**
             * Sets the space between RecyclerView items
             *
             * @param offset Space between items of the RecyclerView
             */
            fun setOffset(@DimenRes offset: Int): Builder {
                mOffset = offset
                return this
            }

            /**
             * Sets the space before the first item and after the last item of the RecyclerView
             *
             * @param sidesOffset space before the first item and after the last item of the RecyclerView
             */
            fun setSidesOffset(@DimenRes sidesOffset: Int): Builder {
                mSidesOffset = sidesOffset
                return this
            }

            fun setHeaderListener(listener: StickyHeaderInterface): Builder {
                mListener = listener
                return this
            }

            fun build(): SiriusItemDecoration {
                val adventureItemDecoration = SiriusItemDecoration(mListener)
                if (mContext != null) {
                    if (mColor != -1) {
                        adventureItemDecoration.setColor(ResourcesCompat.getColor(mContext!!.resources, mColor, null))
                    }
                    if (mOffset != -1) {
                        adventureItemDecoration.setOffset(mContext!!.resources.getDimensionPixelOffset(mOffset))
                    }
                    if (mSidesOffset != -1) {
                        adventureItemDecoration.setSidesOffset(mContext!!.resources.getDimensionPixelOffset(mSidesOffset))
                    }
                }
                return adventureItemDecoration
            }
        }

        companion object {

            val builder: Builder
                get() = SiriusItemDecoration.Builder()
        }
    }
}