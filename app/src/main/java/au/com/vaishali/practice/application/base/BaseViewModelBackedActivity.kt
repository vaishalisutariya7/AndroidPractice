package au.com.vaishali.practice.application.base

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseViewModelBackedActivity<T> : AppCompatActivity() where T : BaseViewModel {

    protected abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        //All root views do not allow touches to be obscured
        window.decorView.rootView.filterTouchesWhenObscured = true
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let { super.attachBaseContext(newBase) }

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }
}