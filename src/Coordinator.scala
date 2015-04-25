import akka.actor._
import scala.concurrent.duration.Duration
import scala.concurrent.duration._

//Messages
case class SetPixel(x:Int, y:Int, colour:Colour)

// TODO
//
// Make this an actor and write a message handler for at least the
// set method.
//
object Coordinator extends Actor{
  def init(im: Image, of: String) = {
    image = im
    outfile = of
    waiting = im.width * im.height
  }
  def receive = {
    case SetPixel(x, y, colour) => set(x, y, colour)
 }
  
  // Number of pixels we're waiting for to be set.
  var waiting = 0
  var outfile: String = null
  var image: Image = null

  // TODO: make set a message
  def set(x: Int, y: Int, c: Colour) = {
    image(x, y) = c
    waiting -= 1
    if (waiting == 0) print
  }

  def print = {
    assert(waiting == 0)
    image.print(outfile)
  }
}