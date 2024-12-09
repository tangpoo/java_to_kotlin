package travelator.emailaddress

import java.net.InetAddress

fun createEmailSender(
    serverAddress: InetAddress,
    username: String,
    password: String
): (Email) -> Unit =
    { email ->
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

