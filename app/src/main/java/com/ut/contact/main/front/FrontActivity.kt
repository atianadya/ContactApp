package com.ut.contact.main.front

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import com.ut.contact.R
import com.ut.contact.common.base.BaseActivity
import com.ut.contact.extension.disposedBy
import com.ut.contact.main.front.listitem.ContactListItem
import kotlinx.android.synthetic.main.activity_front.*
import kotlinx.android.synthetic.main.toolbar_action_detail.*
import java.util.*
import javax.inject.Inject

/**
 * Created by Atia on 2019-09-03
 */

class FrontActivity : BaseActivity<FrontViewModelType>() {

    @Inject
    lateinit var wireframe: FrontWireframe

    @Inject
    lateinit var fastItemAdapter: FastItemAdapter<ContactListItem>

    //    private lateinit var itemAdapter: ItemAdapter<ContactListItem>
    private val comparator = AlphabetComparatorAsc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_front)
        initView()
        bindViewEvent()
        bindViewModel()

        fastItemAdapter.saveInstanceState(savedInstanceState)

        viewModel.inputs.onViewLoaded()
    }

    private fun initView() {
        iconEndFirst.visibility = View.INVISIBLE
        iconEndSecond.visibility = View.INVISIBLE
        toolbarLeft.visibility = View.INVISIBLE
        toolbarTitle.gravity = Gravity.CENTER

        contactList.let {
            it.adapter = fastItemAdapter
            it.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun bindViewEvent() {
        addContact.setOnClickListener {
            wireframe.openAddContact(this)
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.inputs.onRefresh()
        }
    }

    private fun bindViewModel() {
        viewModel.outputs.shouldShowItems
            .subscribe {
                populateItems(it)
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldShowLoading
            .subscribe {
                switchProgressDialogState(it)
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldShowOutput
            .subscribe {
                Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
            }.disposedBy(compositeDisposable)

        viewModel.outputs.shouldShowRefresh
            .subscribe {
                swipeRefresh.isRefreshing = it
            }.disposedBy(compositeDisposable)
    }

    private fun populateItems(items: List<ContactCardItemViewModel>) {
        val newItems: List<ContactListItem> = items.map {
            ContactListItem(it, object : ContactListItem.EventListener {
                override fun onClicked(viewModel: ContactCardItemViewModel) {
                    wireframe.openDetail(this@FrontActivity, viewModel)
                }
            })
        }

        fastItemAdapter.set(sortItems(newItems, comparator))
    }

    private fun sortItems(list: List<ContactListItem>, comparator: Comparator<ContactListItem>)
            : List<ContactListItem> {
        Collections.sort(list, comparator)
        return list
    }

    private inner class AlphabetComparatorAsc : Comparator<ContactListItem> {
        override fun compare(p0: ContactListItem?, p1: ContactListItem?): Int {
            return p0?.viewModel?.firstName.orEmpty().compareTo(p1?.viewModel?.firstName.orEmpty())
        }
    }
}