package travelator.tablereader

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

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
            readTable(listOf(
                "field0,field1"
            ))
        )
    }

    @Test
    fun `empty line returns empty map`() {
        assertEquals(
            listOf(
                emptyMap<String, String>()
            ),
            readTable(listOf(
                ""
            ))
        )
    }

    @Test
    fun `two lines of input with default field names`() {
        assertEquals(
            listOf(
                mapOf("0" to "row0field0", "1" to "row0field1"),
                mapOf("0" to "row1field0", "1" to "row1field1")
                ),
            readTable(listOf(
                "row0field0,row0field1",
                "row1field0,row1field1"
                ))
        )
    }
}