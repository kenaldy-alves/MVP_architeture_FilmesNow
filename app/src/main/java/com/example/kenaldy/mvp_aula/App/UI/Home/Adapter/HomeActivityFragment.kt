package com.example.kenaldy.mvp_aula.App.UI.Home.Adapter

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kenaldy.mvp_aula.R

/**
 * A placeholder fragment containing a simple view.
 */
class HomeActivityFragment : Fragment() {

    companion object {
        val NUM_PAGES = "NUM_PAGES"

        fun newInstance(page: Int): HomeActivityFragment {
            val fragment = HomeActivityFragment()
            val args = Bundle()
            args.putInt(NUM_PAGES, page)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filmes_home, container, false)
    }
}
