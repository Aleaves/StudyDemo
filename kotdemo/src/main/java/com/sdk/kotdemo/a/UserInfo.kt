package com.sdk.kotdemo.a

class UserInfo(val onUserNameDone : (String) -> String) {



    fun setOnUserName(){
        var result = onUserNameDone("122121")
        println(result)
    }

}