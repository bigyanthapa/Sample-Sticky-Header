package com.concept.stickyheader.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ListView

/**
 * Help taken from : https://stackoverflow.com/questions/33961702/set-listview-height-to-display-all-available-items
 */
class ScrollDisabledListView : ListView {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measuredHeight = MeasureSpec.makeMeasureSpec(
            Int.MAX_VALUE shr 2, MeasureSpec.AT_MOST
        )
        super.onMeasure(widthMeasureSpec, measuredHeight)
        val params: ViewGroup.LayoutParams = layoutParams
        params.height = measuredHeight
    }
}