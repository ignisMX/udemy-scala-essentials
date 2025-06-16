package functional

object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  val stringToIntConverter = new Function[String, Int] {
    override def apply(value: String): Int = value.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(numberOne: Int, numberTwo: Int): Int = numberOne + numberTwo
  }

  // Function types Function2[A,B,R] === (A,B) => R

  // All scala functions are objects

  /*
  1. a function which takes 2 strings and concatenates them
  2. transform the MyPredicate and MyTransformer into functions types
  3. define a function which takes and int and returns another function which takes and int and returns an int
    - what's the type of this function
    - hwo to do it
   */
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
