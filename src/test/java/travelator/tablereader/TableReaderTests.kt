package travelator.tablereader

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.StringReader

class TableReaderTests {
    @Test
    fun `empty list returns empty list`() {
        assertEquals(
            emptyList<Map<String, String>>(),
            readTable(emptyList<String>())
        )
    }

    @Test
    fun `one line of input with default field names`() {
        assertEquals(
            listOf(
                mapOf("0" to "field0", "1" to "field1")
            ),
            readTable(
                listOf(
                    "field0,field1"
                )
            )
        )
    }

    @Test
    fun `empty line returns empty map`() {
        assertEquals(
            listOf(
                emptyMap<String, String>()
            ),
            readTable(
                listOf(
                    ""
                )
            )
        )
    }

    @Test
    fun `two lines of input with default field names`() {
        assertEquals(
            listOf(
                mapOf("0" to "row0field0", "1" to "row0field1"),
                mapOf("0" to "row1field0", "1" to "row1field1")
                ),
            readTable(
                listOf(
                    "row0field0,row0field1",
                    "row1field0,row1field1"
                    )
            )
        )
    }

    @Test
    fun `takes headers from header line`() {
        assertEquals(
            listOf(
                mapOf("H0" to "field0", "H1" to "field1")
            ),
            readTableWithHeader(
                listOf(
                    "H0,H1",
                    "field0,field1"
                )
            )
        )
    }

    @Test
    fun `readTableWithHeader on empty list returns empty list`() {
        assertEquals(
            emptyList<String>(),
            readTableWithHeader(
                emptyList()
            )
        )
    }

    @Test
    fun `can specify header names when there is not header row`() {
        val headers = listOf("apple", "banana")
        assertEquals(
            listOf(
                mapOf(
                    "apple" to "field0",
                    "banana" to "field1"
                )
            ),
            readTable(
                listOf("field0,field1"),
                headers::get
            )
        )
    }

    @Test
    fun `can specify splitter`() {
        assertEquals(
            listOf(
                mapOf(
                    "header1" to "field0",
                    "header2" to "field1"
                )
            ),
            readTableWithHeader(
                listOf(
                    "header1\theader2",
                    "field0\tfield1"
                ),
                splitOnTab
            )
        )
    }

    @Test
    fun `read from reader`() {
        val fileContents = """
            H0,H1
            row0field0,row0field1
            row1field0,row1field1
        """.trimIndent()
        StringReader(fileContents).useLines { lines ->
            val result = readTableWithHeader(lines).toList()
            assertEquals(
                listOf(
                    mapOf("H0" to "row0field0", "H1" to "row0field1"),
                    mapOf("H0" to "row1field0", "H1" to "row1field1")
                ),
                result
            )
        }
    }
}

fun readTableWithHeader(
    lines: List<String>,
    splitter: (String) -> List<String> = splitOnComma
): List<Map<String, String>> =
    readTableWithHeader(
        lines.asSequence(),
        splitter
    )


fun readTable(
    lines: List<String>,
    headerProvider: (Int) -> String = Int::toString,
    splitter: (String) -> List<String> = splitOnComma
): List<Map<String, String>> =
    readTable(
        lines.asSequence(),
        headerProvider,
        splitter
    ).toList()