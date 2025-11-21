package com.example.testfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.testfragments.databinding.Fragment1Binding

class BlankFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    lateinit var b: Fragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = Fragment1Binding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.messageForFrag1.observe(activity as LifecycleOwner,{
            b.tvMessage.text = it
        })

        b.bSendToFrag2.setOnClickListener {
            dataModel.messageForFrag2.value = "Hello fragment2 from fragment1"
        }
        b.bSendToActivity.setOnClickListener {
            dataModel.messageForActivity.value = "Hello activity from fragment1"
        }
        b.removeFrag1.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }
}