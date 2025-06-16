package oobasics

// playground._

import playground.{Cinderella => Princess, PrinceCharming}
import java.util.Date
import java.sql.Date => SqlDate

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val princess = new Princess // playground.Cinderella - fully qualified name

  // package are in hierarchy
  // matching folder structures

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use fully qualified names
  val date = new Date
  val sqlDate = new SqlDate(2018, 5, 4)

  // 2. use aliasing
  // java.lang - String, Object, Exception
  // Scala - Int, Nothing, Function
  // scala.Predef - println, ???

}
