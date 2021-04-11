## Camel Processr from csv to xml

**Introduction**

This project is a bunch of reference points that I put together while working through Camel in Action. 

##Main Unit tests

The base running logic is in unit tests.

**CSVSplitterToSeparateCsvFiles**



**CSVSplitterToXML**

Iteration for the csv file and setting it into pojo;
afterwards iterating the list of invoice pojo & saving into separated xml files by buyer

**Bonus tests with Apache camel (not required, but it took me time to overthink the requirement)**

**ConvertorRouteTest**

A classic camel route logic with Domain Specific Language, it converts the whole csv into xml file.

**CsvToXmlTransformerTest**

Testing first row of data custom CsvToXmlTransformer with xStream convert
The invoice img is separated in file & compressed byte[]; all fields are asserted

**CsvToPersonTransformerTest**

Conversion of the first row of csv data and converting into pojo