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

  val concatenator = new Function2[String, String, String] {
    override def apply(valueOne: String, valueTwo: String): String = s"$valueOne $valueTwo"
  }

  println(concatenator("Omar", "Enrique"))

  val superAdder:Function[Int, Function[Int,Int]] = new Function[Int, Function1[Int,Int]]{
    override def apply(numberOne: Int): Function1[Int, Int] = new Function[Int,Int]{
      override def apply(numberTwo: Int): Int = numberTwo + numberOne
    }
  }

  val adder3=superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
