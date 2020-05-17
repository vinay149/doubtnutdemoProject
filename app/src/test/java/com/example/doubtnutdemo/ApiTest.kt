package com.example.doubtnutdemo

import org.junit.Test
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.Charset


class ApiTest{

    @Test
    fun apiIsAvailable(){
        val connection: URLConnection =
            URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=3d25623102aa4c94be05bf294b36e296").openConnection()
        val response: InputStream = connection.getInputStream()

        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, Charset.defaultCharset())).use { reader ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line)
            }
        }

        assert(buffer.isNotEmpty())
    }
}