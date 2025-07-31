package com.nkrolabs.nokrap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nkrolabs.nokrap.databinding.ActivityAddVcardBinding

class AddVCardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddVcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddVcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            var rawPhone = binding.inputPhone.text.toString()
            rawPhone = rawPhone.replace("""\s|-""".toRegex(), "")

            val card = VCard(
                label = binding.inputLabel.text.toString(),
                fullName = binding.inputFullName.text.toString(),
                company = binding.inputCompany.text.toString(),
                jobTitle = binding.inputJobTitle.text.toString(),
                phone = rawPhone,
                email = binding.inputEmail.text.toString(),
                website = binding.inputWebsite.text.toString(),
                address = binding.inputAddress.text.toString(),
            )
            VCardStorage.save(this, card)
            finish()
        }
    }
}