package com.imecatro.nyc_schools.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.imecatro.nyc_schools.R
import com.imecatro.nyc_schools.databinding.FragmentDetailsBinding
import com.imecatro.nyc_schools.viewmodel.SchoolViewModel


class DetailsFragment : Fragment() {

    companion object {
        const val SCORE_KEY = "SCORE_KEY"

        fun newInstance(dbn: String): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(SCORE_KEY, dbn)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding : FragmentDetailsBinding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        viewModel.getThisScore(arguments?.getString(SCORE_KEY)!!)

        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        viewModel.scoreData.observe(viewLifecycleOwner) { score ->
            if (!score.isNullOrEmpty()) {
                binding.apply {
                    scoreSchoolName.text = score[0].schoolName

                    scoreMath.text = "Math: ${score[0].satMathAvgScore}"
                    scoreReading.text ="Reading: ${ score[0].satCriticalReadingAvgScore}"
                    scoreWriting.text = "Writing: ${score[0].satWritingAvgScore}"

                    scoreMath.visibility = View.VISIBLE
                    scoreReading.visibility = View.VISIBLE
                    scoreWriting.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    scoreSchoolName.text = resources.getString(R.string.score_error)
                    scoreMath.visibility = View.INVISIBLE
                    scoreReading.visibility = View.INVISIBLE
                    scoreWriting.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.apply {
            scoreMath.visibility = View.INVISIBLE
            scoreReading.visibility = View.INVISIBLE
            scoreWriting.visibility = View.INVISIBLE
        }
        _binding = null
    }
}