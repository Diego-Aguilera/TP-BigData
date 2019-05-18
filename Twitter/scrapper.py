import tweepy
import pandas as pd
import csv


# consumer keys and access tokens, used for OAuth
consumer_key = 'LWJn0SgYIGgoBkCiLrGcAjWxL'
consumer_secret = 'kGKAfcT0vBpi5UVrFAnRqphKtZeuBRpkU3KQTWsfbw4SZZtadn'
access_token = '1128512621571923972-izWWGlZmO6fRwQKnt4tuh74NBvAWam'
access_token_secret = 'Os9scBmbdqRD3ie62k6RVG5SNuNTlKf8Be8k4hlz2aAtk'

# OAuth process, using the keys and tokens
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

# creation of the actual interface, using authentication
api = tweepy.API(auth, wait_on_rate_limit=True)

data = []

query = "#Tesla";

for tweet in tweepy.Cursor(api.search, q=query, count=100,
                           lang="en",
                           since="2019-01-01").items():
    item = [query, tweet.text.replace("\r", "").replace("\n", ""), tweet.user.name, tweet.created_at,  tweet.retweet_count, tweet.favorite_count]
    print(tweet.text.replace("\r", "").replace("\n", ""), tweet.user.name, tweet.created_at,  tweet.retweet_count, tweet.favorite_count)
    data.append(item)

df = pd.DataFrame(data, columns=['query', 'text', 'author', 'timestamp', 'rts', 'favs'])
df.to_csv('tesla.csv', quoting=csv.QUOTE_NONNUMERIC, index=False)
