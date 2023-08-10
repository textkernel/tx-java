# sovren-java
![GitHub](https://img.shields.io/github/license/sovren/sovren-java?color=0575aa)
![Maven Central](https://img.shields.io/maven-central/v/com.sovren/sovren-java?color=0575aa)
[![build](https://github.com/sovren/sovren-java/actions/workflows/build.yml/badge.svg)](https://github.com/sovren/sovren-java/actions/workflows/build.yml)

The official Java SDK for the Sovren v10 API for resume/CV and job parsing, searching, and matching. Supports Java 1.8+.

## Installation

### Requirements
- Java 1.8 or later

### Gradle Users
Add this dependency to your project's build file:
```
implementation "com.sovren:sovren-java:1.9.1"
```

### Maven Users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.sovren</groupId>
  <artifactId>sovren-java</artifactId>
  <version>1.9.1</version>
</dependency>
```

### Others
You'll need to manually install the following JARs:
- The Sovren JAR from https://repo1.maven.org/maven2/com/sovren/sovren-java/1.9.1/sovren-java-1.9.1.jar
- [Google Gson][gson_url] from https://repo1.maven.org/maven2/com/google/code/gson/gson/2.9.0/gson-2.9.0.jar
- [Square OkHttp][okhttp_url] from https://repo1.maven.org/maven2/com/squareup/okhttp3/okhttp/4.9.3/okhttp-4.9.3.jar


## Documentation
For the full API documentation, information about best practices, FAQs, etc. check out our [docs site][api-docs].

You can also refer to the [online Javadoc][javadoc_url]

## Examples
For full code examples, see [here][examples].

## Usage

### Creating a `SovrenClient`
This is the object that you will use to perform API calls. You create it with your account credentials and the `SovrenClient` makes the raw API calls for you. These credentials can be found in the [Sovren Portal][portal]. Be sure to select the correct `DataCenter` for your account.
```java
SovrenClient client = new SovrenClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
```

For self-hosted customers, you can create a `DataCenter` object with your custom URL using the constructor provided on that class.

### Handling errors and the `SovrenException`
Every call to any of the methods in the `SovrenClient` should be wrapped in a `try/catch` block. Any 4xx/5xx level errors will cause a `SovrenException` to be thrown. Sometimes these are a normal and expected part of the Sovren API. For example, if you have a website where users upload resumes, sometimes a user will upload a scanned image as their resume. Sovren does not process these, and will return a `422 Unprocessable Entity` response which will throw a `SovrenException`. You should handle any `SovrenException` in a way that makes sense in your application.

Additionally, there are `SovrenUsableResumeException` and `SovrenUsableJobException` which are thrown when some error/issue occurs in the API, but the response still contains a usable resume/job. For example, if you are geocoding while parsing and there is a geocoding error (which happens after parsing is done), the `ParsedResume` might still be usable in your application.

### How to create a Matching UI session
You may be wondering, "where are the Matching UI endpoints/methods?". We have made the difference between a normal API call (such as `Search`) and its equivalent Matching UI call extremely trivial. See the following example: 

```java
SovrenClient client = new SovrenClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
List<String> indexesToSearch = ...;
FilterCriteria searchQuery = ...;

SearchResponse searchResponse = client.search(indexesToSearch, searchQuery, null, null);
```
To generate a Matching UI session with the above Search query, you simply need to call the `ui(...)` method on the `SovrenClient` object, pass in any UI settings, and then make the same call as above:
```java
MatchUISettings uiSettings = ...;
GenerateUIResponse uiResponse = client.ui(uiSettings).search(indexesToSearch, searchQuery, null, null);
```
For every relevant method in the `SovrenClient`, you can create a Matching UI session for that query by doing the same as above.

[javadoc_url]: https://sovren.github.io/sovren-java/
[gson_url]: https://github.com/google/gson
[okhttp_url]: https://github.com/square/okhttp
[examples]: https://github.com/sovren/sovren-java/tree/master/examples
[portal]: https://portal.sovren.com
[api-docs]: https://sovren.com/technical-specs/latest/rest-api/overview/