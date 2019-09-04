package com.ut.contact.main.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import com.ut.contact.R
import com.ut.contact.common.base.BaseActivity
import com.ut.contact.extension.disposedBy
import com.ut.contact.main.detail.detailedit.DetailEditFragment
import com.ut.contact.main.detail.detailview.DetailViewFragment
import com.ut.contact.main.front.ContactCardItemViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_action_detail.*
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-04
 */

class DetailActivity : BaseActivity<DetailViewModelType>() {

    @Inject
    lateinit var wireframe: DetailWireframe

    var data: ContactCardItemViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = ContactCardItemViewModel(
            intent.getStringExtra(DetailWireframe.INTENT_ID).orEmpty(),
            intent.getStringExtra(DetailWireframe.INTENT_FIRST_NAME).orEmpty(),
            intent.getStringExtra(DetailWireframe.INTENT_LAST_NAME).orEmpty(),
            intent.getIntExtra(DetailWireframe.INTENT_AGE, -1),
            intent.getStringExtra(DetailWireframe.INTENT_PHOTO_URL).orEmpty()
        )

        initView()
        bindViewEvents()
        bindViewModel()

        viewModel.inputs.onViewLoaded(ContactCardItemViewModel(
            intent.getStringExtra(DetailWireframe.INTENT_ID).orEmpty(),
            intent.getStringExtra(DetailWireframe.INTENT_FIRST_NAME).orEmpty(),
            intent.getStringExtra(DetailWireframe.INTENT_LAST_NAME).orEmpty(),
            intent.getIntExtra(DetailWireframe.INTENT_AGE, -1),
            intent.getStringExtra(DetailWireframe.INTENT_PHOTO_URL).orEmpty()))
    }

    private fun initView() {
        val frag = DetailViewFragment.newFragment(data)
        supportFragmentManager.beginTransaction().replace(R.id.detailContainer, frag).commit()

        Glide.with(this)
            .load(intent.getStringExtra(DetailWireframe.INTENT_PHOTO_URL).orEmpty())
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.default_person)
                    .centerCrop()
            )
            .into(photo)
    }

    private fun bindViewEvents() {
        toolbarLeft.setOnClickListener { onBackPressed() }
        iconEndSecond.setOnClickListener { showEditFragment() }
        iconEndFirst.setOnClickListener { viewModel.inputs.onClickDelete() }
    }

    private fun bindViewModel() {
        viewModel.outputs.shouldShowOutput
            .subscribe {
                Toast.makeText(
                    this,
                    if (it is String) it else getString(it as Int),
                    Toast.LENGTH_SHORT
                ).show()
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldBackToHome
            .subscribe {
                wireframe.backToHome(this)
            }.disposedBy(compositeDisposable)
    }

    private fun showEditFragment() {
        val frag = DetailEditFragment.newFragment(data)
        supportFragmentManager.beginTransaction().replace(R.id.detailContainer, frag).commit()

        iconEndSecond.visibility = View.INVISIBLE
        iconEndFirst.setImageResource(R.drawable.ic_save)
        iconEndFirst.setOnClickListener {
            frag.save()
        }
    }

    private fun showViewFragment() {

    }
}