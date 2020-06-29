package com.sdk.simpleeyes.ui.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import com.facebook.drawee.view.SimpleDraweeView
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.UserPreferences
import com.sdk.simpleeyes.ui.MainActivity
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment
import com.sdk.simpleeyes.utils.bindView
import com.sdk.simpleeyes.utils.dip2px
import com.sdk.simpleeyes.utils.getDateString
import com.sdk.simpleeyes.utils.readyGoThenKillSelf
import com.sdk.simpleeyes.widget.font.CustomFontTextView
import java.util.*

class LocalCommonLandingFragment : BaseAppCompatFragment() {

    private val mIvBackground by bindView<SimpleDraweeView>(R.id.iv_background)
    private val mLoadingContainer by bindView<RelativeLayout>(R.id.rl_loading_container)
    private val mMoveContainer by bindView<RelativeLayout>(R.id.ll_move_container)
    private val mHeadOuter by bindView<ImageView>(R.id.iv_head_outer)
    private val mHeadInner by bindView<ImageView>(R.id.iv_head_inner)
    private val mName by bindView<CustomFontTextView>(R.id.tv_name)

    private val mForToday by bindView<CustomFontTextView>(R.id.tv_today)
    private val mDate by bindView<CustomFontTextView>(R.id.tv_date)
    private val mTodayChose by bindView<CustomFontTextView>(R.id.tv_today_chose)


    companion object {
        fun newInstance(): LocalCommonLandingFragment = LocalCommonLandingFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_local_coomon_landing

    override fun initView(savedInstanceState: Bundle?) {
        //如果用户没有执行上升动画，则执行，反之则执行缩放动画
        if (!UserPreferences.getShowUserAnim()) {
            doUpAnimator()
            doBackgroundAnimator()
        } else {
            doScaleAnimator()
        }
    }
    /**
     * 执行上升动画
     */
    private fun doUpAnimator() {
        val moveY = _mActivity.dip2px(100f)
        ObjectAnimator.ofFloat(mMoveContainer, "translationY", 0f, -moveY.toFloat()).apply {
            addUpdateListener {
                if (it.currentPlayTime in 600..1500) {
                    mHeadOuter.setImageResource(R.drawable.ic_eye_white_outer)
                    mHeadInner.setImageResource(R.drawable.ic_eye_white_inner)
                    mName.setTextColor(resources.getColor(R.color.gray_B7B9B8))

                } else if (it.currentPlayTime in 1500..2000) {
                    mHeadOuter.setImageResource(R.drawable.ic_eye_black_outer)
                    mHeadInner.setImageResource(R.drawable.ic_eye_black_inner)

                    mName.setTextColor(resources.getColor(R.color.black_444444))
                }
            }
            duration = 2000
        }.start()
    }

    /**
     * 执行背景动画
     */
    private fun doBackgroundAnimator() {
        ValueAnimator.ofArgb(0, 0xffffffff.toInt()).apply {
            addUpdateListener { mLoadingContainer.setBackgroundColor(it.animatedValue as Int) }
            doOnEnd { doTextAnimator() }
            duration = 2000
        }.start()
    }

    /**
     * 显示 today 文字与精选
     */
    private fun doTextAnimator() {
        ValueAnimator.ofArgb(0, 0xff444444.toInt()).apply {
            addUpdateListener {
                val color = it.animatedValue as Int
                setTextColor(mForToday, color)
                setTextColor(mDate, color)
                setTextColor(mTodayChose, color)
                mDate.text = getDateString(Date(), "- yyyy/MM/dd -")
            }

            doOnEnd { doInnerEyeAnimator() }
        }.start()
    }

    private fun setTextColor(textView: TextView, color: Int) {
        textView.visibility = View.VISIBLE
        textView.setTextColor(color)
    }

    /**
     * 执行内部眼睛动画
     */
    private fun doInnerEyeAnimator() {
        ObjectAnimator.ofFloat(mHeadInner, "rotation", 0f, 360f).apply {
            doOnEnd {
                readyGoThenKillSelf<MainActivity>()
                UserPreferences.saveShowUserAnim(true)
            }
            duration = 1000
        }.start()
    }
    /**
     * 执行背景缩放动画
     */
    private fun doScaleAnimator() {

        AnimatorSet().apply {
            val scaleX = ObjectAnimator.ofFloat(mIvBackground, "scaleX", 1f, 1.08f)
            val scaleY = ObjectAnimator.ofFloat(mIvBackground, "scaleY", 1f, 1.08f)
            playTogether(scaleX, scaleY)
            doOnEnd {
                readyGoThenKillSelf<MainActivity>()
                UserPreferences.saveShowUserAnim(true)
            }
            duration = 2000
        }.start()
    }

}