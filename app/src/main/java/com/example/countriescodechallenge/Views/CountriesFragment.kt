package com.example.countriescodechallenge.Views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriescodechallenge.Adapter.CountryAdapter
import com.example.countriescodechallenge.Model.Country
import com.example.countriescodechallenge.R
import com.example.countriescodechallenge.Utils.ResponseStatus
import com.example.countriescodechallenge.databinding.FragmentCountriesBinding
import dagger.hilt.EntryPoint

class CountriesFragment : BaseFragment() {

    private val binding by lazy {
        FragmentCountriesBinding.inflate(layoutInflater)
    }

    private val countryAdapter by lazy {
        CountryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.countryRV.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = countryAdapter
        }

        countryViewModel.countries.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ResponseStatus.LOADING -> {
                    binding.countryProgress.visibility = View.VISIBLE
                    binding.countryRV.visibility = View.GONE
                }
                is ResponseStatus.SUCCESS -> {
                    binding.countryProgress.visibility = View.GONE
                    binding.countryRV.visibility = View.VISIBLE
                    countryAdapter.setNewCountries(state.countries)
                }
                is ResponseStatus.ERROR -> {
                    binding.countryProgress.visibility = View.GONE
                    binding.countryRV.visibility = View.GONE

                    displayError(state.error.localizedMessage) {
                        countryViewModel.getAllCountries()
                    }
                }
            }
        }

        countryViewModel.getAllCountries()

        return binding.root
    }
}