package au.com.vaishali.practice.application.base

import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    open fun onStart() = Unit

    open fun onStop() = Unit

    open fun onPause() = Unit

    open fun onResume() = Unit

    open fun onDestroy() = Unit
}