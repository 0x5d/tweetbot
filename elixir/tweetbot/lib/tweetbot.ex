defmodule Tweetbot do
  def main (args) do
    if (length args) > 0 and (hd args) != "" do
      configure ()
      tweet (hd args)
    else
      IO.puts "Something went wrong"
    end
  end

  def tweet (message) do
    ExTwitter.update (message)
  end

  def configure () do
    path = System.user_home! |> Path.join("tweetbot-config.yml")
    config = YamlElixir.read_from_file path
    ExTwitter.configure ([consumer_key: config["consumer-key"],
                          consumer_secret: config["consumer-secret"],
                          access_token: config["access-token"],
                          access_token_secret: config["access-token-secret"]])
  end
end
