package oobasics

object Inheritance extends App {

  //Any class on the same class can extends for Animal but classes outside of this file
  //can't extends from Animal
  sealed class Animal {

    val creatureType = "Wild"

    def eat: Unit = println("animal makes: ñamñam")
  }

  class Cat extends Animal {
    def crunch = {
      super.eat
      println("cat makes: crunch, crunch")
    }
  }

  private val cat = new Cat
  cat.crunch

  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  //Constructors
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  //Overriding
  class Dog(override val creatureType: String) extends Animal{
    // override val creatureType = "domestic"
    override def eat = println("Dog make: kof kof")
  }

  private val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  private val unknownAnimal = new Dog("K9")
  unknownAnimal.eat

  // Preventing overrides
  // 1. final keyword on class members
  // 2. final keyword on class
  // 3. seal the class

}
