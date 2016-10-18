package co.tweetbot

import io.Source
import java.io.File

import co.tweetbot.config.TwitterCredentials
import com.danielasfregola.twitter4s.TwitterRestClient
import com.danielasfregola.twitter4s.entities.{AccessToken, ConsumerToken}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Main extends App {

  override def main(args: Array[String]): Unit = {
    val filename = ".tweetbot"
    val configFilePath = sys.env.getOrElse("HOME", ".") + File.separator + filename

    val credentials = parseCredentialsFile(configFilePath)

    tweet(args(0), credentials)
  }

  def parseCredentialsFile(filepath: String): TwitterCredentials = {
    val lines = Source.fromFile(filepath).mkString
    val mapper = new ObjectMapper(new YAMLFactory())
    mapper.registerModule(DefaultScalaModule)
    mapper.readValue(lines, classOf[TwitterCredentials])
  }

  def tweet(status: String, credentials: TwitterCredentials): Unit = {
    val consumerToken = ConsumerToken(credentials.consumerKey, credentials.consumerSecret)
    val accessToken = AccessToken(credentials.accessTokenKey, credentials.accessTokenSecret)
    val restClient = new TwitterRestClient(consumerToken, accessToken)
    restClient.tweet(status)
  }
}
