package com.sdk.kotdemo.b

class User {
    fun setOnDataDoneListener(name:String,mListener: OnDataDoneListener?){
        mListener?.onDataDone(name)
    }

    fun setOnDataDoneListener(mListener: OnDataDoneListener?){
        mListener?.onDataDone("jane")
    }

    fun setOnDataDoneListener1(OnNameDone: (item: String) -> String){
        val name = OnNameDone("macro")
        println(name)
    }

    fun setOnNameDoneListener(mListener: OnCallListener){
        mListener.onNameDone("122")
    }
}