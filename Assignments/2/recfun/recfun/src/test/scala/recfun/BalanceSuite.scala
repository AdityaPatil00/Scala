package recfun

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main.balance

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: 'I told him ...' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: counting is not enough") {
    assert(!balance("())(".toList))
  }

  test("balance: with no paranthesis") {
    assert(balance("skdjsajkd".toList))
  }

  test("balance: starting with opposite paranthesis") {
    assert(!balance(")(".toList))
  }

  test("balance: starting with opposite paranthesis but count also unbalanced") {
    assert(!balance(")".toList))
  }

  test("balance: Empty list") {
    assert(balance("".toList))
  }

  test("balance: My favorite") {
    assert(balance("((()))".toList))
  }
}
