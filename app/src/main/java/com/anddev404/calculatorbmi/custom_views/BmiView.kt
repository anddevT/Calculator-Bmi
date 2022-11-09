package com.anddev404.calculatorbmi.custom_views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.anddev404.calculatorbmi.R

class BmiView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private lateinit var underWeightView: View
    private lateinit var normalWeightView: View
    private lateinit var overWeightView: View
    private lateinit var obesity1View: View
    private lateinit var obesity2View: View


    init {
        inflate(context, R.layout.custom_view_bmi_table_view, this)
        initializeViews()

        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.BmiView)

        typedArray?.let {
            try {

                val highlightedView = typedArray.getInt(R.styleable.BmiView_highlight, -1)

                highlightView(highlightedView)

            } finally {
                typedArray.recycle()
            }
        }
    }

    fun highlightView(highlightedView: Int) {
        clearAllBackgroundColors()

        when (highlightedView) {
            HIGHLIGHT_UNDER_WEIGHT -> underWeightView.setBackgroundColor(resources.getColor(R.color.underweight_bright))
            HIGHLIGHT_NORMAL_WEIGHT -> normalWeightView.setBackgroundColor(resources.getColor(R.color.normal_weight_bright))
            HIGHLIGHT_OVER_WEIGHT -> overWeightView.setBackgroundColor(resources.getColor(R.color.overweight_bright))
            HIGHLIGHT_OBESITY_1 -> obesity1View.setBackgroundColor(resources.getColor(R.color.obese1_bright))
            HIGHLIGHT_OBESITY_2 -> obesity2View.setBackgroundColor(resources.getColor(R.color.obese2_bright))
        }
    }

    private fun clearAllBackgroundColors() {
        underWeightView.setBackgroundColor(Color.TRANSPARENT)
        normalWeightView.setBackgroundColor(Color.TRANSPARENT)
        overWeightView.setBackgroundColor(Color.TRANSPARENT)
        obesity1View.setBackgroundColor(Color.TRANSPARENT)
        obesity2View.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun initializeViews() {
        underWeightView = findViewById(R.id.under_weight_view)
        normalWeightView = findViewById(R.id.normal_weight_view)
        overWeightView = findViewById(R.id.over_weight_view)
        obesity1View = findViewById(R.id.obesity1_view)
        obesity2View = findViewById(R.id.obesity2_view)
    }

    companion object {
        const val HIGHLIGHT_UNDER_WEIGHT = 1
        const val HIGHLIGHT_NORMAL_WEIGHT = 2
        const val HIGHLIGHT_OVER_WEIGHT = 3
        const val HIGHLIGHT_OBESITY_1 = 4
        const val HIGHLIGHT_OBESITY_2 = 5
    }

}

