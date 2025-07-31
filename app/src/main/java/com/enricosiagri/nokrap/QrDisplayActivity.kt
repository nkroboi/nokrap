package com.nkrolabs.nokrap

import com.nkrolabs.nokrap.databinding.ActivityQrDisplayBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.app.AlertDialog

class QrDisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQrDisplayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQrDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show the ActionBar back arrow
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // (OPTIONAL: only if you add a Button with id backButton in your XML)
        binding.backButton.setOnClickListener {
            finish()
        }

        val label = intent.getStringExtra("label") ?: return finish()
        val card = VCardStorage.getByLabel(this, label) ?: return finish()
        val bitmap = QRCodeUtils.generate(card.toVCardString())
        binding.qrImage.setImageBitmap(bitmap)
        binding.cardTitle.text = label

        binding.deleteButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Delete vCard?")
                .setMessage("Are you sure you want to delete this vCard?")
                .setPositiveButton("Delete") { _, _ ->
                    val label = intent.getStringExtra("label") ?: return@setPositiveButton
                    VCardStorage.deleteByLabel(this, label)
                    finish()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}