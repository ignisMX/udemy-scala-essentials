package functional

object AnonymousFunction extends App {

  // anonymous function (LAMBDA)
  private val doublerOne: Int => Int = (number: Int) => number * 2
  private val doublerTwo: Int => Int = _ * 2

  println(doublerOne(3))
  println(doublerTwo(5))

  // multiple params in a lambda
  val adderOne: (Int, Int) => Int = (numberOne: Int, numberTwo: Int) => numberOne + numberTwo
  val adderTwo: (Int, Int) => Int = _ + _

  println(adderOne(3, 2))
  println(adderTwo(4, 5))

  // no params
  val justDoSomething: () => Int = () => 3

  // careful
  println(justDoSomething)
  println(justDoSomething())

  // curly braces with lambdas
  val stringToInt = { (str: String) => str.toInt }

  // MOAR syntactic sugar
  val niceIncrementer: Int => Int = _ + 1
  val niceAdder: (Int, Int) => Int = _ + _

  println(niceIncrementer(5))
  println(niceAdder(8,5))

  /*
  1. MyList: replace all FunctionX call with lambdas
  2. Rewrite the "special" adder as anonymous function

  * */

}
