package com.huixing.financial.test

fun main() {
    val list = listOf(1,2,3,4,5)
    list.apply {
        asReversed().forEach {
            println(it)
        }
        println("=============")
        forEach {
            println(it)
        }
    }
}