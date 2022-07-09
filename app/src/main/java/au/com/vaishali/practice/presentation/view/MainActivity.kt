package au.com.vaishali.practice.presentation.view

import android.os.Bundle
import androidx.activity.viewModels
import au.com.vaishali.practice.application.base.BaseViewModelBackedActivity
import au.com.vaishali.practice.databinding.ActivityMainBinding
import au.com.vaishali.practice.presentation.viewmodel.FlickerSharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseViewModelBackedActivity<FlickerSharedViewModel>() {

    override val viewModel: FlickerSharedViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}