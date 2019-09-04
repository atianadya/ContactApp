package com.ut.contact.main.front.listitem

import android.view.View
import com.ut.contact.R
import com.ut.contact.common.base.BindableListItemViewHolder
import com.ut.contact.databinding.ListItemContactCardBinding
import com.ut.contact.main.front.ContactCardItemViewModel
import com.mikepenz.fastadapter.items.AbstractItem

/**
 * Created by Atia on 2019-09-03
 */

class ContactListItem(val viewModel: ContactCardItemViewModel,
                      private val listener: EventListener?) : AbstractItem<ContactListItem, ContactListItem.ViewHolder>() {

    override fun getType(): Int = hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    override fun getLayoutRes(): Int = R.layout.list_item_contact_card

    override fun bindView(holder: ViewHolder, payloads: MutableList<Any>) {
        super.bindView(holder, payloads)
        holder.binding.viewmodel = viewModel
        holder.binding.executePendingBindings()
        holder.binding.cardView.setOnClickListener {
            listener?.onClicked(viewModel)
        }
    }

    class ViewHolder(itemView: View): BindableListItemViewHolder<ListItemContactCardBinding>(itemView)

    interface EventListener {
        fun onClicked(viewModel: ContactCardItemViewModel)
    }
}