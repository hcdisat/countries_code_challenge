package com.example.countriescodechallenge.Views

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.countriescodechallenge.Viewmodel.CountryViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {
    protected val countryViewModel: CountryViewModel by viewModels()

    fun displayError(message: String = "Working on the issues", retry: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("Error has occurred")
            .setPositiveButton("RETRY") { dialog, _ ->
                dialog.dismiss()
                retry()
            }
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
            .setMessage(message)
            .create()
            .show()
    }
}