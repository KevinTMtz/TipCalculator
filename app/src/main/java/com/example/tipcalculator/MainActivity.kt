package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        binding.button15.setOnClickListener {
            interfaceCalculateTip(0.15)
        }

        binding.button20.setOnClickListener {
            interfaceCalculateTip(0.20)
        }

        binding.button35.setOnClickListener {
            interfaceCalculateTip(0.35)
        }

        binding.buttonRound.setOnClickListener {
            roundAmount()
        }
    }

    private fun interfaceCalculateTip(percentage: Double) {
        val amount = binding.amountDecimal.text.toString()

        val amountValue = amount.toDoubleOrNull()

        if (amountValue == null) {
            binding.totalTip.text = "0.0"
            binding.totalAmount.text = "0.0"

            Toast.makeText(applicationContext, "Input amount", Toast.LENGTH_SHORT).show()

            return
        } else if (amountValue <= 0) {
            Toast.makeText(applicationContext, "Amount must be bigger than 0", Toast.LENGTH_SHORT).show()

            return
        } else {
            Toast.makeText(applicationContext, "Percentage selected: ${percentage * 100}%", Toast.LENGTH_SHORT).show()

            binding.totalTip.text = "${amountValue * percentage}"
            binding.totalAmount.text = "${amountValue * (1 + percentage)}"
        }
    }

    private fun roundAmount() {
        if (binding.amountDecimal.text.toString() == "") {
            Toast.makeText(applicationContext, "Input amount", Toast.LENGTH_SHORT).show()

            return
        } else {
            val totalTip = binding.totalTip.text.toString().toDouble()
            val totalAmount = binding.totalAmount.text.toString().toDouble()

            val df = DecimalFormat("#.##")

            binding.totalTip.text = "${df.format(totalTip)}"
            binding.totalAmount.text = "${df.format(totalAmount)}"

            Toast.makeText(applicationContext, "Rounded amount", Toast.LENGTH_SHORT).show()
        }
    }
}