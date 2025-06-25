package functional

import scala.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs
  def unsafeMethod(): String = null

  // val result = Some(null) // WRONG. Never do this
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained methods
  def backupMethod(): String = "A valid result"

  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  // DESIGN unsafe APIs
  def betterUnsafeMethod(): Option[String] = None

  def betterBackupMethod(): Option[String] = Some("A valid result")

  val betterChainedResult = betterBackupMethod() orElse betterBackupMethod()

  // functions on Options
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(noOption.map(_ * 2))
  println(myFirstOption.filter(element => element > 10))
  println(myFirstOption.flatMap(element => Option(element * 10)))

  // for-comprehensions
  /*
  Exercise
   */
  val config: Map[String, String] = Map(
    "host" -> "128.161.1.10",
    "port" -> "90"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")

  /*
    if(host != null)
      if(port != null)
        return Connection.apply(host,port)
    return null
   */
  val connection = host.flatMap(host => port.flatMap(port => Connection(host, port)))
  /*
    if(connection != null)
      return connection.connect
    return null
   */
  val connectionStatus = connection.map(conn => conn.connect)
  // if(connectionStatus == null) println(None) else println(Some(connectionStatus.get))
  println(connectionStatus)
  /*
    if(status != null)
      println(status)
   */
  connectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect

}
