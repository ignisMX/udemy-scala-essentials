package oobasics

object Exceptions extends App {

  val value: String = null
  // println(value.length)
  // It will crash with null pointer exception

  // 1. throwing exceptions
  //val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class,
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch exceptions
  def getInt(withException: Boolean): Int =
    if (withException) throw new RuntimeException("Not int for you")
    else 42


  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch
    case e: RuntimeException => println("caught a Runtime exception")
  finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val myException = new MyException

  throw myException

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with Stack Overflow Error
    3. PocketCalculator
      - add(x,y)
      - subtract(x,y)
      - multiply(x,y)
      - divide(x,y)

      Throw
        - Overflow Exception if add(x,y) exceeds Int.MAX_VALUE
        - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
        - MatchCalculationException for division by 0
   */
}
