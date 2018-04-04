import twitter4j._

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

class TwitterReceiver(language: String = "ja")
  extends Receiver[Status](StorageLevel.MEMORY_AND_DISK_2){

  private def statusListener = new StatusListener() {
    def onStatus(status: Status): Unit = {
      store(status)
    }
    def onDeletionNotice(notice: StatusDeletionNotice): Unit = {}
    def onScrubGeo(x1: Long, x2: Long): Unit = {}
    def onStallWarning(stw: StallWarning): Unit = {}
    def onTrackLimitationNotice(x: Int): Unit = {}
    def onException(e: Exception): Unit = {
      e.printStackTrace
    }
  }

  def onStart(): Unit = {
    new Thread("Socket Receiver") {
      override def run(): Unit = {
        receive()
      }
    }
  }

  def onStop(): Unit = {
    // pass
  }

  private def receive(): Unit = {
    // Twitter Streaming APIを起動
    val twitterStream = new TwitterStreamFactory().getInstance()
    // Listenerの作成
    twitterStream.addListener(statusListener)
    // 起動
    twitterStream.sample(language)
    while(!isStopped) {}
    // 終了処理
    twitterStream.cleanUp
    twitterStream.shutdown
  }
}
