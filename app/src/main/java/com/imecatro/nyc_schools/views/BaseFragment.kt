package com.imecatro.nyc_schools.views

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.imecatro.nyc_schools.viewmodel.SchoolViewModel

open class BaseFragment: Fragment() {

    protected val schoolsViewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }
}