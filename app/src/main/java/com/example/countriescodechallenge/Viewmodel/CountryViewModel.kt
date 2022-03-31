package com.example.countriescodechallenge.Viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countriescodechallenge.Retrofit.CountriesRepository
import com.example.countriescodechallenge.Utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: CountriesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : BaseViewModel() {

    private val _countries: MutableLiveData<ResponseStatus> = MutableLiveData(ResponseStatus.LOADING())
    val countries: LiveData<ResponseStatus> get() = _countries

    fun getAllCountries() {
        viewModelSafeScope.launch(ioDispatcher) {
            countryRepository.getAllCountries().collect {
                _countries.postValue(it)
            }
        }
    }
}