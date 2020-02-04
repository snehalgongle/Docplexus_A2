package com.snake.docplexus

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val arr = intArrayOf(0, 10, 2, -10, -20)
        val arr_size = arr.size
        val missing = findMissing(arr, arr_size)
        println("The smallest positive missing number is $missing")
    }


    fun segregate(arr:IntArray, size:Int):Int {
        var j = 0
        var i:Int
        i = 0
        while (i < size)
        {
            if (arr[i] <= 0)
            {
                val temp:Int
                temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
                // increment count of non-positive
                // integers
                j++
            }
            i++
        }
        return j
    }
    /* Find the smallest positive missing
     number in an array that contains
     all positive integers */
    fun findMissingPositive(arr:IntArray, size:Int):Int {
        var i:Int
        // Mark arr[i] as visited by making
        // arr[arr[i] - 1] negative. Note that
        // 1 is subtracted because index start
        // from 0 and positive numbers start from 1
        i = 0
        while (i < size)
        {
            val x = Math.abs(arr[i])
            if (x - 1 < size && arr[x - 1] > 0)
                arr[x - 1] = -arr[x - 1]
            i++
        }
        // Return the first index value at which
        // is positive
        i = 0
        while (i < size)
        {
            if (arr[i] > 0)
                return i + 1
            i++
        } // 1 is added becuase indexes
        // start from 0
        return size + 1
    }
    /* Find the smallest positive missing
     number in an array that contains
     both positive and negative integers */
    fun findMissing(arr:IntArray, size:Int):Int {
        // First separate positive and
        // negative numbers
        val shift = segregate(arr, size)
        val arr2 = IntArray(size - shift)
        var j = 0
        for (i in shift until size)
        {
            arr2[j] = arr[i]
            j++
        }
        // Shift the array and call
        // findMissingPositive for
        // positive part
        return findMissingPositive(arr2, j)
    }
}
