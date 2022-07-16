package com.imecatro.nyc_schools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.imecatro.nyc_schools.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 *
 * @author Abdiel Carreño
 * @date 7/15/2022
 *
 */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}