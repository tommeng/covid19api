package com.tommeng.covid19api.model

data class StateRecord(val date: String,
                       val state: String,
                       val positive: Int,
                       val negative: Int,
                       val pending: Int,
                       val hospitalized: Int,
                       val death: Int,
                       val total: Int,
                       val hash: String,
                       val dateChecked: String,
                       val totalTestResults: Int,
                       val fips: Int,
                       val deathIncrease: Int,
                       val hospitalizedIncrease: Int,
                       val negativeIncrease: Int,
                       val positiveIncrease: Int,
                       val totalTestResultsIncrease: Int)
