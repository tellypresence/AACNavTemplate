package com.example.navtemplate.fragment.dummy

import androidx.appcompat.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.navtemplate.R
import com.example.navtemplate.fragment.dummy.DummyItemDetailFrag.Companion.ARG_KEY_CONTENT
import com.example.navtemplate.fragment.dummy.DummyItemDetailFrag.Companion.ARG_KEY_DUMMY_ID


import com.example.navtemplate.fragment.dummy.DummyListFrag.OnDummyListFragInteractionListener
import com.example.navtemplate.fragment.dummy.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.frag_dummyitem.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnDummyListFragInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class DummyItemAdapter(
        private val mValues: List<DummyItem>,
        private val mListener: OnDummyListFragInteractionListener?)
    : RecyclerView.Adapter<DummyItemAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
//            mListener?.onDummyListFragInteraction(item)
            val bundle = bundleOf(ARG_KEY_DUMMY_ID to item.id,
                    ARG_KEY_CONTENT to item.content)

            Navigation.findNavController(v).navigate(R.id.action_dummyListFrag_to_dummyItemDetailFrag, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.frag_dummyitem, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mIdView.text = item.id
        holder.mContentView.text = item.content

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }
}
