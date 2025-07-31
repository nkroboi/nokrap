package com.nkrolabs.nokrap
data class VCard(
    val label: String,
    val fullName: String,
    val company: String,
    val jobTitle: String,
    val phone: String,
    val email: String,
    val website: String?,
    val address: String?
) {
    fun toVCardString(): String {
        return """
            BEGIN:VCARD
            VERSION:3.0
            FN:$fullName
            ORG:$company
            TITLE:$jobTitle
            TEL:$phone
            EMAIL:$email
            ${if (!website.isNullOrBlank()) "URL:$website" else ""}
            ${if (!address.isNullOrBlank()) "ADR:$address" else ""}
            END:VCARD
        """.trimIndent()
    }
}