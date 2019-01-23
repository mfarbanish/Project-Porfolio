Mahlon Farbanish
I do not believe I have any problems in my code.
From your code I am using the HTMLScanner, the StringComparator (to test my heap), and the classes that you gave starter code with.
To try and improve my search engine, I made the search ignore cases, use AND operator, and based the score off of frequency of word usage, not just word count. To ignore cases, I just set everything to lower before comparing anything. For the AND operator I just made sure that at least one of the words in the query was in the current WebPageIndex.contains that was removed from the heap, if it was I printed it once, other wise I didn?t print it. For frequency, in the URLcomparator I just did Math.round(idx.frequency * 100) to calculate score. These all improved my results significantly. These were also all manageable implimentations.

I adhered to the honor code in this assignment. 
