package oobasics

object CaseClasses extends App {
  case class Person(name: String, age: Int)

  //1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  //2. sensible toString
  // println(instance) = println(instance.toString) //Syntactic sugar
  println(jim)

  //3. equals and hashcode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  //4. Case classes have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  println(jim3 == jim)

  //5. Case classes have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  //6. Case classes are serializable
  //AKka

  //7. Case classes have extractor patterns - Case classes can be used in PATTERN MATCHING
  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }

  //Expand MyList - use case classes and case objects
}
