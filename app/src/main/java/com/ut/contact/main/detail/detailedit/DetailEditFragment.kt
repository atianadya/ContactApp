package com.ut.contact.main.detail.detailedit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ut.contact.R
import com.ut.contact.common.base.BaseFragment
import com.ut.contact.extension.disposedBy
import com.ut.contact.main.front.ContactCardItemViewModel
import kotlinx.android.synthetic.main.content_detail_edit.*
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

class DetailEditFragment : BaseFragment<DetailEditViewModelType>() {

    @Inject
    lateinit var wireframe: DetailEditWireframe

    interface Navigate {
        fun changeFragment(data: ContactCardItemViewModel)
    }

    private var data: ContactCardItemViewModel? = null
    private var listener: Navigate? = null

    companion object {
        fun newFragment(data: ContactCardItemViewModel?, listener: Navigate) : DetailEditFragment {
            val fragment = DetailEditFragment()
            data?.let{fragment.data = data}
            fragment.listener = listener

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.content_detail_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindViewEvents()
        bindViewModel()

        data?.let { viewModel.inputs.onViewLoaded(it) }
    }

    fun save() {
        viewModel.inputs.onClickSave()
    }

    private fun initView() {
        firstName.setText(data?.firstName)
        lastName.setText(data?.lastName)
        age.setText(data?.age.toString())
        photoUrl.setText(data?.photo)
    }

    private fun bindViewEvents() {
        firstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.inputs.onChangedFirstName(p0.toString())
            }
        })

        lastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.inputs.onChangedLastName(p0.toString())
            }
        })

        age.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.inputs.onChangedAge(p0.toString())
            }
        })

        photoUrl.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.inputs.onChangedPhotoUrl(p0.toString())
            }
        })
    }

    private fun bindViewModel() {
        viewModel.outputs.shouldShowOutput
            .subscribe {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldBackToMain
            .subscribe {
                activity?.let {
                    wireframe.startMain(it)
                }
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldShowLoading
            .subscribe {
                switchProgressDialogState(it)
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldNavigate
            .subscribe {
                listener?.changeFragment(it)
            }.disposedBy(compositeDisposable)
    }
}