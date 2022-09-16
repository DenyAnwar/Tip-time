package mythgreendev.com.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mythgreendev.com.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        // convert stringInTextField to text and then
        // convert it to String using toString()
        val stringInTextField = binding.costOfService.text.toString()
        // convert stringInTextField from String to Double using toDouble()
        val cost = stringInTextField.toDoubleOrNull()
        // if the cost is null or 0, then display 0 tip and exit this function early
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return // return means exit the method without executing the rest of the instructions
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        // round up tip
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        // display the formatted tip value on screen
        displayTip(tip)
    }

    private fun displayTip(tip: Double) {
        // change number format tip to currency format
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        // add format tip to tip_amount in string.xml
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}