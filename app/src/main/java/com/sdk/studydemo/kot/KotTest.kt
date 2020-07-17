package com.sdk.studydemo.kot

import com.sdk.studydemo.kot.entity.Student
import com.sdk.studydemo.kot.inter.OnSubDoneListener
import com.sdk.studydemo.kot.ob.Hosts
import com.sdk.studydemo.kot.ob.SingletonDemo
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class KotTest {

    val array1 = arrayOf(1, 2, 3)
    var array2 = intArrayOf()
    var lists = listOf<Int>()

    fun test1() {
        val array = arrayListOf("1", "2", "3")
        for (i in 1 until array.size step 2) {
            print(array[i])
        }

        var a = A();
        a.setOnSubListener(object : OnSubDoneListener{
            override fun onSuccess() {

            }
        })
        a.setName(age = 18)

        var student = Student("nick")
        student.name

        //SingletonDemo.get1()

    }






}

fun main(args: Array<String>) {
    GlobalScope.launch {
        delay(1000)
        println("word !")
    }
    println("hello,")
    runBlocking {
        delay(2000)
    }
}