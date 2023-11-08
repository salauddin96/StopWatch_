package org.techtales.mystopwatchapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import org.techtales.mystopwatchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog)
        var numberPicker = dialog.findViewById<NumberPicker>(R.id.numberPicker)
        numberPicker.minValue = 0
        numberPicker.maxValue = 5
        dialog.findViewById<Button>(R.id.setTime).setOnClickListener {
            binding.clockTime.text =  dialog.findViewById<NumberPicker>(R.id.numberPicker).value.toString()+ " mins"
            dialog.dismiss()
        }
        dialog.show()
    }
}