package com.mikhailn45.leroymerlinclone

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlin.math.max
import kotlin.math.min

class OverScrollBehavior() : AppBarLayout.Behavior() {
    constructor(ctx: Context, attrs: AttributeSet) : this()

    private lateinit var targetView: View
    private lateinit var collapsingView: CollapsingToolbarLayout
    private var targetHeight: Int = 0
    private var parentHeight: Int = 0
    private var totalDy: Int = 0
    private var lastScale: Float = 0f
    private var lastBottom: Int = 0
    private var isStopped: Boolean = false


    override fun onLayoutChild(
        parent: CoordinatorLayout,
        abl: AppBarLayout,
        layoutDirection: Int
    ): Boolean {
        val superLayout: Boolean = super.onLayoutChild(parent, abl, layoutDirection)
        if (!::targetView.isInitialized) initialize(abl)
        return superLayout
    }

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        isStopped = false
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        abl: AppBarLayout,
        target: View,
        type: Int
    ) {
        isStopped = true
        //restore size if scroll ends
        restore(abl)
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        val ablBottom = child.bottom
        //scale if scroll down and scroll up before scroll ends
        if ((dy > 0 && ablBottom >= parentHeight) || (dy > 0 && ablBottom > parentHeight)) {
            scale(child, dy)
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }


    private fun initialize(abl: AppBarLayout) {
        targetView = abl.findViewById(R.id.iv_product)
        collapsingView = abl.getChildAt(0) as CollapsingToolbarLayout
        parentHeight = abl.height
        targetHeight = targetView.height

    }

    //restore appbar size
    private fun restore(abl: AppBarLayout) {
        if (totalDy > 0) {
            totalDy = 0
            val anim = ValueAnimator.ofFloat(lastScale, 1f)
            anim.addUpdateListener {
                val value = it.animatedValue as Float
                targetView.scaleX = value
                targetView.scaleY = value
                val bottomValue =
                    (lastBottom - (lastBottom - parentHeight) * it.animatedFraction).toInt()
                abl.bottom = bottomValue
                collapsingView.bottom = bottomValue
            }
            anim.start()
        }
    }

    //scale appbar and search
    private fun scale(abl: AppBarLayout, dY: Int) {
        //don't scale if scroll ends
        if (isStopped) return
        totalDy += -dY
        totalDy = min(totalDy, targetHeight)
        lastScale = max(1f, 1f + totalDy.toFloat() / targetHeight)
        targetView.scaleX = lastScale
        targetView.scaleY = lastScale

        lastBottom = parentHeight + (targetHeight / 2 * (lastScale - 1)).toInt()
        abl.bottom = lastBottom

    }
}