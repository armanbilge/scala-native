package scala.reflect

import org.junit.Test
import org.junit.Assert._

class ClassTagTest {

  @Test def primitiveClassTagEquality(): Unit = {
    assertEquals(ClassTag.Any, implicitly[ClassTag[Any]])
    assertEquals(ClassTag.AnyRef, implicitly[ClassTag[AnyRef]])
    assertEquals(ClassTag.AnyVal, implicitly[ClassTag[AnyVal]])
    assertEquals(ClassTag.Boolean, implicitly[ClassTag[Boolean]])
    assertEquals(ClassTag.Byte, implicitly[ClassTag[Byte]])
    assertEquals(ClassTag.Char, implicitly[ClassTag[Char]])
    assertEquals(ClassTag.Double, implicitly[ClassTag[Double]])
    assertEquals(ClassTag.Float, implicitly[ClassTag[Float]])
    assertEquals(ClassTag.Int, implicitly[ClassTag[Int]])
    assertEquals(ClassTag.Long, implicitly[ClassTag[Long]])
    assertEquals(ClassTag.Nothing, implicitly[ClassTag[Nothing]])
    assertEquals(ClassTag.Null, implicitly[ClassTag[Null]])
    assertEquals(ClassTag.Object, implicitly[ClassTag[Object]])
    assertEquals(ClassTag.Short, implicitly[ClassTag[Short]])
    assertEquals(ClassTag.Unit, implicitly[ClassTag[Unit]])
  }

}
