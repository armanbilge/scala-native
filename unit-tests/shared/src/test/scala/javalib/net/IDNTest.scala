// ported from android luni 79179d5284bdc6854d1366226d26eec8b766d1ac

package javalib.net

import java.net.IDN

import org.junit.Test
import org.junit.Assert._

import scalanative.junit.utils.AssertThrows.assertThrows

class IDNTest {

  @Test
  def toAscii(): Unit = {
    assertThrows(classOf[NullPointerException], IDN.toASCII(null))

    assertThrows(
      classOf[IllegalArgumentException],
      IDN.toASCII("www.m\uE400kitorppa.edu")
    )
    assertThrows(
      classOf[IllegalArgumentException],
      IDN.toASCII("www.\u672C\uFE73\uFFFF.jp")
    )

    assertEquals(
      "www.xn--gwtq9nb2a.jp",
      IDN.toASCII("www.\u65E5\u672C\u5E73.jp")
    )
    assertEquals(
      "www.xn--vckk7bxa0eza9ezc9d.com",
      IDN.toASCII(
        "www.\u30CF\u30F3\u30C9\u30DC\u30FC\u30EB\u30B5\u30E0\u30BA.com"
      )
    )
    assertEquals(
      "www.xn--frgbolaget-q5a.nu",
      IDN.toASCII("www.f\u00E4rgbolaget.nu")
    )
    assertEquals("www.xn--bcher-kva.de", IDN.toASCII("www.b\u00FCcher.de"))
    assertEquals(
      "www.xn--brndendekrlighed-vobh.com",
      IDN.toASCII("www.br\u00E6ndendek\u00E6rlighed.com")
    )
    assertEquals(
      "www.xn--rksmrgs-5wao1o.se",
      IDN.toASCII("www.r\u00E4ksm\u00F6rg\u00E5s.se")
    )
    assertEquals(
      "www.xn--9d0bm53a3xbzui.com",
      IDN.toASCII("www.\uC608\uBE44\uAD50\uC0AC.com")
    )
    assertEquals(
      "xn--lck1c3crb1723bpq4a.com",
      IDN.toASCII("\u7406\u5BB9\u30CA\u30AB\u30E0\u30E9.com")
    )
    assertEquals(
      "xn--l8je6s7a45b.org",
      IDN.toASCII("\u3042\u30FC\u308B\u3044\u3093.org")
    )
    assertEquals(
      "www.xn--frjestadsbk-l8a.net",
      IDN.toASCII("www.f\u00E4rjestadsbk.net")
    )
    assertEquals(
      "www.xn--mkitorppa-v2a.edu",
      IDN.toASCII("www.m\u00E4kitorppa.edu")
    )
  }

  @Test
  def toAsciiWithFlags(): Unit = {
    assertEquals(
      "www.xn--gwtq9nb2a.jp",
      IDN.toASCII("www.\u65E5\u672C\u5E73.jp", 0)
    )
    assertEquals(
      "www.xn--vckk7bxa0eza9ezc9d.com",
      IDN.toASCII(
        "www.\u30CF\u30F3\u30C9\u30DC\u30FC\u30EB\u30B5\u30E0\u30BA.com",
        0
      )
    )
    assertEquals(
      "www.xn--frgbolaget-q5a.nu",
      IDN.toASCII("www.f\u00E4rgbolaget.nu", IDN.ALLOW_UNASSIGNED)
    )
    assertEquals(
      "www.xn--bcher-kva.de",
      IDN.toASCII("www.b\u00FCcher.de", IDN.ALLOW_UNASSIGNED)
    )
    assertEquals(
      "www.google.com",
      IDN.toASCII("www.google\u002Ecom", IDN.USE_STD3_ASCII_RULES)
    )
  }

  @Test
  def toUnicode(): Unit = {
    assertThrows(classOf[NullPointerException], IDN.toUnicode(null))

    assertEquals("", IDN.toUnicode(""))
    assertEquals("www.bcher.de", IDN.toUnicode("www.bcher.de"))
    assertEquals("www.b\u00FCcher.de", IDN.toUnicode("www.b\u00FCcher.de"))
    assertEquals(
      "www.\u65E5\u672C\u5E73.jp",
      IDN.toUnicode("www.\u65E5\u672C\u5E73.jp")
    )
    assertEquals(
      "www.\u65E5\u672C\u5E73.jp",
      IDN.toUnicode("www\uFF0Exn--gwtq9nb2a\uFF61jp")
    )
    assertEquals(
      "www.\u65E5\u672C\u5E73.jp",
      IDN.toUnicode("www.xn--gwtq9nb2a.jp")
    )
  }

  @Test
  def toUnicodeWithFlags(): Unit = {
    assertEquals("", IDN.toUnicode("", IDN.ALLOW_UNASSIGNED));
    assertEquals(
      "www.f\u00E4rgbolaget.nu",
      IDN.toUnicode("www.f\u00E4rgbolaget.nu", IDN.USE_STD3_ASCII_RULES)
    );
    assertEquals(
      "www.r\u00E4ksm\u00F6rg\u00E5s.nu",
      IDN.toUnicode(
        "www.r\u00E4ksm\u00F6rg\u00E5s\u3002nu",
        IDN.USE_STD3_ASCII_RULES
      )
    );
    // RI bug. It cannot parse "www.xn--gwtq9nb2a.jp" when
    // USE_STD3_ASCII_RULES is set.
    assertEquals(
      "www.\u65E5\u672C\u5E73.jp",
      IDN.toUnicode("www\uFF0Exn--gwtq9nb2a\uFF61jp", IDN.USE_STD3_ASCII_RULES)
    );
  }

}
