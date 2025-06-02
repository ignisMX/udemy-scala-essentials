package basics

object StringOperations extends App {

  private val greetingOne: String = "Hello, I am learning scala"

  println(greetingOne.charAt(2))
  println(greetingOne.substring(7, 11))
  println(greetingOne.split(" ").toList)
  println(greetingOne.startsWith("Hello"))
  println(greetingOne.replace(" ", "-"))
  println(greetingOne.toLowerCase())
  println(greetingOne.length)

  val numberString: String = "45"
  val stringNumber: Int = numberString.toInt
  println('p' +: numberString :+ 's')
  println(greetingOne.reverse)
  println(greetingOne.take(2))

  //Scala-specific: String interpolation

  //S interpolator
  val name = "David"
  val age = 12
  val greetingTwo = s"Hello, my name is $name and I am $age years old"
  val greetingThree = s"Hello, my name is $name and I am ${age + 1} years old"
  println(greetingTwo)
  println(greetingThree)

  //F interpolator
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.2f burgers per minute"
  println(myth)

}
