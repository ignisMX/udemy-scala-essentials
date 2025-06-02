package basics

object DefaultArguments extends App {

  private def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1800): Unit =
    println(s"save picture. format = $format, width: $width, height: $height")

  savePicture()
  savePicture(height = 600, width = 900, format = "png")

}
