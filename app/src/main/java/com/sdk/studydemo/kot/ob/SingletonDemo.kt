package com.sdk.studydemo.kot.ob

class SingletonDemo private constructor(){

     companion object{
         val instance : SingletonDemo by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {SingletonDemo()  }
     }

}