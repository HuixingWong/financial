package com.huixing.financial

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException as Ru

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_equal() {
        val a = 200
        assert(a == 200)
    }

    @Test
    fun testFlow() {
        GlobalScope.launch {

            flowOf(1)
                .onEach {
                    throw RuntimeException("哈哈")
                }.catch {
                    println(it.message)
                }.onCompletion {
                    println("完成了")
                }.collect {
                    println(it)
                }

        }
        Thread.sleep(10000)
    }
}