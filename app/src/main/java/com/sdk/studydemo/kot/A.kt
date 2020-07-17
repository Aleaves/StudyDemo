package com.sdk.studydemo.kot

import com.sdk.studydemo.kot.inter.OnSubDoneListener

class A {
    companion object{
        val name = "kangkang"
        fun getAge():String{

            return name
        }
    }

    fun setOnSubListener(listener:OnSubDoneListener){
        listener.onSuccess()
    }

    fun setName(name :String = "1223",age:Int){

    }

}