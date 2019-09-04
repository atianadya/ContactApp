package com.ut.contact.main.addcontact

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.ut.contact.R
import com.ut.contact.common.base.BaseActivity
import com.ut.contact.extension.disposedBy
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.toolbar_action_detail.*
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

class AddContactActivity : BaseActivity<AddContactViewModelType>() {

    @Inject
    lateinit var wireframe: AddContactWireframe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        initView()
        bindViewEvents()
        bindViewModel()
    }

    private fun initView() {
        iconEndFirst.visibility = View.GONE
        iconEndSecond.visibility = View.GONE
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
                viewModel.inputs.onChangedPhoto(p0.toString())
            }
        })

        addContact.setOnClickListener { viewModel.inputs.onClickAdd() }
    }

    private fun bindViewModel() {
        viewModel.outputs.shouldShowOutput
            .subscribe {
                Toast.makeText(
                    this,
                    if (it is String) it else getString(it as Int),
                    Toast.LENGTH_SHORT
                )
                    .show()
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldBackToHome
            .subscribe {
                wireframe.backToHome(this)
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldShowLoading
            .subscribe {
                switchProgressDialogState(it)
            }.disposedBy(compositeDisposable)
    }
}