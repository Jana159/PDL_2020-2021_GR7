**_Extractor success rate_**
We used the BenchTest.java test with the list of 316 urls presented in largeUrl.test.
We got 1710 csv files for HTML tables and 36 for wikitext tables.
not being able to manually verify all these files, 
we decided according to the pareto law to process 20% of these data.

**study of the existing**
- First of all we notice that the client does not have the right to choose the page from which he wants to extract the tables.
- When we extract the URl tables from the large_url_test.txt file, we have a very lower number of comparison in wikitext compared to those in HTML, i.e. less than 20% of the comparisons.
- The tests which check the validity of the Urls are ignored so the Urls are used without being tested.
