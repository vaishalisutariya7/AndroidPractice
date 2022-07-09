package au.com.vaishali.practice.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import au.com.vaishali.practice.application.base.BaseViewModelBackedFragment
import au.com.vaishali.practice.databinding.HomeFragmentBinding
import au.com.vaishali.practice.presentation.adapter.FlickerPhotoAdapter
import au.com.vaishali.practice.presentation.model.FlickerPhoto
import au.com.vaishali.practice.presentation.viewmodel.HomeViewModel
import au.com.vaishali.practice.presentation.viewmodel.HomeViewModel.DisplayEvent.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseViewModelBackedFragment<HomeViewModel>(),
    FlickerPhotoAdapter.OnFlickerPhotoClickListener {

    override val viewModel: HomeViewModel by viewModels()

    private lateinit var binding: HomeFragmentBinding

    private lateinit var flickerPhotoAdapter: FlickerPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(binding) {
            flickerPhotoAdapter = FlickerPhotoAdapter(requireActivity())
            flickerPhotoAdapter.setFlickerPhotoClickListener(this@HomeFragment)

            flickerPhotoRecyclerview.apply {
                layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
                adapter = flickerPhotoAdapter
            }

            searchPhotoEdittext.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) =
                    Unit

                override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) =
                    viewModel.textGetsChanged()

                override fun afterTextChanged(text: Editable?) = Unit

            })

            searchPhotoEdittext.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    requireActivity().hideKeyboard(binding.root)
                    viewModel.searchPhoto(searchPhotoEdittext.text.toString())
                    true
                } else {
                    false
                }
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.getListOfFlickerPhoto.observe(viewLifecycleOwner) {
            when (it) {
                is DisplayLoading -> showProgressbar()
                is FinishLoading -> dismissProgressbar()
                is DisplayEmptyList -> displayEmptyList()
                is DisplayPhotos -> displayPhotoList(it.data)
                is DisplayError -> displayError()
                else -> Unit
            }
        }
    }

    private fun showProgressbar() = with(binding) {
        circularProgress.visibility = VISIBLE
        flickerPhotoRecyclerview.visibility = GONE
    }

    private fun displayError() = with(binding) {
        circularProgress.visibility = GONE
        flickerPhotoRecyclerview.visibility = GONE
    }

    private fun dismissProgressbar() = with(binding) {
        circularProgress.visibility = GONE
    }

    private fun displayPhotoList(data: List<FlickerPhoto>) =
        with(binding) {
            flickerPhotoRecyclerview.visibility = VISIBLE
            if (!flickerPhotoRecyclerview.isComputingLayout) {
                flickerPhotoAdapter.updateFlickerList(data)
            }
        }

    private fun displayEmptyList() = with(binding) {
        flickerPhotoRecyclerview.visibility = GONE
    }

    override fun onPhotoClickListener(photo: FlickerPhoto) {

    }
}