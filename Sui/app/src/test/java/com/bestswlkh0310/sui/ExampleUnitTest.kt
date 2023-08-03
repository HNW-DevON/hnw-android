package com.bestswlkh0310.sui

import android.util.Log
import com.bestswlkh0310.sui.data.req.LoginRequest
import com.bestswlkh0310.sui.data.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() = runBlocking {
        val a = RetrofitClient.retrofit
        val b = CoroutineScope(Dispatchers.IO).launch {
            val d = a.login(
                LoginRequest(
                    id = "b",
                    pw = "c"
                )
            )
            Log.d("TAG", "${d.id}ExampleUnitTest - addition_isCorrect() called")
        }
        b.join()
    }
}