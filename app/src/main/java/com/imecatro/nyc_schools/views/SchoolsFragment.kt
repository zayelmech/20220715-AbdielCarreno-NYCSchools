package com.imecatro.nyc_schools.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import com.imecatro.nyc_schools.R
import com.imecatro.nyc_schools.adapter.SchoolsAdapter
import com.imecatro.nyc_schools.databinding.FragmentSchoolsfragmentBinding
import com.imecatro.nyc_schools.model.Schools
import com.imecatro.nyc_schools.utils.UIState


class SchoolsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolsfragmentBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", "loading")

                }
                is UIState.SUCCESS<*> -> {
                    (state as UIState.SUCCESS<List<Schools>>).response

                    state.response.let {
                        binding.apply {
                            schoolsList.adapter = SchoolsAdapter(it, ::showDetails)
                            schoolsList.layoutManager =LinearLayoutManager(context)
                        }
                    }

                    Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", "success")
                }

                is UIState.ERROR -> {
                    Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", "error")
                }
            }

        }

       schoolsViewModel.getSchoolsList()

        return binding.root
    }

    private fun showDetails(dbn: String){
        Log.d("CLASS::${javaClass.simpleName} MESSAGE ->", dbn)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,DetailsFragment.newInstance(dbn))
            .addToBackStack(null)
            .commit()
    }

}