package com.sdk.kotdemo.f

import com.sdk.kotdemo.f.G.Companion.getName

class Test1 {

    fun setName(name: String): Int {
        return 0
    }

    fun getSum(a: Int, b: Int) = a + b

    fun main(args: Array<String>) {
        println("21112")
    }


    fun test1() {
        var a: Int
        var b: String
        val c: String
        arrayOf(1, 2, 3)
    }

    fun test2(a: Int): String {
        when (a) {
            1 -> {
                return "one"
            }
            2 -> {
                return "two"
            }
            else -> {
                return "default"
            }
        }

    }

    fun test2() {

        val lists = arrayOf(1, 2, 3, 4)
        lists.forEach {
            println(it)
        }

        val list1 = listOf<Int>(1, 2, 3, 4)
        for (i in list1) {
            println(i)
        }


        for (i in 1..100) {
            println(i)
        }

        listOf(1,2,3,4,5).forEach {
            println(it)
        }
        getName()

    }


}