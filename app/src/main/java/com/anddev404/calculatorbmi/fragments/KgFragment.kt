package com.anddev404.calculatorbmi.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anddev404.calculatorbmi.SharedViewModel
import com.anddev404.calculatorbmi.R

class KgFragment : Fragment() {

    lateinit var viewmodel: SharedViewModel
    lateinit var text: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kg, container, false)

        text = view.findViewById(R.id.kg_edit_text)

        text.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("MARCIN", "beforeTextChanged $p0");

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d("MARCIN", "onTextChanged $p0");

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d("MARCIN", "afterTextChanged $p0")



                try {
                    viewmodel.changeWeight(p0.toString().toFloat())

                } catch (T: Throwable) {
                    viewmodel.changeWeight(0f)
                }

            }

        })

        viewmodel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        return view
    }
}