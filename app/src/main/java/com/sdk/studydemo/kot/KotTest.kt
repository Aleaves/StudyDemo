package com.sdk.studydemo.kot

class KotTest {

    val array1 = arrayOf(1, 2, 3)
    var array2 = intArrayOf()
    var lists = listOf<Int>()

    fun test1() {
        val array = arrayListOf("1", "2", "3")
        for (i in 1 until array.size step 2) {
            print(array[i])
        }

    }

}