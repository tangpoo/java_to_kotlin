package travelator.tablereader

fun readTableWithHeader(lines: List<String>): List<Map<String, String>> {
    return readTable(lines)
}

fun readTable(lines: List<String>): List<Map<String, String>> {
    return lines.map(::parseLine)
}

private fun parseLine(line: String): Map<String, String> {
    val values = splitFields(line)
    val headerProvider: (Int) -> String = Int::toString
    val keys = values.indices.map(headerProvider)
    return keys.zip(values).toMap()
}

private fun splitFields(line: String): List<String> =
    if (line.isEmpty()) emptyList() else line.split(",")