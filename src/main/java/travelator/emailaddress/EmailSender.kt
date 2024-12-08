package travelator.emailaddress

import java.net.InetAddress

fun interface ISendEmail {
    fun send(email: Email)
}

fun createEmailSender(
    serverAddress: InetAddress,
    username: String,
    password: String
) = ISendEmail { email ->
    sendEmail(
        email,
        serverAddress,
        username,
        password
    )
}

fun sendEmail(
    email: Email,
    serverAddress: InetAddress,
    username: String,
    password: String
) {

}

