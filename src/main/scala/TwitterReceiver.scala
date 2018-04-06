import twitter4j._

import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.receiver.Receiver

class TwitterReceiver(language: String = "ja")
  extends Receiver[Status](StorageLevel.MEMORY_AND_DISK_2){

  private def statusListener = new StatusListener() {
    override def onStatus(status: Status): Unit = {
      store(status)
    }
    override def onDeletionNotice(notice: StatusDeletionNotice): Unit = {}
    override def onScrubGeo(x1: Long, x2: Long): Unit = {}
    override def onStallWarning(stw: StallWarning): Unit = {}
    override def onTrackLimitationNotice(x: Int): Unit = {}
    override def onException(e: Exception): Unit = {
      e.printStackTrace
    }
  }

  def onStart(): Unit = {
        receive()
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
    while(!isStopped) {

    }
    // 終了処理
    twitterStream.cleanUp
    twitterStream.shutdown
  }
}

