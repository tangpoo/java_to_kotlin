package travelator.tablereader

fun readTableWithHeader(
    lines: Sequence<String>,
    splitter: (String) -> List<String>
) = when {
    lines.firstOrNull() == null -> emptySequence()
    else ->
        readTable(
            lines.drop(1),
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

// 빈 문자열에 대해 String.split을 호출하면 빈 리스트가 아니라
// 빈 문자열의 리스트를 반환하기 때문에 빈 문자열을 별도로 처리할 필요가 있다.
private fun String.splitFields(separator: String): List<String> =
    if (isEmpty()) emptyList() else split(separator)
