package com.example.navtemplate.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navtemplate.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {

    lateinit var rootView: View
    val buttonDummy by lazy { button_dummy_nav }
    val buttonDummyList by lazy { button_dummy_list_nav }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
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
