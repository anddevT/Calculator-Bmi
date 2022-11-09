package com.anddev404.calculatorbmi.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnTouchListener
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.anddev404.calculatorbmi.R

class BmiResultView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var seekBar: SeekBar
    private var text: TextView

    init {
        inflate(context, R.layout.custom_view_bmi_bar_view, this)

        seekBar = findViewById(R.id.bmi_seek_bar)
        text = findViewById(R.id.bmi_text)

        val typedArray = context?.obtainStyledAttributes(attrs, R.styleable.BmiResultView)

        typedArray?.let {
            try {

                val bmi = typedArray.getFloat(R.styleable.BmiResultView_bmi, 0f)
                setBmi(bmi)

            } finally {
                typedArray.recycle()
            }
        }

        seekBar.setOnTouchListener(OnTouchListener { view, motionEvent -> true })

    }

    fun setBmi(bmi: Float) {

        if (bmi <= 0) {
            seekBar.progress = 0
            text.setTextColor(resources.getColor(R.color.text_color))

        } else if (bmi < 18.5) {
            val factor = 20 / 18.5f
            val progress = bmi * factor
            seekBar.progress = progress.toInt()
            text.setTextColor(resources.getColor(R.color.underweight))

        } else if (bmi >= 18.5 && bmi < 25) {
            val factor = 20 / 6.5f
            val progress = (bmi - 18.5f) * factor
            seekBar.progress = progress.toInt() + 20
            text.setTextColor(resources.getColor(R.color.normal_weight))

        } else if (bmi >= 25 && bmi < 30) {
            val factor = 20 / 5f
            val progress = (bmi - 25f) * factor
            seekBar.progress = progress.toInt() + 40
            text.setTextColor(resources.getColor(R.color.overweight))

        } else if (bmi >= 30 && bmi < 35) {
            val factor = 20 / 5f
            val progress = (bmi - 30f) * factor
            seekBar.progress = progress.toInt() + 60
            text.setTextColor(resources.getColor(R.color.obese1))

        } else {
            val factor = (20 / 20).toFloat()
            val progress = (bmi - 35f) * factor
            seekBar.progress = progress.toInt() + 80
            text.setTextColor(resources.getColor(R.color.obese2))

        }

        setText(bmi)
    }

    private fun setText(bmi: Float) {
        if (bmi <= 0) {
            text.text = "BMI ..."

        } else {
            text.text = "BMI $bmi"
        }
    }
}