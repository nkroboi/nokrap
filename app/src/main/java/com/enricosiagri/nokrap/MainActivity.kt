package com.nkrolabs.nokrap

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import com.nkrolabs.nokrap.databinding.ActivityMainBinding
import android.widget.LinearLayout
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddVCardActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        val cards = VCardStorage.loadAll(this)
        binding.cardList.removeAllViews()
        for (card in cards) {
            val btn = Button(this)
            btn.text = card.label

            // Modern/minimal styling:
            btn.setBackgroundResource(R.drawable.card_item_bg)
            btn.setTextColor("#222222".toColorInt())
            btn.textSize = 16f

            // set bold typeface
            // btn.setTypeface(btn.typeface, android.graphics.Typeface.BOLD)

            // Add margin between cards:
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = (12 * resources.displayMetrics.density).toInt()
            btn.layoutParams = params

            btn.setOnClickListener {
                val intent = Intent(this, QrDisplayActivity::class.java)
                intent.putExtra("label", card.label)
                startActivity(intent)
            }
            binding.cardList.addView(btn)
        }
    }
}