package com.ut.contact.common.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ut.contact.utils.ProgressDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

abstract class BaseFragment<VM : ViewModelType> : Fragment(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModel: VM
    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    private var loadingDialog: ProgressDialog? = null

    protected val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let { loadingDialog = ProgressDialog.buildProgressDialog(it) }
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = childFragmentInjector

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun switchProgressDialogState(isShowing: Boolean) = loadingDialog?.apply { if (isShowing) show() else dismiss() }
}