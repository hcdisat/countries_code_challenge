package com.example.countriescodechallenge.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriescodechallenge.Model.Country
import com.example.countriescodechallenge.R
import com.example.countriescodechallenge.databinding.CountryItemBinding
import com.squareup.picasso.Picasso

class CountryAdapter(
    private val countriesData: MutableList<Country> = mutableListOf()
) : RecyclerView.Adapter<CountryViewHolder>() {

    fun setNewCountries(newDataSet: List<Country>) {
        countriesData.clear()
        countriesData.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(countriesData[position])

    override fun getItemCount(): Int = countriesData.size
}

class CountryViewHolder(
    private val binding: CountryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country) {
        binding.countryCode.text = country.code
        binding.countryName.text = String.format(country.name + ",")
        binding.countryRegion.text = country.region
        binding.countryCapital.text = country.capital

        Picasso.get()
            .load(country.flag)
            .placeholder(R.drawable.flag_placeholder)
            .error(R.drawable.error_image)
            .fit()
            .into(binding.countryFlag)

    }

}