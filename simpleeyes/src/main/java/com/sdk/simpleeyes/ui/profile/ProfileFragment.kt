package com.sdk.simpleeyes.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.ui.base.BaseFragment
import com.sdk.simpleeyes.ui.profile.adapter.ProfileSettingAdapter
import com.sdk.simpleeyes.ui.profile.presenter.ProfilePresenter
import com.sdk.simpleeyes.ui.profile.view.ProfileView
import com.sdk.simpleeyes.utils.bindView

class ProfileFragment : BaseFragment<ProfileView,ProfilePresenter>(),ProfileView, View.OnClickListener {

    private val mRecycler: RecyclerView by bindView(R.id.rv_profile_recycler)
    private val mIvAvatar: SimpleDraweeView by bindView(R.id.iv_avatar)
    private val mFlComment: FrameLayout by bindView(R.id.fl_comment_container)

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment = ProfileFragment()

        private const val MESSAGE = 0  //我的消息
        private const val FOCUS = 1 //我的关注
        private const val CACHE_POSITION = 2//我的缓存
        private const val WATH_HISTORY = 3//观看记录

    }

    override fun getContentViewLayoutId(): Int = R.layout.fragment_profile

    override fun initView(savedInstanceState: Bundle?) {

        mIvAvatar.setOnClickListener(this)
        mFlComment.setOnClickListener(this)
        mRecycler.apply {
            val stringArray = resources.getStringArray(R.array.profile_setting)
            adapter = ProfileSettingAdapter(arrayListOf(*stringArray))
                    .apply {

                    }
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_avatar,
            R.id.fl_comment_container -> {//跳转到登录界面
            }
        }
    }
}