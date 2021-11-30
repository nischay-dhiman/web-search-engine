# Web Search Engine

A search engine is an online tool that is designed to search for websites on the internet based on the user’s search query.

Popular search engines include Google, Bing and Yahoo.

This project is a very basic search engine that allows users to find the information they are looking for by using keywords or phrases.


# Crawling and Html to Text Processing


```
Run crawler/Crawling.java

```

Homepage: https://windsorstar.com

Depth-first search algorithm

Checking whether a page is visited
HashTable data structure

jsoup (Java HTML Parser)




# Inverting Indexes

```
Run websites_data/UrlWordsData_InvertedIndex.java

```


Mapping from each word in content, to the  document or a set of documents that contain the word.



# User Search & AutoCorrection

```
Run search_engine/InitiateSearch.java

```


When ever a user enters a word in search input, 

We find it in our reversed indexed dictionary.

This dictionary was created in the preprocessing step.

We fetch the list of websites stored for this particular word.

And Finally, we will list out the top websites from that list in our results page.

Now lets begin to the final User Search Part






