package com.sdk.simpleeyes.ui.video

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sdk.simpleeyes.R
import com.sdk.simpleeyes.entity.Content
import com.sdk.simpleeyes.entity.ContentBean
import com.sdk.simpleeyes.net.Extras
import com.sdk.simpleeyes.ui.base.BaseAppCompatActivity

class VideoDetailActivity : BaseAppCompatActivity() {

    companion object{
        @JvmStatic
        fun start(context: Context, content: ContentBean, videoListInfo: ArrayList<Content>, defaultIndex: Int = 0) {
            val bundle = Bundle()
            bundle.putSerializable(Extras.VIDEO_INFO, content)
            bundle.putSerializable(Extras.VIDEO_LIST_INFO, videoListInfo)
            bundle.putInt(Extras.VIDEO_INFO_INDEX, defaultIndex)
            val starter = Intent(context, VideoDetailActivity::class.java)
            starter.putExtras(bundle)
            context.startActivity(starter)
        }
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun getContentViewLayoutId(): Int = R.layout.activity_video_detail

}