package com.example.testfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.testfragments.databinding.Fragment2Binding

class BlankFragment2 : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private lateinit var b: Fragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = Fragment2Binding.inflate(inflater)
        return b.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataModel.messageForFrag2.observe(activity as LifecycleOwner,{
            b.tvMessage.text = it
        })

        b.bSendToFrag1.setOnClickListener {
            dataModel.messageForFrag1.value = "Hello fragment1 from fragment2"
        }
        b.bSendToActivity.setOnClickListener {
            dataModel.messageForActivity.value = "Hello activity from fragment2"
        }
        b.removeFrag2.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment2()
    }
}