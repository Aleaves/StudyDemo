package com.sdk.simpleeyes.ui.profile.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sdk.simpleeyes.R

class ProfileSettingAdapter(data: MutableList<String>?) : BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_profile_setting,data) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper?.setText(R.id.tv_text,item)
    }

}