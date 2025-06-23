package functional

object TuplesAndMaps extends App {

  // tuples = finite ordered "list"
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "good bye"))
  println(aTuple.swap) // ("hello, Scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is sugar for (a,b)
  println(phonebook)

  // maps ops
  println(phonebook.contains("Jim"))
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  //filterKeys
  println(phonebook.view.filterKeys(element => element.startsWith("J")))
  //mapValues
  println(phonebook.view.mapValues(number => number * 10))
  println(phonebook.view.mapValues(number => s"0245-$number"))

  //conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel",555)).toMap)

  val names = List("Bob","James","Angela","Mary", "Daniel","Jim")
  println(names.groupBy(name => name.charAt(0)))

}
