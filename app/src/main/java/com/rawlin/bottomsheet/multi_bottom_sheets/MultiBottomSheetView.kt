package com.rawlin.bottomsheet.multi_bottom_sheets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.rawlin.bottomsheet.R

class MultiBottomSheetView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?,
    defaultStyle: Int = 0
) : CoordinatorLayout(context, attributeSet, defaultStyle) {

    @SuppressLint("ClickableViewAccessibility")
    override fun onFinishInflate() {
        super.onFinishInflate()

        val sheet1 = findViewById<View>(R.id.bottom_sheet1)
        val sheet2 = findViewById<View>(R.id.bottom_sheet2)

        val behaviourSheet1 = BottomSheetBehavior.from(sheet1) as MultiBottomSheetBehaviour
        val behaviourSheet2 = BottomSheetBehavior.from(sheet2) as MultiBottomSheetBehaviour

        behaviourSheet2.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED || newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behaviourSheet1.setAllowDragging(false)
                } else {
                    behaviourSheet1.setAllowDragging(true)
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                behaviourSheet1.setAllowDragging(false)
            }
        })

        findViewById<FrameLayout>(R.id.sheet1PeekView).setOnClickListener {
            behaviourSheet1.state = BottomSheetBehavior.STATE_EXPANDED
        }



        findViewById<FrameLayout>(R.id.sheet2PeekView).setOnTouchListener { v, event ->
            behaviourSheet1.setAllowDragging(false)
            behaviourSheet2.setAllowDragging(true)
            false
        }


    }


}