package com.chayan.tippy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
private const val TAG="MainActivity"
private const val INITIAL_TIP_PERCENT = 15
class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount : EditText
    private lateinit var seekBarTip : SeekBar
    private lateinit var tvPercent : TextView
    private lateinit var tvTipAmount : TextView
    private lateinit var tvTotalAmount : TextView
    private lateinit var tvTipDescribetion : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBarTip=findViewById(R.id.seekBarTip)
        tvPercent=findViewById(R.id.tvPercentLevel)
        tvTipAmount=findViewById(R.id.tvTipAmount)
        etBaseAmount=findViewById(R.id.etBaseAmount)
        tvTotalAmount=findViewById(R.id.tvTotalAmount)
        tvTipDescribetion=findViewById(R.id.tvTipDescribetion)

        seekBarTip.progress= INITIAL_TIP_PERCENT //it will show in the seekbar default 15
        tvPercent.text="$INITIAL_TIP_PERCENT%"
        updateTipDescribtion(INITIAL_TIP_PERCENT)
        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG,"onProgressChanged $p1")
                tvPercent.text="$p1%"
                computeTipAndTotal()
                updateTipDescribtion(p1)

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}

        })
        etBaseAmount.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
              Log.i(TAG,"afterTextChanged $p0")
                computeTipAndTotal()
            }

        })
    }

    private fun updateTipDescribtion(p1: Int) {
  val tipDescription = when (p1){
      in 0..9 ->"Poor"
      in 10..14->"Acceptable"
      in 15..19->"Good"
      in 20..24->"Great"
      else  ->"Amazing"
  }

    tvTipDescribetion.text=tipDescription
    }

    private fun computeTipAndTotal() {
        //Three things we are going to do over here.
        if(etBaseAmount.text.isEmpty()){
            tvTipAmount.text=""
            tvTotalAmount.text=""
            return
        }
     val baseAmount =   etBaseAmount.text.toString().toDouble()
       val tipPercent = seekBarTip.progress
        //Get the value from the base and tip percent.
        val tipAmount = baseAmount * tipPercent /100
        //Compute the tip and total
        val totalAmount = baseAmount + tipAmount
        //Update the UI
        tvTipAmount.text= "%.2f".format(tipAmount)
        tvTotalAmount.text= "%.2f".format(totalAmount)
    }
}