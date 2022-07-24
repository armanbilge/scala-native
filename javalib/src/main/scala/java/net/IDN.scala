// ported from android luni 79179d5284bdc6854d1366226d26eec8b766d1ac
// ported from icu4j 3f043c7693e20c8cded76035918dad104e7256e3

package java.net

object IDN {

  final val ALLOW_UNASSIGNED = 1
  final val USE_STD3_ASCII_RULES = 2

  def toASCII(input: String, flags: Int): String = ???

  def toASCII(input: String): String = toASCII(input, 0)

  def toUnicode(input: String, flags: Int): String = ???

  def toUnicode(input: String): String = toUnicode(input, 0)

}
