package com.sdk.kotdemo.f

class D(name:String,age:Int,  val onDone:(item:String) -> Unit) :C(name,age) {

    private var street: String = ""
    var city: String = ""
    var state: String? = ""
    var zip: String = ""


    fun setOnNetCallBack(onNetCallBack: OnNetCallBack){
        onNetCallBack.OnSuccess()
    }

    fun setA(onNameListener : (item :String) -> Unit){
        onNameListener("123")
        onDone("12343")

    }

}