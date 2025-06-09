package oobasics

object Objects extends App {

  //It is called companion
  object Person { //type + it is only instance
    //similar as "static class"
    private val NUMBER_OF_EYES = 2

    def canFly: Boolean = false

    def apply(mother: Person): Person = new Person(mother.name)
  }

  class Person(val name: String) {
    //instance level functionality
    def getNumberOfEyes: Int = Person.NUMBER_OF_EYES
  }

  println(Person.canFly)

  private val mary = new Person("Mary")
  private val john = new Person("John")

  println(s"Mary and John are equals ? : ${mary == john}")

  //Scala objects == a singleton instance
  private val personOne = Person
  private val personTwo = Person

  println(s"PersonOne and PersonTwo are equals ? : ${personOne == personTwo}")

  private val bobbie = Person(mary)
  println(bobbie.name)


}
