package com.example.countriescodechallenge.Retrofit

import com.example.countriescodechallenge.Model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {

    @GET(COUNTRIES_PATH)
    suspend fun getCountries(): Response<List<Country>>

    companion object {
        const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        // url = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json"
        private const val COUNTRIES_PATH = "countries.json"
    }
}