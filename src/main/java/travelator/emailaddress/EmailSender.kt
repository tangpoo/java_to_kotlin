package travelator.emailaddress

import java.net.InetAddress

interface ISendEmail {
    fun send(email: Email)
}

class EmailSender(
    private val serverAddress: InetAddress,
    private val username: String,
    private val password: String
) : ISendEmail {
    override fun send(email: Email) {
        sendEmail(
            email,
            serverAddress,
            username,
            password
        )
    }

}

fun EmailSender.sendEmail(
    email: Email,
    serverAddress: InetAddress,
    username: String,
    password: String
) {

}


//설정을 알 수 있는 곳
val subsystem = Rescuing(
    EmailSender(
        inetAddress("smtp.travelator.com"),
        "username",
        "password"
    )
)


// 메세지를 보내는 곳
class Rescuing(
    private val sender: EmailSender
) {
    fun sendThanks() {
        sender.send(
            Email(
                to = EmailAddress.parse("support@internationalrescue.org"),
                from = EmailAddress.parse("support@travelator.com"),
                subject = "Thanks for your help",
                body = "..."
            )
        )
    }
}

