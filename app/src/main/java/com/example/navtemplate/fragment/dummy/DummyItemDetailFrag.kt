package com.example.navtemplate.fragment.dummy

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.core.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.navtemplate.R
import kotlinx.android.synthetic.main.frag_dummy_item_detail.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DummyItemDetailFrag.OnDummyDetailFragInteractionListener] interface
 * to handle interaction events.
 * Use the [DummyItemDetailFrag.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DummyItemDetailFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var itemId: String? = null
    private var itemContent: String? = null
    private var listener: OnDummyDetailFragInteractionListener? = null

    val textViewID by lazy { textView_id }
    val textViewContent by lazy { textView_content }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getString(ARG_KEY_DUMMY_ID)
            itemContent = it.getString(ARG_KEY_CONTENT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_dummy_item_detail, container, false)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onDummyDetailFragInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDummyDetailFragInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnDummyDetailFragInteractionListener")
        }
    }

    override fun onResume() {
        super.onResume()
        textViewID.text = itemId?: "ID NOT SET!"
        textViewContent.text = itemContent?: "CONTENT NOT SET!"
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnDummyDetailFragInteractionListener {
        // TODO: Update argument type and name
        fun onDummyDetailFragInteraction(uri: Uri)
    }

    companion object {
        const val ARG_KEY_DUMMY_ID = "itemId"
        const val ARG_KEY_CONTENT = "itemContent"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DummyItemDetailFrag.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                DummyItemDetailFrag().apply {
                    arguments = Bundle().apply {
                        putString(ARG_KEY_DUMMY_ID, param1)
                        putString(ARG_KEY_CONTENT, param2)
                    }
                }
    }
}
