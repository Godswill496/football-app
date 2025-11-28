package ie.setu.persistence

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver
import ie.setu.models.Player
import ie.setu.models.Match
import ie.setu.models.MatchPlayer
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class JSONSerializer(private val file: File) : Serializer {

    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(JettisonMappedXmlDriver())

        xStream.allowTypes(
            arrayOf(
                Player::class.java,
                Match::class.java,
                MatchPlayer::class.java
            )
        )

        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject()
        inputStream.close()
        return obj
    }

    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(JettisonMappedXmlDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}
