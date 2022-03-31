package com.example.countriescodechallenge.Retrofit

import com.example.countriescodechallenge.Utils.NullResponseException
import com.example.countriescodechallenge.Utils.ResponseIsAFailure
import com.example.countriescodechallenge.Utils.ResponseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface CountriesRepository {
    fun getAllCountries(): Flow<ResponseStatus>
}

class CountriesRepositoryImpl @Inject constructor(
    private val countryService: CountryService
) : CountriesRepository {

    override fun getAllCountries(): Flow<ResponseStatus> =
        flow {
            emit(ResponseStatus.LOADING())

            try {
                val response = countryService.getCountries()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ResponseStatus.SUCCESS(it))
                    } ?: throw NullResponseException()
                } else {
                    throw ResponseIsAFailure()
                }
            } catch (e: Exception) {
                emit(ResponseStatus.ERROR(e))
            }
        }
}