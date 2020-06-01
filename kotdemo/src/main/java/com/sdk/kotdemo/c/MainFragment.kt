package com.sdk.kotdemo.c

class MainFragment {

    companion object{

        private val ins :MainFragment by lazy {
            MainFragment()
        }

        fun getInstance(): MainFragment{
            return ins
        }
    }

    private object MainFragmentHolder{
        val instance = MainFragment()
    }

}