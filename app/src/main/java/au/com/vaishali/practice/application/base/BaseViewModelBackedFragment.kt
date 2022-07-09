package au.com.vaishali.practice.application.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment

abstract class BaseViewModelBackedFragment<T> : Fragment() where T : BaseViewModel {

    protected abstract val viewModel: T

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onStart()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}