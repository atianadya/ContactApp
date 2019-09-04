package com.ut.contact.common.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ut.contact.utils.ProgressDialog
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-03
 */

interface ViewModelType

abstract class BaseActivity<VM : ViewModelType> : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var viewModel: VM
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private var loadingDialog: ProgressDialog? = null

    protected val compositeDisposable = CompositeDisposable()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        loadingDialog = ProgressDialog.buildProgressDialog(this)
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun supportFragmentInjector() : AndroidInjector<Fragment> = fragmentInjector

    fun switchProgressDialogState(isShowing: Boolean) = loadingDialog?.apply { if (isShowing) show() else dismiss() }
}