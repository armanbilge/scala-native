package scala.scalanative
package unsigned

import scala.reflect.Typeable
import scalanative.runtime.Intrinsics.castIntToRawSizeUnsigned

/** `UByte`, a 8-bit unsigned integer. */
opaque type UByte = Byte

object UByte {
  private[scalanative] inline def apply(byte: Byte): UByte = byte

  given Typeable[UByte] = new:
    def unapply(a: Any): Option[a.type & UByte] = a match
      case b: UByte => Some(b.asInstanceOf[a.type & UByte])
      case _ => None

  extension (underlying: UByte) {
    @inline final def toByte: Byte = underlying
    @inline final def toShort: Short = toInt.toShort
    @inline final def toChar: Char = toInt.toChar
    @inline final def toInt: Int = underlying & 0xff
    @inline final def toLong: Long = toInt.toLong
    @inline final def toFloat: Float = toInt.toFloat
    @inline final def toDouble: Double = toInt.toDouble

    @inline final def toUByte: UByte = underlying
    @inline final def toUShort: UShort = new UShort(toShort)
    @inline final def toUInt: UInt = new UInt(toInt)
    @inline final def toULong: ULong = new ULong(toLong)
    @inline final def toUSize: USize = new USize(
      castIntToRawSizeUnsigned(toInt)
    )

    /** Returns the bitwise negation of this value.
     *  @example
     *    {{{~5 == -6 // in binary: ~00000101 == // 11111010}}}
     */
    @inline final def unary_~ : UInt = ~toUInt

    /** Returns this value bit-shifted left by the specified number of bits,
     *  filling in the new right bits with zeroes.
     *  @example
     *    {{{6 << 3 == 48 // in binary: 0110 << 3 == 0110000}}}
     */
    @inline final def <<(x: Int): UInt = toUInt << x

    /** Returns this value bit-shifted left by the specified number of bits,
     *  filling in the new right bits with zeroes.
     *  @example
     *    {{{6 << 3 == 48 // in binary: 0110 << 3 == 0110000}}}
     */
    @inline final def <<(x: Long): UInt = toUInt << x

    /** Returns this value bit-shifted right by the specified number of bits,
     *  filling the new left bits with zeroes.
     *  @example
     *    {{{21 >>> 3 == 2 // in binary: 010101 >>> 3 == 010}}}
     *  @example
     *    {{{ 4294967275 >>> 3 == 536870909 // in binary: 11111111 11111111
     *    11111111 11101011 >>> 3 == // 00011111 11111111 11111111 11111101 }}}
     */
    @inline final def >>>(x: Int): UInt = toUInt >>> x

    /** Returns this value bit-shifted right by the specified number of bits,
     *  filling the new left bits with zeroes.
     *  @example
     *    {{{21 >>> 3 == 2 // in binary: 010101 >>> 3 == 010}}}
     *  @example
     *    {{{ 4294967275 >>> 3 == 536870909 // in binary: 11111111 11111111
     *    11111111 11101011 >>> 3 == // 00011111 11111111 11111111 11111101 }}}
     */
    @inline final def >>>(x: Long): UInt = toUInt >>> x

    /** Returns this value bit-shifted left by the specified number of bits,
     *  filling in the right bits with the same value as the left-most bit of
     *
     *  @example
     *    {{{ 4294967275 >> 3 == 4294967293 // in binary: 11111111 11111111
     *    11111111 11101011 >> 3 == // 11111111 11111111 11111111 11111101 }}}
     */
    @inline final def >>(x: Int): UInt = toUInt >> x

    /** Returns this value bit-shifted left by the specified number of bits,
     *  filling in the right bits with the same value as the left-most bit of
     *
     *  @example
     *    {{{ 4294967275 >> 3 == 4294967293 // in binary: 11111111 11111111
     *    11111111 11101011 >> 3 == // 11111111 11111111 11111111 11111101 }}}
     */
    @inline final def >>(x: Long): UInt = toUInt >> x

    @inline final def compareTo(x: UByte): Int =
      (underlying & 0xff) - (x & 0xff)

    /** Returns `true` if this value is equal to x, `false` otherwise. */
    @inline final def ==(x: UByte): Boolean = underlying == x

    /** Returns `true` if this value is equal to x, `false` otherwise. */
    @inline final def ==(x: UShort): Boolean = toUInt == x.toUInt

    /** Returns `true` if this value is equal to x, `false` otherwise. */
    @inline final def ==(x: UInt): Boolean = toUInt == x

    /** Returns `true` if this value is equal to x, `false` otherwise. */
    @inline final def ==(x: ULong): Boolean = toULong == x

    /** Returns `true` if this value is not equal to x, `false` otherwise. */
    @inline final def !=(x: UByte): Boolean = underlying != x

    /** Returns `true` if this value is not equal to x, `false` otherwise. */
    @inline final def !=(x: UShort): Boolean = toUInt != x.toUInt

    /** Returns `true` if this value is not equal to x, `false` otherwise. */
    @inline final def !=(x: UInt): Boolean = toUInt != x

    /** Returns `true` if this value is not equal to x, `false` otherwise. */
    @inline final def !=(x: ULong): Boolean = toULong != x

    /** Returns `true` if this value is less than x, `false` otherwise. */
    @inline final def <(x: UByte): Boolean = toUInt < x.toUInt

    /** Returns `true` if this value is less than x, `false` otherwise. */
    @inline final def <(x: UShort): Boolean = toUInt < x.toUInt

    /** Returns `true` if this value is less than x, `false` otherwise. */
    @inline final def <(x: UInt): Boolean = toUInt < x

    /** Returns `true` if this value is less than x, `false` otherwise. */
    @inline final def <(x: ULong): Boolean = toULong < x

    /** Returns `true` if this value is less than or equal to x, `false`
     *  otherwise.
     */
    @inline final def <=(x: UByte): Boolean = toUInt <= x.toUInt

    /** Returns `true` if this value is less than or equal to x, `false`
     *  otherwise.
     */
    @inline final def <=(x: UShort): Boolean = toUInt <= x.toUInt

    /** Returns `true` if this value is less than or equal to x, `false`
     *  otherwise.
     */
    @inline final def <=(x: UInt): Boolean = toUInt <= x

    /** Returns `true` if this value is less than or equal to x, `false`
     *  otherwise.
     */
    @inline final def <=(x: ULong): Boolean = toULong <= x

    /** Returns `true` if this value is greater than x, `false` otherwise. */
    @inline final def >(x: UByte): Boolean = toUInt > x.toUInt

    /** Returns `true` if this value is greater than x, `false` otherwise. */
    @inline final def >(x: UShort): Boolean = toUInt > x.toUInt

    /** Returns `true` if this value is greater than x, `false` otherwise. */
    @inline final def >(x: UInt): Boolean = toUInt > x

    /** Returns `true` if this value is greater than x, `false` otherwise. */
    @inline final def >(x: ULong): Boolean = toULong > x

    /** Returns `true` if this value is greater than or equal to x, `false`
     *  otherwise.
     */
    @inline final def >=(x: UByte): Boolean = toUInt >= x.toUInt

    /** Returns `true` if this value is greater than or equal to x, `false`
     *  otherwise.
     */
    @inline final def >=(x: UShort): Boolean = toUInt >= x.toUInt

    /** Returns `true` if this value is greater than or equal to x, `false`
     *  otherwise.
     */
    @inline final def >=(x: UInt): Boolean = toUInt >= x

    /** Returns `true` if this value is greater than or equal to x, `false`
     *  otherwise.
     */
    @inline final def >=(x: ULong): Boolean = toULong >= x

    /** Returns the bitwise OR of this value and `x`. */
    @inline final def |(x: UByte): UInt = toUInt | x.toUInt

    /** Returns the bitwise OR of this value and `x`. */
    @inline final def |(x: UShort): UInt = toUInt | x.toUInt

    /** Returns the bitwise OR of this value and `x`. */
    @inline final def |(x: UInt): UInt = toUInt | x

    /** Returns the bitwise OR of this value and `x`. */
    @inline final def |(x: ULong): ULong = toULong | x

    /** Returns the bitwise AND of this value and `x`. */
    @inline final def &(x: UByte): UInt = toUInt & x.toUInt

    /** Returns the bitwise AND of this value and `x`. */
    @inline final def &(x: UShort): UInt = toUInt & x.toUInt

    /** Returns the bitwise AND of this value and `x`. */
    @inline final def &(x: UInt): UInt = toUInt & x

    /** Returns the bitwise AND of this value and `x`. */
    @inline final def &(x: ULong): ULong = toULong & x

    /** Returns the bitwise XOR of this value and `x`. */
    @inline final def ^(x: UByte): UInt = toUInt ^ x.toUInt

    /** Returns the bitwise XOR of this value and `x`. */
    @inline final def ^(x: UShort): UInt = toUInt ^ x.toUInt

    /** Returns the bitwise XOR of this value and `x`. */
    @inline final def ^(x: UInt): UInt = toUInt ^ x

    /** Returns the bitwise XOR of this value and `x`. */
    @inline final def ^(x: ULong): ULong = toULong ^ x

    /** Returns the sum of this value and `x`. */
    @inline final def +(x: UByte): UInt = toUInt + x.toUInt

    /** Returns the sum of this value and `x`. */
    @inline final def +(x: UShort): UInt = toUInt + x.toUInt

    /** Returns the sum of this value and `x`. */
    @inline final def +(x: UInt): UInt = toUInt + x

    /** Returns the sum of this value and `x`. */
    @inline final def +(x: ULong): ULong = toULong + x

    /** Returns the difference of this value and `x`. */
    @inline final def -(x: UByte): UInt = toUInt - x.toUInt

    /** Returns the difference of this value and `x`. */
    @inline final def -(x: UShort): UInt = toUInt - x.toUInt

    /** Returns the difference of this value and `x`. */
    @inline final def -(x: UInt): UInt = toUInt - x

    /** Returns the difference of this value and `x`. */
    @inline final def -(x: ULong): ULong = toULong - x

    /** Returns the product of this value and `x`. */
    @inline final def *(x: UByte): UInt = toUInt * x.toUInt

    /** Returns the product of this value and `x`. */
    @inline final def *(x: UShort): UInt = toUInt * x.toUInt

    /** Returns the product of this value and `x`. */
    @inline final def *(x: UInt): UInt = toUInt * x

    /** Returns the product of this value and `x`. */
    @inline final def *(x: ULong): ULong = toULong * x

    /** Returns the quotient of this value and `x`. */
    @inline final def /(x: UByte): UInt = toUInt / x.toUInt

    /** Returns the quotient of this value and `x`. */
    @inline final def /(x: UShort): UInt = toUInt / x.toUInt

    /** Returns the quotient of this value and `x`. */
    @inline final def /(x: UInt): UInt = toUInt / x

    /** Returns the quotient of this value and `x`. */
    @inline final def /(x: ULong): ULong = toULong / x

    /** Returns the remainder of the division of this value by `x`. */
    @inline final def %(x: UByte): UInt = toUInt % x.toUInt

    /** Returns the remainder of the division of this value by `x`. */
    @inline final def %(x: UShort): UInt = toUInt % x.toUInt

    /** Returns the remainder of the division of this value by `x`. */
    @inline final def %(x: UInt): UInt = toUInt % x

    /** Returns the remainder of the division of this value by `x`. */
    @inline final def %(x: ULong): ULong = toULong % x

    @inline final def toString(): String = toInt.toString()

    @inline def hashCode(): Int = underlying.##

    @inline def equals(obj: Any): Boolean = obj match {
      case that: UByte => underlying == that
      case _           => false
    }

    // "Rich" API

    @inline final def max(that: UByte): UByte =
      toUInt.max(that.toUInt).toUByte
    @inline final def min(that: UByte): UByte =
      toUInt.min(that.toUInt).toUByte

    @inline final def toBinaryString: String = toUInt.toBinaryString
    @inline final def toHexString: String = toUInt.toHexString
    @inline final def toOctalString: String = toUInt.toOctalString
  }

  /** The smallest value representable as a UByte. */
  final val MinValue = UByte(0.toByte)

  /** The largest value representable as a UByte. */
  final val MaxValue = UByte((-1).toByte)

  /** The String representation of the scala.UByte companion object. */
  override def toString(): String = "object scala.UByte"

  /** Language mandated coercions from UByte to "wider" types. */
  import scala.language.implicitConversions
  implicit def ubyte2ushort(x: UByte): UShort = x.toUShort
  implicit def ubyte2uint(x: UByte): UInt = x.toUInt
  implicit def ubyte2ulong(x: UByte): ULong = x.toULong
}
