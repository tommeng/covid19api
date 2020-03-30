package com.tommeng.covid19api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tommeng.covid19api.model.StateRecord
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.reflect.Type

@SpringBootApplication
@RestController
class Covid19apiApplication {

    @GetMapping("/state")
    fun stateData(@RequestParam(value = "state", defaultValue = "New York") name: String): String {
        val client = OkHttpClient()
        val request = Request.Builder().url("https://covidtracking.com/api/states/daily").build()
        val stateRecords = client.newCall(request)
                .execute().body?.string()?.takeIf { it.isNotEmpty() }
                ?.let {
                    Gson().fromJson<List<StateRecord>>(it)
                } ?: emptyList()

        return stateRecords
                .filter { it.state.equals(name, ignoreCase = true) }
                .sortedBy { it.date }
                .map { "${it.date}, ${it.positive}, ${it.negative}, ${it.pending}, ${it.hospitalized}, ${it.death}, ${it.total}, ${it.totalTestResults}, ${it.deathIncrease}, ${it.hospitalizedIncrease}, ${it.negativeIncrease}, ${it.positiveIncrease}, ${it.totalTestResultsIncrease}" }
                .joinToString("<br>")
    }
}

fun main(args: Array<String>) {
    runApplication<Covid19apiApplication>(*args)
}
