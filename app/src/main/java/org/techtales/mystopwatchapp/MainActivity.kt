package org.techtales.mystopwatchapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.NumberPicker
import org.techtales.mystopwatchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var isRunning = false
    private var minutes:String? = "00.00.00"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.imageView.setOnClickListener {

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog)
            var numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPicker)
            numberPicker.minValue = 0
            numberPicker.maxValue = 5
            dialog.findViewById<Button>(R.id.setTime).setOnClickListener {
                minutes = numberPicker.value.toString()

                binding.clockTime.text = dialog.findViewById<NumberPicker>(R.id.numberPicker).value.toString() + " mins"
                dialog.dismiss()

            }
            dialog.show()
        }
        binding.run.setOnClickListener {
            if (isRunning) {
                isRunning=false
                if(!minutes.equals("00.00.00")){
                    var totalmin = minutes!!.toInt()*60*1000L
                    var countDown = 1000L
                    binding.chronometer.base= SystemClock.elapsedRealtime()+totalmin
                    binding.chronometer.format= "%$ %$"
                }

            }
            else{
                isRunning=true
            }
        }
    }
}