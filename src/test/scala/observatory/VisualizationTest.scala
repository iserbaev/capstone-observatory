package observatory


import observatory.Extraction._
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

trait VisualizationTest extends FunSuite with Checkers with TimeCheck {
  import SparkHelper._
  import Visualization._
  import math._

  private val stationsPath = "/stations.csv"
  private val tempPath = "/1975.csv"
  private val year = 1975

  private lazy val records = locateTemperatures(year, stationsPath, tempPath)
  private lazy val temperatures = locationYearlyAverageRecords(records)

  val location = Location(37,119)
  val locationAntiPod = Location(-37, -61)

  val location2 = Location(22.55, 23.783)

  test("distance") {
    assert(location == location.copy(), "location equal to self should be true")
    assert(location != location2, "location should not be equal to other")

    println(location)
    println(getAntipode(location))
    assert(getAntipode(location) == locationAntiPod, "func getAntipod should work correctly")

    assert(distance(location, location) == 0.0)
    assert(distance(location, locationAntiPod).toInt == 20015)
    assert(
      distance(location, locationAntiPod.copy(lat = - 38)).toInt == distance(location, locationAntiPod.copy(lat = -36)).toInt
    )
  }

  test("predict temperature should work correctly and fast") {
  }

}
