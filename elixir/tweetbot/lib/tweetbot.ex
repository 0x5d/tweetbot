defmodule Tweetbot do
  import IO

  def main (args) do
    configure ()
    tweet (hd (args)
  end

  def tweet (message) do

  end

  def configure () do
    ExTwitter.configure ()
  end
end
