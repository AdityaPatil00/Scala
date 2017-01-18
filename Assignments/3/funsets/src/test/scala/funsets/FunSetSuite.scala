package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(1)
    val s5 = union(s1,s2)
    val s6 = union(union(s1,s2),s3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersection contains common elements of each set") {
    new TestSets {
      val s = intersect(s1, s2)
      val si1 = intersect(s1,s5)
      assert(!contains(s, 1), "Intersect 1")
      assert(!contains(s, 2), "Intersect 2")
      assert(contains(si1, 1), "Intersect 2")
    }
  }


  test("Difference contains elements in the first set but not in the second") {
    new TestSets {
      val s = diff(s1, s2)
      val sd1 = diff(s5,s1)
      val sd2 = diff(s1,s1)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 2), "Diff 2")
      assert(contains(sd1, 2), "Diff 2")
      assert(!contains(sd2, 1), "Diff 1")
    }
  }

  test("Filter the main set to get a subset after passing through a certain predicate") {
    new TestSets {
      val sf1 = filter(s1,x=>x>0)
      val sf2 = filter(s6,x=>x%2==0)
      val sf3 = filter(s6,x=>x<0)
      assert(contains(sf1, 1), "Positive subset")
      assert(contains(sf2,2), "Modulo 2")
      assert(!contains(sf2,1), "Modulo 2")
      assert(!contains(sf3,1), "negative subset")
      assert(!contains(sf3,2), "negative subset")
      assert(!contains(sf3,3), "negative subset")
      assert(!contains(sf3,-1), "negative subset")
    }
  }

  test("forall is used for checking if all elements of the set obey the predicate") {
    new TestSets {
      val sforall1 = forall(s1,x=>x>0)
      val sforall2 = forall(s6,x=>x>0)
      val sforall3 = forall(s6,x=>x<0)
      val sforall4 = forall(s6,x=>x==2)
      assert(sforall1, "Positive set")
      assert(sforall2, "Positive set")
      assert(!sforall3, "Negative set")
      assert(!sforall4, "s6 is not a sinleton set")
    }
  }

  test("exists is made up of forall") {
    new TestSets {
      val exists1 = exists(s1,x=>x>0)
      val exists2 = exists(s6,x=>x>0)
      val exists3 = exists(s6,x=>x<0)
      val exists4 = exists(s6,x=>x==2)
      assert(exists1, "at least one positive integer")
      assert(exists2, "at least one positive integer")
      assert(!exists3, "No negative element in the set")
      assert(exists4, "One element of s6 is 2")
    }
  }

  test("map to some other set") {
    new TestSets {
      val smap1 = map(s1,x => 2*x)
      val smap2 = map(s6,x => -1*x)
      val smap3 = map(s6,x => x+2)
      val smap4 = map(s6,x=>x)
      assert(contains(smap1,2))
      assert(contains(smap2,-2), "the new set now contains negative integers")
      assert(contains(smap2,-1), "the new set now contains negative integers")
      assert(contains(smap3,4))
      assert(contains(smap4,1))
      assert(contains(smap4,3))
    }
  }


}
