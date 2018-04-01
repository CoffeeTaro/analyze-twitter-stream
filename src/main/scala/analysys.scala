import com.typesafe.config._
import org.apache.spark._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token

object Main {
  // Twitter Keysの読み込み
  def main(args: Array[String]): Unit = {
     val config = ConfigFactory.load()
     val consumerKey = config.getString("consumerKey")
     val consumerSecret = config.getString("consumerSecret")
     val accessToken = config.getString("accessToken")
     val accessTokenSecret = config.getString("accessTokenSecret")
     // Twitter Keysの設定
     System.setProperty("twitter4j.oauth.consumerKey", consumerKey)
     System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret)
     System.setProperty("twitter4j.oauth.accessToken", accessToken)
     System.setProperty("twitter4j.oauth.accessTokenSecret",accessTokenSecret)

     // Sparkの初期設定
     val conf = new SparkConf().setMaster("local[4]").setAppName("geoTweetStream")
     val ssc = new StreamingContext(conf, Seconds(3))

     sys.ShutdownHookThread {
       System.out.println("Gracefully stopping SparkStreaming Application")
       ssc.stop(true, true)
       System.out.println("SparkStreaming Application stopped")
     }

     // Twitterのストリーム生成
     val stream = TwitterUtils.createStream(ssc, None)

     val twitterStream = stream.map(status => status.getText())
     twitterStream.print()

     ssc.start()
     ssc.awaitTermination()

     // 形態素解析
     val tokenizer = Tokenizer.builder.mode(Tokenizer.Mode.NORMAL).build
     val tokens = tokenizer.tokenize("ドはドーナッツのド").toArray
     tokens.foreach { t =>
       val token = t.asInstanceOf[Token]
       println(s"${token.getSurfaceForm} - ${token.getAllFeatures}")
     }
  }
}
