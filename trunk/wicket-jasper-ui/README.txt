Welcome to wicket-jasper project
	- wicket-jasper-bridge - this project acts a remote project calling the wicket-jasper-web a main report provider.
	  The report is displayed in <iframe> tag. Anyway, the latest version of that project is not yet incorporated.

	- wicket-jasper-core - a core components for wicket-jasper and is based on project wicket-contrib-jasperreports.
			       I added some components like paging and etc...

	- wicket-jasper-web - the example page for wicket-jasper-core.

Installation
===========
1. inside [additional] folder are some files needed for this project:
	- customer_sales_20090321_1815.sql - a sql data-dump used in this example
	- barcode4j-jasper -  a baarcode tool for jasper
	- and the .jrxml file (just in case you are running problem in .jasper file bcoz i compile it in jdk 6)
	  you can compile it in your jdk installed
2. In relation to compiling the .jrxml files you need to put first the barcode4j-jasper jar into lib of iReport before compiling
 	the jrxml files. And also dont forget install it (bcoz it is not located elsewhere) in your maven repository.

2. dump first the sql file in MySQL
3. the run mvn eclipse:eclipse
4. mount it in eclipse
5. Start running the application
 
Question: please email me at eman.nollase@gmail.com


