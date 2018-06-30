package com.example.navtemplate.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.navtemplate.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    lateinit var rootView: View
    // "by lazy" is EVIL:
    //      https://issuetracker.google.com/issues/110950258
    //      https://www.bignerdranch.com/blog/kotlin-when-to-use-lazy-or-lateinit/
    lateinit var buttonDummy : Button
    lateinit var buttonDummyList : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        buttonDummy = rootView.findViewById(R.id.button_dummy_nav)
        buttonDummyList = rootView.findViewById(R.id.button_dummy_list_nav)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        buttonDummy.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_mainActivityFragment_to_gameOver)
        }
        buttonDummyList.setOnClickListener {
            Navigation.findNavController(rootView).navigate(R.id.action_mainActivityFragment_to_dummyListFrag)
        }
    }
}
