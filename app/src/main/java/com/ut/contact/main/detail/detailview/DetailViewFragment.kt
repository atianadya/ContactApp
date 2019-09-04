package com.ut.contact.main.detail.detailview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ut.contact.R
import com.ut.contact.common.base.BaseFragment
import com.ut.contact.main.detail.DetailWireframe
import com.ut.contact.main.front.ContactCardItemViewModel
import kotlinx.android.synthetic.main.content_detail_view.*

/**
 * Created by Atia on 2019-09-04
 */

class DetailViewFragment : BaseFragment<DetailViewViewModelType>() {

    var id: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: Int? = -1
    var photo: String? = null
    var data: ContactCardItemViewModel? = null

    companion object {
        fun newFragment(data: ContactCardItemViewModel?) : DetailViewFragment {
            val fragment = DetailViewFragment()
            data?.let{fragment.data = data}

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.content_detail_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        name.text = getString(R.string.full_name, data?.firstName, data?.lastName)
        ageYearsOld.text = getString(R.string.age_years_old, data?.age)
    }
}