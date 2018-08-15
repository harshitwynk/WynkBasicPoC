package com.example.harshitjain.wynkbasicpoc.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.harshitjain.wynkbasicpoc.R
import com.example.harshitjain.wynkbasicpoc.db.Item
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GridFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar(toolbar)
        launchContentListFragment()
    }

    private fun launchContentListFragment() {
        supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, GridFragment.newInstance())?.commit()
    }

    private fun initToolbar(toolbar: Toolbar?) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        toolbar?.title = "Wynk Music"
        supportActionBar?.title = "Wynk Music"
    }

    override fun onListFragmentInteraction(item: Item?) {
        toolbar?.title = item?.title
        supportFragmentManager?.beginTransaction()?.addToBackStack(null)?.add(R.id.fragment_container, HomeFragment.newInstance(item?.id, item?.type))?.commit()
    }
}
