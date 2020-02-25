package com.sdk.kotdemo.f

class TestFun {

    var c = 1
    var d = 2

    fun sum(a: Int, b: Int): Int {
        println("$c")
        return a + b
    }


    fun plus(a: Int, b: Int) = a + b

    fun test1() {
        val items = listOf("apple", "banana", "kiwifruit")
        for (item in items) {
            println(item)
        }

        while (1 < 4) {

        }

        when (1) {
            1 -> ""
            2 -> ""
        }

    }

    fun test2() {
        var items: Array<String> = arrayOf("1", "2", "3")
        var items1: ArrayList<String> = ArrayList()
        items1.add("1")
        items1.add("2")

        var string = "123435"
        for (c in string) {
            println("==========" + c)
        }

    }

    val a = 1
    val b = 2

    var max = if (a > b) a else b

}