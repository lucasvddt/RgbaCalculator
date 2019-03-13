package com.example.rgbacalculator

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import java.util.regex.Pattern

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener, View.OnKeyListener, View.OnClickListener {

    private lateinit var mColorView:View

    private lateinit var mSeekbarAlpha:SeekBar
    private lateinit var mSeekbarRed:SeekBar
    private lateinit var mSeekbarGreen:SeekBar
    private lateinit var mSeekbarBlue:SeekBar

    private lateinit var mEditTextAlpha: EditText
    private lateinit var mEditTextRed: EditText
    private lateinit var mEditTextGreen: EditText
    private lateinit var mEditTextBlue: EditText

    private lateinit var mTextViewValue: TextView

    private lateinit var mRadioButtonARGB: RadioButton
    private lateinit var mRadioButtonHex: RadioButton

    private var valueAlpha: String = "0"
    private var valueRed: String = "0"
    private var valueGreen: String = "0"
    private var valueBlue: String = "0"

    private var isDecimal: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mColorView = findViewById(R.id.main_color_view)

        mSeekbarAlpha = findViewById(R.id.main_seekbar_alpha)
        mSeekbarRed = findViewById(R.id.main_seekbar_red)
        mSeekbarGreen = findViewById(R.id.main_seekbar_green)
        mSeekbarBlue = findViewById(R.id.main_seekbar_blue)

        mEditTextAlpha = findViewById(R.id.main_edittext_alpha)
        mEditTextRed = findViewById(R.id.main_edittext_red)
        mEditTextGreen = findViewById(R.id.main_edittext_green)
        mEditTextBlue = findViewById(R.id.main_edittext_blue)

        mTextViewValue = findViewById(R.id.textViewValue)

        mRadioButtonARGB = findViewById(R.id.radioButtonARGB)
        mRadioButtonHex = findViewById(R.id.radioButtonHex)

        configureWidgets()

    }

    private fun configureWidgets() {

        mColorView.setBackgroundColor(Color.BLACK)

        mSeekbarAlpha.max = 255
        mSeekbarAlpha.setOnSeekBarChangeListener(this)

        mSeekbarRed.max = 255
        mSeekbarRed.setOnSeekBarChangeListener(this)

        mSeekbarGreen.max = 255
        mSeekbarGreen.setOnSeekBarChangeListener(this)

        mSeekbarBlue.max = 255
        mSeekbarBlue.setOnSeekBarChangeListener(this)

        mEditTextAlpha.setText("0")
        mEditTextAlpha.setOnKeyListener(this)

        mEditTextRed.setText("0")
        mEditTextRed.setOnKeyListener(this)

        mEditTextGreen.setText("0")
        mEditTextGreen.setOnKeyListener(this)

        mEditTextBlue.setText("0")
        mEditTextBlue.setOnKeyListener(this)

        mTextViewValue.text = "0, 0, 0, 0"

        mRadioButtonARGB.isChecked = true
        mRadioButtonARGB.setOnClickListener(this)

        mRadioButtonHex.isChecked = false
        mRadioButtonHex.setOnClickListener(this)

    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

        when(seekBar.id){

            R.id.main_seekbar_alpha -> {

                val alpha = if (isDecimal){
                    seekBar.progress.toString()
                }else{
                    java.lang.Integer.toHexString(seekBar.progress)
                }

                mEditTextAlpha.setText(alpha)
                valueAlpha = seekBar.progress.toString()
            }

            R.id.main_seekbar_red -> {

                val red = if (isDecimal){
                    seekBar.progress.toString()
                }else{
                    java.lang.Integer.toHexString(seekBar.progress)
                }

                mEditTextRed.setText(red)
                valueRed = seekBar.progress.toString()
            }

            R.id.main_seekbar_green -> {

                val green = if (isDecimal){
                    seekBar.progress.toString()
                }else{
                    java.lang.Integer.toHexString(seekBar.progress)
                }

                mEditTextGreen.setText(green)
                valueGreen = seekBar.progress.toString()
            }

            R.id.main_seekbar_blue -> {

                val blue = if (isDecimal){
                    seekBar.progress.toString()
                }else{
                    java.lang.Integer.toHexString(seekBar.progress)
                }

                mEditTextBlue.setText(blue)
                valueBlue = seekBar.progress.toString()
            }

        }

        updateColorView()
        updateTextView(valueAlpha, valueRed, valueGreen, valueBlue, isDecimal)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {

    }

    override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {

        when(v.id){

            R.id.main_edittext_alpha ->{
                if(event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
                    var text = mEditTextAlpha.text.toString()
                    if (isDecimal){
                        var value = text.toInt()
                        if( value > 255) value = 255
                        mSeekbarAlpha.progress = value
                    }else{
                        val validator = Pattern.compile("[0-9A-Fa-f]{2}")
                        val matcher = validator.matcher(text)
                        if (matcher.matches()){
                            var value = Integer.parseInt(text, 16)
                            if( value > 255) value = 255
                            mSeekbarAlpha.progress = value
                        }else{
                            val toast = Toast.makeText(this, "Formato inválido", Toast.LENGTH_LONG)
                            toast.show()
                            mSeekbarAlpha.progress = 0
                            mEditTextAlpha.setText("")
                        }
                    }

                }
            }

            R.id.main_edittext_red ->{
                if(event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
                    var text = mEditTextRed.text.toString()
                    if (isDecimal){
                        var value = text.toInt()
                        if( value > 255) value = 255
                        mSeekbarRed.progress = value
                    }else{
                        val validator = Pattern.compile("[0-9A-Fa-f]{2}")
                        val matcher = validator.matcher(text)
                        if (matcher.matches()){
                            var value = Integer.parseInt(text, 16)
                            if( value > 255) value = 255
                            mSeekbarRed.progress = value
                        }else{
                            val toast = Toast.makeText(this, "Formato inválido", Toast.LENGTH_LONG)
                            toast.show()
                            mSeekbarRed.progress = 0
                            mEditTextRed.setText("")
                        }
                    }
                }
            }

            R.id.main_edittext_green ->{
                if(event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
                    var text = mEditTextGreen.text.toString()
                    if (isDecimal){
                        var value = text.toInt()
                        if( value > 255) value = 255
                        mSeekbarGreen.progress = value
                    }else{
                        val validator = Pattern.compile("[0-9A-Fa-f]{2}")
                        val matcher = validator.matcher(text)
                        if (matcher.matches()){
                            var value = Integer.parseInt(text, 16)
                            if( value > 255) value = 255
                            mSeekbarGreen.progress = value
                        }else{
                            val toast = Toast.makeText(this, "Formato inválido", Toast.LENGTH_LONG)
                            toast.show()
                            mSeekbarGreen.progress = 0
                            mEditTextGreen.setText("")
                        }
                    }
                }
            }

            R.id.main_edittext_blue ->{
                if(event.action == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER){
                    var text = mEditTextBlue.text.toString()
                    if (isDecimal){
                        var value = text.toInt()
                        if( value > 255) value = 255
                        mSeekbarBlue.progress = value
                    }else{
                        val validator = Pattern.compile("[0-9A-Fa-f]{2}")
                        val matcher = validator.matcher(text)
                        if (matcher.matches()){
                            var value = Integer.parseInt(text, 16)
                            if( value > 255) value = 255
                            mSeekbarBlue.progress = value
                        }else{
                            val toast = Toast.makeText(this, "Formato inválido", Toast.LENGTH_LONG)
                            toast.show()
                            mSeekbarBlue.progress = 0
                            mEditTextBlue.setText("")
                        }
                    }
                }
            }

        }

        updateColorView()

        return false
    }

    override fun onClick(v: View) {

        when(v.id){

            R.id.radioButtonARGB -> {
                mRadioButtonHex.isChecked = !mRadioButtonARGB.isChecked
                isDecimal = mRadioButtonARGB.isChecked
                val inputType = if (isDecimal) InputType.TYPE_CLASS_NUMBER else InputType.TYPE_CLASS_TEXT
                mEditTextAlpha.inputType = inputType
                mEditTextRed.inputType = inputType
                mEditTextGreen.inputType = inputType
                mEditTextBlue.inputType = inputType
            }

            R.id.radioButtonHex -> {
                mRadioButtonARGB.isChecked = !mRadioButtonHex.isChecked
                isDecimal = !mRadioButtonHex.isChecked
                val inputType = if (isDecimal) InputType.TYPE_CLASS_NUMBER else InputType.TYPE_CLASS_TEXT
                mEditTextAlpha.inputType = inputType
                mEditTextRed.inputType = inputType
                mEditTextGreen.inputType = inputType
                mEditTextBlue.inputType = inputType
            }

        }

    }

    private fun updateColorView() {
        val color = Color.argb(mSeekbarAlpha.progress, mSeekbarRed.progress, mSeekbarGreen.progress, mSeekbarBlue.progress)
        mColorView.setBackgroundColor(color)
    }

    private fun updateTextView(valueAlpha: String, valueRed: String, valueGrenn: String, valueBlue: String, isDecimal: Boolean){

        val alpha: String
        val red: String
        val green: String
        val blue: String

        if (isDecimal){
            alpha = java.lang.Integer.toHexString(valueAlpha.toInt())
            red = java.lang.Integer.toHexString(valueRed.toInt())
            green = java.lang.Integer.toHexString(valueGrenn.toInt())
            blue = java.lang.Integer.toHexString(valueBlue.toInt())
        }else{
            alpha = java.lang.Integer.parseInt(valueAlpha).toString()
            red = java.lang.Integer.parseInt(valueRed).toString()
            green = java.lang.Integer.parseInt(valueGrenn).toString()
            blue = java.lang.Integer.parseInt(valueBlue).toString()
        }

        mTextViewValue.text = "$alpha, $red, $green, $blue"
    }

}


