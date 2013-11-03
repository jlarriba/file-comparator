file-comparator
===============

The File Comparator is a Java console app that binary compare all the files in two directories by obtaining and comparing their hashes.

Usage: java -jar file-comparator.jar <directory1> <directory2> > comparation.html

It writes html file that then can be checked with any browser.

It uses java.nio libraries present in JDK7 and the Tree traverser class, so the comparison is blazing fast.

I used this application to teach myself the nio API.
