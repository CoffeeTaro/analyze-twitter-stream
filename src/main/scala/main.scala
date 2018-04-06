import org.apache.spark._
import org.apache.spark.streaming._
import org.atilika.kuromoji.Tokenizer
import org.atilika.kuromoji.Token

object Main {
  // Twitter Keysの読み込み
  def main(args: Array[String]): Unit = {
     // Sparkの初期設定
     val conf = new SparkConf().setMaster("local[4]").setAppName("geoTweetStream")
     val ssc = new StreamingContext(conf, Seconds(3))

     sys.ShutdownHookThread {
       System.out.println("Gracefully stopping SparkStreaming Application")
       ssc.stop(true, true)
       System.out.println("SparkStreaming Application stopped")
     }

     // Twitterのストリーム生成
     val twitterStream = ssc.receiverStream(new TwitterReceiver("ja"))
     val twitterStreamText = twitterStream.map(status => status.getText())
     twitterStreamText.print()

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
