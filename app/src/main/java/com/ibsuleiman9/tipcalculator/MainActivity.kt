package com.ibsuleiman9.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.SeekBar
import android.widget.TextClock
import android.widget.Toast
import com.ibsuleiman9.tipcalculator.databinding.ActivityMainBinding
import kotlin.math.cos

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val INITIAL_TIP_PERCENT = 15
    val INITIAL_COST = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etCost.setText(INITIAL_COST.toString())
        binding.seekBarTip.progress = INITIAL_TIP_PERCENT
        binding.seekBarTip.min = 1
        binding.seekBarTip.max = 50
        binding.tvTipPercent.text = "$INITIAL_TIP_PERCENT%"
        calculateTip(INITIAL_COST, INITIAL_TIP_PERCENT)


        binding.marqueText1.isSelected = true

        binding.seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(progress != 0) {
                    binding.tvTipPercent.text = "$progress"
                    var cost = binding.etCost.text.toString().toDouble()
                    calculateTip(cost, progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        binding.etCost.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if(!TextUtils.isEmpty(binding.etCost.text.toString())){
                    var cost = binding.etCost.text.toString().toDouble()
                    var progress = binding.seekBarTip.progress
                    if(cost != 0.0){
                        calculateTip(cost, progress)
                    }else {
                        binding.etCost.setText("1")
                        var cost = binding.etCost.text.toString().toDouble()
                        calculateTip(cost, progress)
                    }
                }
            }
        })


    }

    private fun calculateTip(cost: Double, progress: Int){
        val tipAmount = cost * (progress)/100
        val tipAmount2 = String.format("%.2f", tipAmount).toDouble()
        binding.tvTipAmount.text = tipAmount2.toString()
        binding.tvTotal.text = (cost + tipAmount2).toString()
    }
}