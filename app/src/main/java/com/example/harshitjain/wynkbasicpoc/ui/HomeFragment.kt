package com.example.harshitjain.wynkbasicpoc.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.harshitjain.wynkbasicpoc.R
import com.example.harshitjain.wynkbasicpoc.WynkApp
import com.example.harshitjain.wynkbasicpoc.repo.Status
import com.example.harshitjain.wynkbasicpoc.viewModel.HomeViewModel
import com.example.harshitjain.wynkbasicpoc.viewModel.HomeViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [HomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var itemId: String? = null
    private var itemType: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var adapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemId = it.getString(ARG_PARAM1)
            itemType = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = HomeAdapter(context)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(SpacingDecoration())

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val viewModel = ViewModelProviders.of(this, HomeViewModelFactory(WynkApp.instance.itemRepository)).get(HomeViewModel::class.java)

        viewModel.getItem(itemId, itemType, "SONG")?.observe(this, Observer {
            //            var items = mutableListOf<Item>()

//            it?.data?.let { items.add(it) }
            if (it?.status==Status.LOADING){
                progress_bar.visibility=View.VISIBLE
                recyclerView.visibility=View.GONE
            }else{
                progress_bar.visibility=View.GONE
                recyclerView.visibility=View.VISIBLE
            }

            it?.data?.run {
                Log.v("hjhj", "data set() --> ${this.size}")
                adapter.setData(this)
            }
        })
    }


    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private class SpacingDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
//            super.getItemOffsets(outRect, view, parent, state)

            outRect?.bottom = 20
        }
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
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param itemId Parameter 1.
         * @param type Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(itemId: String? = null, type: String? = null) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, itemId)
                        putString(ARG_PARAM2, type)
                    }
                }
    }
}
