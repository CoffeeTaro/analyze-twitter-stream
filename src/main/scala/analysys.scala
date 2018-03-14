import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token

object Main extends App{
  // Sparkの初期設定
  val conf = new SparkConf().setMaster("local").setAppName("geoTweetStream")
  val sc = new SparkContext(sc)
  val batchDuration = Seconds(3)
  val ssc = new StreamingContext(sc, batchDuration)
  val stream = TwitterUtils.createStream(ssc, None)

  // 形態素解析
  val tokenizer = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build
  val tokens = tokenizer.tokenize("ドはドーナッツのド").toArray
  tokens.foreach { t =>
    val token = t.asInstanceOf[Token]
    println(s"${token.getSurfaceForm} - ${token.getAllFeatures}")
  }
}
