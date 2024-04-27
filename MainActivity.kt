package com.example.quickbrew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.view.LayoutInflater
import android.widget.PopupWindow
import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var showPopupInstruct : Button
    private lateinit var Items: TextView
    private lateinit var coffee: String
    private var side: String = ""
    private var stringList = mutableListOf<String>()
    private var selectedButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //coffee
        findViewById<ImageButton>(R.id.affoButton).setOnClickListener { onCoffeeButtonClick(it) }
        findViewById<ImageButton>(R.id.amerButton).setOnClickListener { onCoffeeButtonClick(it) }
        findViewById<ImageButton>(R.id.cappButton).setOnClickListener { onCoffeeButtonClick(it) }
        findViewById<ImageButton>(R.id.esprButton).setOnClickListener { onCoffeeButtonClick(it) }
        findViewById<ImageButton>(R.id.frappButton).setOnClickListener { onCoffeeButtonClick(it) }
        findViewById<ImageButton>(R.id.mochButton).setOnClickListener { onCoffeeButtonClick(it) }

        //sides
        findViewById<ImageButton>(R.id.croiButton).setOnClickListener { onSideButtonClick(it) }
        findViewById<ImageButton>(R.id.donuButton).setOnClickListener { onSideButtonClick(it) }
        findViewById<ImageButton>(R.id.muffButton).setOnClickListener { onSideButtonClick(it) }


        showPopupInstruct = findViewById(R.id.instrucButton)
        showPopupInstruct.setOnClickListener {
            showPopup(this)
        }

        Items = findViewById(R.id.selectedItem)
    }

    private fun onCoffeeButtonClick(clickedButton: View) {
        val zoomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        clickedButton.startAnimation(zoomOutAnimation)

        selectedButton?.isSelected = false
        selectedButton = clickedButton as ImageButton
        selectedButton?.isSelected = true

        coffee = when (clickedButton.id) {
            R.id.affoButton -> "Affogato"
            R.id.amerButton -> "Americano"
            R.id.cappButton -> "Cappuccino"
            R.id.esprButton -> "Espresso"
            R.id.frappButton -> "Frappuccino"
            R.id.mochButton -> "Mocha"
            else -> ""
        }

        stringList.clear()
        stringList.add(coffee)
        "$coffee selected".also { Items.text = it }
    }

    private fun onSideButtonClick(clickedButton: View) {
        val zoomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.zoom_out)
        clickedButton.startAnimation(zoomOutAnimation)

        selectedButton?.isSelected = false
        selectedButton = clickedButton as ImageButton
        selectedButton?.isSelected = true

        side = when (clickedButton.id) {
            R.id.croiButton  -> "Croissant"
            R.id.donuButton -> "Donut"
            R.id.muffButton -> "Muffin"
            else -> ""
        }

        stringList.clear()
        stringList.add(side)
        "$side selected".also { Items.text = it }
    }

    fun onConfirmButtonClick(view: View) {
        val selectedCoffee = coffee.ifEmpty { "No coffee" }
        val selectedSide = if (side.isNotEmpty()) " with $side" else ""
        "Your order: $selectedCoffee$selectedSide".also { Items.text = it }

        coffee = ""
        side = ""
    }

}


private fun showPopup(context: Context) {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val popupView = inflater.inflate(R.layout.popup, null)

    val width = 1080
    val height = 2226

    val instructWindow = PopupWindow(popupView, width, height, true)
    instructWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)

    val closeButton = popupView.findViewById<Button>(R.id.closeButton)
    closeButton.setOnClickListener{
        instructWindow.dismiss()
    }
}




