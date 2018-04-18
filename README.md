# Point Compression and Decompression

[![Build Status](https://travis-ci.org/vitalibo/point-compression.svg?branch=master)](https://travis-ci.org/vitalibo/point-compression)

Java tool for compress and decompress list of points by Bing [Point Compression Algorithm](https://msdn.microsoft.com/en-us/library/jj158958.aspx).

## Overview

When the number of points (latitude and longitude pairs) becomes too large, the length of the URL request may exceed limits imposed by clients, proxies, or the server. 
To reduce the size of the request or when you cannot use the HTTP POST method, you can implement the compression algorithm described below to get a compressed string that you can use instead of the lengthy points list. 

This algorithm is best for 400 points or less. The following example shows the difference in size between a list of points and the equivalent compressed string.

**Original Values**:<br>
`points=35.894309002906084,-110.72522000409663,35.893930979073048,-110.72577999904752,35.893744984641671,-110.72606003843248,35.893366960808635,-110.72661500424147`

**Equivalent Compressed Value**:<br>
`points=vx1vilihnM6hR7mEl2Q`

## Usage

In order to use, simply add these lines to your project's `pom.xml`

```xml
<dependency>
    <groupId>com.meteogroup</groupId>
    <artifactId>point-compression</artifactId>
    <version>1.2</version>
</dependency>
```

The following snippets shows usage of compressing and decompressing coordinates.
Example of point compressions, you can find below.

```java
String locations = PointCompression.compress(
    Arrays.asList(
        new Point(35.894309002906084, -110.72522000409663),
        new Point(35.893930979073048, -110.72577999904752),
        new Point(35.893744984641671, -110.72606003843248),
        new Point(35.893366960808635, -110.72661500424147)));
```

As output you receives string `locations` with compressed points. The next example show, how to decompress compressed previously points.

```java
List<Point> points = PointCompression.decompress(locations);
```

The output list of points looks like: 

```text
Point(latitude=35.89431, longitude=-110.72522)
Point(latitude=35.89393, longitude=-110.72578)
Point(latitude=35.89374, longitude=-110.72606)
Point(latitude=35.89337, longitude=-110.72662)
```

You can see that all coordinates is rounded to five decimal please, take into consideration it.