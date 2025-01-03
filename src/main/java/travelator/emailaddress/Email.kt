package travelator.emailaddress

import travelator.emailaddress.EmailAddress

data class Email(
    val to: EmailAddress,
    val from: EmailAddress,
    val subject: String,
    val body: String
)