package com.sdk.simpleeyes.ui.splash

import android.media.MediaPlayer
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewpager.widget.ViewPager
import com.rd.PageIndicatorView
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.UserPreferences
import com.sdk.simpleeyes.player.render.IRenderView.Companion.AR_ASPECT_FIT_PARENT
import com.sdk.simpleeyes.ui.MainActivity
import com.sdk.simpleeyes.ui.base.BaseAppCompatFragment
import com.sdk.simpleeyes.ui.splash.adapter.SplashVideoFragmentAdapter
import com.sdk.simpleeyes.utils.bindView
import com.sdk.simpleeyes.utils.readyGoThenKillSelf
import com.sdk.simpleeyes.widget.FullScreenVideoView
import com.sdk.simpleeyes.widget.font.CustomFontTypeWriterTextView
import com.sdk.simpleeyes.widget.viewpager.InterceptVerticalViewPager

class VideoLandingFragment : BaseAppCompatFragment() {

    private val mVideoView: FullScreenVideoView by bindView(R.id.video_view)
    private val mViewPager: InterceptVerticalViewPager by bindView(R.id.view_pager)
    private val mTvSloganChinese: CustomFontTypeWriterTextView by bindView(R.id.tv_slogan_zh)
    private val mTvSloganEnglish: CustomFontTypeWriterTextView by bindView(R.id.tv_slogan_en)
    private val mIndicator: PageIndicatorView by bindView(R.id.pageIndicatorView)

    private var mVideoPosition = 0
    private var isHasPaused = false
    private lateinit var mFragmentList: MutableList<SloganFragment>

    companion object {
        fun newInstance(): VideoLandingFragment = VideoLandingFragment()
    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_video_landing

    override fun initView(savedInstanceState: Bundle?) {
        initSloganText()
        setVideoObserver()
        playVideo()
    }

    private fun initSloganText() {
        //设置初始标语
        mTvSloganEnglish.printText(resources.getStringArray(R.array.slogan_array_en)[0])
        mTvSloganChinese.printText(resources.getStringArray(R.array.slogan_array_zh)[0])

        mIndicator.count = 4

        mFragmentList = List(4) {
            SloganFragment.newInstance()
        } as MutableList<SloganFragment>

        with(mViewPager) {
            verticalListener = {
                goMainActivity()
            }
            horizontalListener = {
                goMainActivity()
            }
            mDisMissIndex = mFragmentList.size - 1
            adapter = SplashVideoFragmentAdapter(mFragmentList,childFragmentManager)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
                override fun onPageScrollStateChanged(state: Int) {

                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    mIndicator.setSelected(position)
                }

                override fun onPageSelected(position: Int) {
                    if(position in 0..3){
                        mTvSloganEnglish.printText(resources.getStringArray(R.array.slogan_array_en)[position])
                        mTvSloganChinese.printText(resources.getStringArray(R.array.slogan_array_zh)[position])
                    }
                }
            })
        }
    }

    private fun setVideoObserver(){
        lifecycle.addObserver(object : LifecycleObserver{
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onVideoResume() {
                if (isHasPaused) {
                    mVideoView.seekTo(mVideoPosition)
                    mVideoView.resume()
                    isHasPaused = false
                }
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
            fun onVideoPause() {
                mVideoPosition = mVideoView.currentPosition
                mVideoView.pause()
                isHasPaused = true
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onVideoStop() {
                mVideoView.stopPlayback()
            }

        })
    }

    private fun playVideo(){
        val path = R.raw.landing
        with(mVideoView){
            setAspectRatio(AR_ASPECT_FIT_PARENT)
            setVideoPath("android.resource://${activity?.packageName}/$path")
            setOnPreparedListener{
                requestFocus()
                seekTo(0)
                start()
            }
            setOnCompletionListener {
                it.isLooping = true
                start()
            }
        }
    }

    private fun goMainActivity() {
        UserPreferences.saveUserIsFirstLogin(false)
        readyGoThenKillSelf<MainActivity>()
    }
}