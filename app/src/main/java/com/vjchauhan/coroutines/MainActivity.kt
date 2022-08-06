package com.vjchauhan.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //  IO for Background
        CoroutineScope(Dispatchers.IO).launch {
            data()
        }

        //  Main for UI
        CoroutineScope(Dispatchers.Main).launch {
            data()
        }

        CoroutineScope(Main).launch {
        //    Async and await is used to call threads simultaniously

            val stock1= async(IO) {getStock1()}
            val stock2= async(IO) {getStock2()}

            val total= stock1.await()  + stock2.await()
            Toast.makeText(this@MainActivity,""+total,Toast.LENGTH_LONG).show()
        }

    }

    /*suspending fuction is can be invoked from coroutine block or another suspending fuction
     A suspending function doesn't block a thread,
        but only suspends the coroutine itself. (one thread can have more coroutines)
        The thread is returned to the pool while the coroutine is waiting,
         and when the waiting is done, the coroutine resumes on a free thread in the pool.
     */

    suspend fun data()
    {

        //withContect is one of suspending functio
        // which is used to switch from main to io or io to main
        withContext(Dispatchers.Main)
        {

        }
    }


    suspend fun getStock1():Int
    {
        delay(2000)
        return 500
    }
    suspend fun getStock2():Int
    {
        delay(5000)
        return 1000
    }
}