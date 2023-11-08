package org.techtales.mystopwatchapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Chronometer
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

        var lapsList = ArrayList<String>()
        var arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lapsList)
        binding.listView.adapter= arrayAdapter
        binding.lap.setOnClickListener {
            if (isRunning) {
                lapsList.add(binding.chronometer.text.toString())
                arrayAdapter.notifyDataSetChanged()
            }
        }

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
                isRunning = false
                if (!minutes.equals("00.00.00")) {
                    var totalmin = minutes!!.toInt() * 60 * 1000L
                    var countDown = 1000L
                    binding.chronometer.base = SystemClock.elapsedRealtime() + totalmin
                    binding.chronometer.format = "%S %S"
                    binding.chronometer.onChronometerTickListener =
                        Chronometer.OnChronometerTickListener {
                            var elapsedtime =
                                SystemClock.elapsedRealtime() - binding.chronometer.base
                            if (elapsedtime >= totalmin) {
                                binding.chronometer.stop()
                                isRunning = false
                                binding.run.text = "Run"
                            }
                        }
                }
             else{
                    binding.chronometer.stop()
                    isRunning= false
                    binding.run.text= "Run"

            }}
            else {
                isRunning=true
                binding.chronometer.base = SystemClock.elapsedRealtime()
                binding.run.text = "Stop"
                binding.chronometer.start()
            }

        }
    }
}