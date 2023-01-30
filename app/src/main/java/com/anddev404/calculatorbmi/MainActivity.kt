package com.anddev404.calculatorbmi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anddev404.calculatorbmi.custom_views.BmiBarView
import com.anddev404.calculatorbmi.custom_views.BmiTableView
import com.anddev404.calculatorbmi.data.model.HeightUnit
import com.anddev404.calculatorbmi.data.model.WeightUnit
import com.anddev404.calculatorbmi.fragments.*
import com.anddev404.calculatorbmi.tools.UnitConverter

class MainActivity : AppCompatActivity() {

    private lateinit var resultBar: BmiBarView
    private lateinit var tableView: BmiTableView

    private lateinit var changeWeightUnitButton: Button
    private lateinit var changeHeightUnitButton: Button

    private lateinit var idealWeight: TextView
    private lateinit var correctWeight: TextView
    private lateinit var underOrOverWeight: TextView
    private lateinit var underOrOverWeightLeft: TextView

    private lateinit var viewmodel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewmodel = ViewModelProvider(this)[SharedViewModel::class.java]

        hideActionBar()
        initializeViews()
        addMenuButtonsClickListener()
        addObservers()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun initializeViews() {
        resultBar = findViewById(R.id.result_bar)
        tableView = findViewById(R.id.result_table)

        changeHeightUnitButton = findViewById(R.id.change_height_unit_button)
        changeWeightUnitButton = findViewById(R.id.change_weight_unit_button)

        idealWeight = findViewById(R.id.ideal_weight_textview)
        correctWeight = findViewById(R.id.correct_weight_textview)
        underOrOverWeight = findViewById(R.id.underweight_overweight_textview)
        underOrOverWeightLeft = findViewById(R.id.underweight_overweight_left_textview)
    }

    private fun addMenuButtonsClickListener() {

        changeHeightUnitButton.setOnClickListener {
            val popup = PopupMenu(this, changeHeightUnitButton)
            popup.menuInflater.inflate(R.menu.height_popup_menu, popup.menu)

            popup.setOnMenuItemClickListener {
                when (it.title) {
                    resources.getString(R.string.cm) ->
                        viewmodel.changeHeightUnit(HeightUnit.CM)

                    resources.getString(R.string.`in`) ->
                        viewmodel.changeHeightUnit(HeightUnit.IN)

                    resources.getString(R.string.ft_in) ->
                        viewmodel.changeHeightUnit(HeightUnit.FT_IN)
                }
                true
            }
            popup.show()
        }

        changeWeightUnitButton.setOnClickListener {
            val popup = PopupMenu(this, changeWeightUnitButton)
            popup.menuInflater.inflate(R.menu.weight_popup_menu, popup.menu)

            popup.setOnMenuItemClickListener {
                when (it.title) {
                    resources.getString(R.string.kg) -> {
                        viewmodel.changeWeightUnit(WeightUnit.KG)
                    }
                    resources.getString(R.string.lb) -> {
                        viewmodel.changeWeightUnit(WeightUnit.LB)
                    }
                    resources.getString(R.string.st_lb) -> {
                        viewmodel.changeWeightUnit(WeightUnit.ST_LB)
                    }
                }
                true
            }
            popup.show()
        }
    }

    private fun addObservers() {

        viewmodel.bmi.observe(this) {
            resultBar.setBmi(it)

            val roundedBmi = String.format("%.0f", it).toFloat()
            tableView.highlightView(roundedBmi)
        }

        viewmodel.heightFragment.observe(this) {

            when (it.first) {//TODO dodac tu initializer w tej funkcji i dane z Pair przekazać ewentualnmie stworzyc jakis Interfejst z metoda wspolna dla wsszysktich fragmentów
                HeightUnit.CM -> changeHeightFragment(it.first)
                HeightUnit.IN -> changeHeightFragment(it.first)
                HeightUnit.FT_IN -> changeHeightFragment(it.first)
            }
        }
        viewmodel.weightFragment.observe(this) {

            when (it.first) {//TODO dodac tu initializer w tej funkcji i dane z Pair przekazać ewentualnmie stworzyc jakis Interfejst z metoda wspolna dla wsszysktich fragmentów
                WeightUnit.KG -> changeWeightFragment(it.first)
                WeightUnit.LB -> changeWeightFragment(it.first)
                WeightUnit.ST_LB -> changeWeightFragment(it.first)
            }
        }

        viewmodel.textOfHeightUnitButton.observe(this) {
            changeTextOfHeightUnitButton(it)
        }
        viewmodel.textOfWeightUnitButton.observe(this) {
            changeTextOfWeightUnitButton(it)
        }

        viewmodel.idealWeight.observe(this) {
            idealWeight.text = it
        }
        viewmodel.correctWeight.observe(this) {
            correctWeight.text = it
        }
        viewmodel.underOverWeight.observe(this) {
//TODO dodać zamienianie jedn ostek bo domyslnie jest w kg, oraz dać tą jednostkę, to pewnie zamienic w vieww modelu

//            if (it > 0) {
//                underOrOverWeightLeft.text = resources.getString(R.string.overweight)
            underOrOverWeight.text = it
//            }
//            if (it < 0) {
//                underOrOverWeightLeft.text = resources.getString(R.string.underweight)
//                underOrOverWeight.text = "${abs(it)}"
//
//            }
//            if (it == 0f) {
//                underOrOverWeightLeft.text = resources.getString(R.string.under_over_weight)
//                underOrOverWeight.text = resources.getString(R.string.three_dots)
//
//            }
        }

    }

    private fun changeHeightFragment(unit: HeightUnit) {
        when (unit) {
            HeightUnit.CM -> supportFragmentManager.beginTransaction()
                .replace(R.id.height_fragment, CmFragment()).commit()

            HeightUnit.IN -> supportFragmentManager.beginTransaction()
                .replace(R.id.height_fragment, InFragment()).commit()

            HeightUnit.FT_IN -> supportFragmentManager.beginTransaction()
                .replace(R.id.height_fragment, FtInFragment()).commit()
        }
    }

    private fun changeWeightFragment(unit: WeightUnit) {
        when (unit) {
            WeightUnit.KG -> supportFragmentManager.beginTransaction()
                .replace(R.id.weight_fragment, KgFragment()).commit()

            WeightUnit.LB -> supportFragmentManager.beginTransaction()
                .replace(R.id.weight_fragment, LbFragment()).commit()

            WeightUnit.ST_LB -> supportFragmentManager.beginTransaction()
                .replace(R.id.weight_fragment, StLbFragment()).commit()
        }
    }

    private fun changeTextOfHeightUnitButton(unit: HeightUnit) {
        when (unit) {
            HeightUnit.CM -> changeHeightUnitButton.text = resources.getString(R.string.cm)
            HeightUnit.IN -> changeHeightUnitButton.text = resources.getString(R.string.`in`)
            HeightUnit.FT_IN -> changeHeightUnitButton.text = resources.getString(R.string.ft_in)
        }
    }

    private fun changeTextOfWeightUnitButton(unit: WeightUnit) {
        when (unit) {
            WeightUnit.KG -> changeWeightUnitButton.text = resources.getString(R.string.kg)
            WeightUnit.LB -> changeWeightUnitButton.text = resources.getString(R.string.lb)
            WeightUnit.ST_LB -> changeWeightUnitButton.text = resources.getString(R.string.st_lb)
        }
    }
}