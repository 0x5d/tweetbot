defmodule Tweetbot do
  import IO

  def main (args) do
    if (length args) > 0 and (hd args) != "" do
      tweet (hd (args))
    else
      puts "Something went wrong"
    end
  end

  def tweet (message) do
    ExTwitter.update (message)
  end
end
