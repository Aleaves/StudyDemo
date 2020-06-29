package com.sdk.kotdemo.map

fun main() {
    //println(reverse(-446464))
    println(isPalindrome(-12321))
}

fun reverse(x: Int): Int {
    var n: Int = 0
    var nx = x
    while (nx != 0) {
        n = n * 10 + nx % 10
        nx /= 10
    }
    return n
}

fun isPalindrome(x: Int): Boolean {
    if (x < 0) {
        return false
    }
    var n: Int = 0
    var nx = x
    while (nx != 0) {
        n = n * 10 + nx % 10
        nx /= 10
    }
    return x == n
}
