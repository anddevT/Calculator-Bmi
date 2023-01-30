package com.anddev404.calculatorbmi.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anddev404.calculatorbmi.R
import com.anddev404.calculatorbmi.SharedViewModel
import com.anddev404.calculatorbmi.tools.UnitConverter
import java.lang.NumberFormatException

class FtInFragment : Fragment() {

    lateinit var viewmodel: SharedViewModel
    lateinit var textFoot: EditText
    lateinit var textIn: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ft_in, container, false)
        viewmodel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]



        textFoot = view.findViewById(R.id.foot_edit_text)
        textIn = view.findViewById(R.id.in_edit_text)

        viewmodel.height.observe(viewLifecycleOwner) {
            textFoot.setText(UnitConverter.cmToFeet(it).toString())
            textIn.setText(
                UnitConverter.cmToInches(UnitConverter.restFromCmToFeet(it)).toInt().toString()
            )
        }
        viewmodel.height.observe(viewLifecycleOwner) {
            try {
                val textFloat = textFoot.text.toString().toFloat()
                if (textFloat != it) textFoot.setText((UnitConverter.cmToFeet(it).toString()))

            } catch (nfe: NumberFormatException) {
                textFoot.setText((UnitConverter.cmToFeet(it).toString()))
            }

            try {
                val textFloat = textIn.text.toString().toFloat()
                if (textFloat != it) textIn.setText(
                    UnitConverter.cmToInches(
                        UnitConverter.restFromCmToFeet(
                            it
                        )
                    ).toInt().toString()
                )

            } catch (nfe: NumberFormatException) {
                textIn.setText(
                    UnitConverter.cmToInches(UnitConverter.restFromCmToFeet(it)).toInt().toString()
                )
            }

        }

        textFoot.addTextChangedListener(object : TextWatcher {

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
        textIn.addTextChangedListener(object : TextWatcher {

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