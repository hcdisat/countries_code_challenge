package com.example.countriescodechallenge.Utils

import com.example.countriescodechallenge.Model.Country

sealed interface ResponseStatus {
    class LOADING(val isLoading: Boolean = true) : ResponseStatus
    class SUCCESS(val countries: List<Country>) : ResponseStatus
    class ERROR(val error: Throwable) : ResponseStatus
}