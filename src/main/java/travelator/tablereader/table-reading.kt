package travelator.tablereader

fun readTableWithHeader(
    linesAsSequence: Sequence<String>,
    lines: List<String>,
    splitter: (String) -> List<String>
) = when {
    linesAsSequence.firstOrNull() == null -> emptySequence()
    else ->
        readTable(
            linesAsSequence.drop(1),
            headerProviderFrom(lines.first(), splitter),
            splitter
        )
}.toList()

fun headerProviderFrom(
    header: String,
    splitter: (String) -> List<String>
): (Int) -> String {
    val headers = splitter(header)
    return { index -> headers[index] }
}




fun readTable(
    lines: Sequence<String>,
    headerProvider: (Int) -> String = Int::toString,
    splitter: (String) -> List<String> = splitOnComma
) = lines.map {
    parseLine(it, headerProvider, splitter)
}

fun splitOn(separators: String) = { line: String ->
    line.splitFields(separators)
}

val splitOnComma: (String) -> List<String> = splitOn(",")

val splitOnTab: (String) -> List<String> = splitOn("\t")

private fun parseLine(
    line: String,
    headerProvider: (Int) -> String,
    splitter: (String) -> List<String>
): Map<String, String> {
    val values = splitter(line)
    val keys = values.indices.map(headerProvider)
    return keys.zip(values).toMap()
}

private fun String.splitFields(separator: String): List<String> =
    if (isEmpty()) emptyList() else split(separator)
