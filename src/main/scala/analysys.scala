import com.typesafe.config._
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token

object Main extends App{
  // Twitter Keysの読み込み
  val config = ConfigFactory.load()
  val consumerKey = config.getString("consumerKey")
  val consumerSecret = config.getString("consumerSecret")
  val accessToken = config.getString("accessToken")
  val accessTokenSecret = config.getString("accessTokenSecret")

  // Sparkの初期設定
  val conf = new SparkConf().setMaster("local[4]").setAppName("geoTweetStream")
  val ssc = new StreamingContext(conf, Seconds(3))
  val stream = TwitterUtils.createStream(ssc, None)

  // // 形態素解析
  // val tokenizer = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build
  // val tokens = tokenizer.tokenize("ドはドーナッツのド").toArray
  // tokens.foreach { t =>
  //   val token = t.asInstanceOf[Token]
  //   println(s"${token.getSurfaceForm} - ${token.getAllFeatures}")
  // }
}
