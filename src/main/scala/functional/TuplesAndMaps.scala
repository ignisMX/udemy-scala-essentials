package functional

import scala.annotation.tailrec

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
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))

  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
    R = It is valid because maps are case-sensitive but careful with mapping keys
    2.- Overly simplified social network based on maps
      Person - String
      - add a person to the network
      - remove
      - friend (mutual)
      - unfriend

      - number of friends of a person
      - person with most friends
      - how many people have NO friends
      - if there is a social connection between two people (direct or not)

   */
  val persons = Map(("Jim" -> 555), ("JIM" -> 900))
  println(persons)

  def add(person: String, network: Map[String, Set[String]]): Map[String, Set[String]] = network + (person -> Set())

  def friend(personOne: String, personTwo: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
    val friendsPersonOne = if (network.contains(personOne)) network(personOne) else Set()
    val friendsPersonTwo = if (network.contains(personTwo)) network(personTwo) else Set()

    network + (personOne -> (friendsPersonOne + personTwo)) + (personTwo -> (friendsPersonTwo + personOne))
  }

  def unfriend(personOne: String, personTwo: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
    val friendsPersonOne = if (network.contains(personOne)) network(personOne) else Set()
    val friendsPersonTwo = if (network.contains(personTwo)) network(personTwo) else Set()
    val filteredFriendsOne = friendsPersonOne.filterNot(_ == personTwo)
    val filteredFriendsTwo = friendsPersonTwo.filterNot(_ == personOne)

    network + (personOne -> filteredFriendsOne) + (personTwo -> filteredFriendsTwo)
  }

  def remove(person: String, network: Map[String, Set[String]]): Map[String, Set[String]] = {
    def removeFriends(friends: Set[String], networkAccumulator: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAccumulator
      else removeFriends(friends.tail, unfriend(person, friends.head, networkAccumulator))
    }

    val unfriendedNetwork = removeFriends(network(person), network)
    unfriendedNetwork - person
  }

  val emptyNetwork: Map[String, Set[String]] = Map()
  val network = add("Mary", emptyNetwork)
  println(network)

  println(friend("Bob", "Mary", network))
  println(unfriend("Bob", "Mary", friend("Bob", "Mary", network)))
  println(remove("Bob", friend("Bob", "Mary", network)))

  //Jim,Bob,Mary
  val people = add("Jim", add("Mary", add("Bob", emptyNetwork)))
  val jimBob = friend("Bob", "Jim", people)
  val testNet = friend("Bob", "Mary", jimBob)

  println(testNet)

  def getNumberOfFriends(person: String, network: Map[String, Set[String]]): Int = {
    if (network.contains(person)) network(person).size else 0
  }

  println(getNumberOfFriends("Bob", testNet))
  println(getNumberOfFriends("Jimy", testNet))

  def getPersonWithMostFriends(network: Map[String, Set[String]]): String = network.maxBy(pair => pair._2.size)._1

  println(getPersonWithMostFriends(testNet))

  def getNumberOfPersonsWithNoFriends(network: Map[String, Set[String]]): Int = network.count(_._2.isEmpty)

  println(getNumberOfPersonsWithNoFriends(testNet))

  def socialConnection(personOne: String, personTwo: String, network: Map[String, Set[String]]) = {

    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(personTwo, Set(), network(personOne) + personOne)
  }

  println(socialConnection("Mary","Jim", testNet))
  println(socialConnection("Mary","Bob", network))
}
