package patternmatching

object PatternsEverywhere extends App {

  // big idea #1
  try {
    //code
  } catch {
    case e: RuntimeException => "runtime exception"
    case npe: NullPointerException => "npe"
    case _ => "something else"
  }

  // catches are actually MATCHES
  /*
  try{
  // code
  } catch(e){
    e match {
      case e: RuntimeException => "runtime"
      case npe: NullPointerException => "npe"
      case _ => "something else"
    }
  }
   */

  // big idea 2
  val list = List(1, 2, 3, 4)
  val evenOne = for {
    value <- list if value % 2 == 0
  } yield 10 * value

  // generators are also based on PATTERN MATCHING
  val tuples = List((1, 2), (3, 4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators,

  // big idea #3
  val tuple = (1, 2, 3)
  val (a, b, c) = tuple
  println(b)
  //multiple value definitions based on PATTERN MATCHING
  //ALL THE POWER

  // big idea #4 - NEW
  val mappedList = list.map {
    case value if value % 2 == 0 => s"$value is even"
    case 1 => "the one"
    case _ => "something else"
  }

  val mappedList2 = list.map{
    value => value match {
      case value if value % 2 == 0 => s"$value is even"
      case 1 => "the one"
      case _ => "something else"
    }
  }

  println(mappedList)

}
