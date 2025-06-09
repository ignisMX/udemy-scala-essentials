package oobasics

object AbstractDataTypes extends App {
  abstract class Animal {
    val creatureType: String = "Wild"

    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"

    def eat: Unit = println("crunch crunch")
  }

  //traits
  trait Carnivore {
    val preferedMeal: String = "Fresh meat"

    def eat(animal: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override val creatureType: String = "Croc"

    def eat: Unit = println("ñamñam")

    def eat(animal: Animal): Unit = println(s"I am a Croc and I am eating ${animal.creatureType}")
  }

  private val dog = new Dog
  private val crocodile = new Crocodile
  crocodile eat dog

  //abstract classes vs traits
  //1. In scala v2 traits does not support constructors, in version 3 it does
  //2. Multiple traits can be used in same class
  //3. Traits represents behavior and abstract class things
}
