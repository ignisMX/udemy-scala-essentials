package functional

object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  //map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  //filter
  println(list.filter(_ % 2 == 0))

  val toPair = (element: Int) => List(element, element + 1)
  println(list.flatMap(toPair))

  val numbers = List(1, 2, 3)
  val chars = List('a', 'b', 'c')
  val colors = List("black", "white")

  //List("a1", "a2","a3")

  val combination = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combination)

  val combination2 = numbers.flatMap(number => chars.flatMap(character => colors.map(s"$number$character-" + _)))
  println(combination2)

  //foreach
  list.foreach(print)

  //for-comprehensions
  val forCombination = for {
    number <- numbers
    character <- chars
    color <- colors
  } yield s"$number$character-$color"

  println(forCombination)

  // syntax overload
  list.map { x => x * 2 }

  /*

  1. does MyList support for-comprehensions?
  2. A small collection of at most ONE element - Maybe[+T]
    -map, flatMap, filter
   */
}
