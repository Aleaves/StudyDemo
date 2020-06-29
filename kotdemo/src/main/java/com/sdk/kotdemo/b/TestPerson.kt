package com.sdk.kotdemo.b

fun main() {

    var person = Person("tom",12)
//    var age = person.let {
//        println(it.name)
//        println(it.age)
//        it.age
//    }

//   var age = with(person){
//       println(name)
//       println(age)
//       age
//   }

//    var name = person?.run {
//        println(name)
//        println(age)
//        name
//    }

//    var p = person.apply {
//        println(name)
//        println(age)
//        name
//    }

    var name = person.also {
        println(it.name)
        print(it.age)
        it.name
    }

}