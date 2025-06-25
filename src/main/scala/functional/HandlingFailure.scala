package functional

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {

  /*
  * Use Option when null are handling
  * Use Try, Success Failure when Exception is handling
  * */
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(element => Success(element * 10)))
  println(aSuccess.filter(_ > 10))
  // => for-comprehensions

  /*
  Exercise
   */
  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
  }

  object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection =
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")

    def getSafeConnection(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
  }

  // if you get the html page from connection, print it to the console i.e. call renderHTML

  println("===========first version=============")
  Try(HttpService.getConnection(hostname, port))
    .flatMap(connection => Try(renderHTML(connection.get("/home"))))

  println("===========second version=============")
  Try(HttpService.getConnection(hostname, port))
    .flatMap(connection => Try(connection.get("/home"))).foreach(renderHTML)

  println("===========daniel's version=============")
  val possibleConnection = HttpService.getSafeConnection(hostname, port)
  val possibleHtml = possibleConnection.flatMap(connection => connection.getSafe("/home"))
  possibleHtml.foreach(renderHTML)

  println("===========for-comprehension version=============")
  for {
    connection <- HttpService.getSafeConnection(hostname, port)
    html <- connection.getSafe("/home")
  } yield renderHTML(html)

  /*
  try{
    val connection = HttpService.getConnection(hostname, port)
    try{
      val page = connection.get("/home")
      renderHTML(page)
    } catch(some other exception){

      }
  }catch(Exception)
   */
}
