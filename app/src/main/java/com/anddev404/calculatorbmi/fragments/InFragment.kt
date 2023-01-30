package com.anddev404.calculatorbmi.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.anddev404.calculatorbmi.R
import com.anddev404.calculatorbmi.SharedViewModel
import com.anddev404.calculatorbmi.tools.UnitConverter
import java.lang.NumberFormatException
import androidx.fragment.app.Fragment


class InFragment : Fragment() {

    lateinit var viewmodel: SharedViewModel
    lateinit var text: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_in, container, false)
        viewmodel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        text = view.findViewById(R.id.in_edit_text)

        viewmodel.height.observe(viewLifecycleOwner) {
            try {
                val textFloat = text.text.toString().toFloat()
                if (textFloat != it) text.setText(UnitConverter.cmToInches(it).toInt().toString())

            } catch (nfe: NumberFormatException) {
                text.setText(UnitConverter.cmToInches(it).toInt().toString())
            }
        }

        text.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
//                try {
//                    viewmodel.changeHeight(p0.toString().toFloat())
//
//                } catch (T: Throwable) {
//                    viewmodel.changeHeight(0f)
//                }
            }
        })

        return view
    }
}