# tx-java
![GitHub](https://img.shields.io/github/license/textkernel/tx-java?color=0575aa)
![Maven Central](https://img.shields.io/maven-central/v/com.textkernel/tx-java?color=0575aa)
[![build](https://github.com/textkernel/tx-java/actions/workflows/build.yml/badge.svg)](https://github.com/textkernel/tx-java/actions/workflows/build.yml)

The official Java SDK for the Textkernel Tx v10 API for resume/CV and job parsing, searching, and matching. Supports Java 1.8+.

## Installation

### Requirements
- Java 1.8 or later

### Gradle Users
Add this dependency to your project's build file:
```
implementation "com.textkernel:tx-java:2.1.0"
```

### Maven Users
Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.textkernel</groupId>
  <artifactId>tx-java</artifactId>
  <version>2.1.0</version>
</dependency>
```

### Others
You'll need to manually install the following JARs:
- The Textkernel Tx JAR from https://repo1.maven.org/maven2/com/textkernel/tx-java/2.1.0/tx-java-2.1.0.jar
- [Google Gson][gson_url] from https://repo1.maven.org/maven2/com/google/code/gson/gson/2.9.0/gson-2.9.0.jar
- [Square OkHttp][okhttp_url] from https://repo1.maven.org/maven2/com/squareup/okhttp3/okhttp/4.9.3/okhttp-4.9.3.jar


## Documentation
For the full API documentation, information about best practices, FAQs, etc. check out our [docs site][api-docs].

You can also refer to the [online Javadoc][javadoc_url]

## Examples
For full code examples, see [here][examples].

## Usage

### Creating a `TxClient`
This is the object that you will use to perform API calls. You create it with your account credentials and the `TxClient` makes the raw API calls for you. These credentials can be found in the [Tx Console][portal]. Be sure to select the correct `DataCenter` for your account.
```java
TxClient client = new TxClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
```

For self-hosted customers, you can create a `DataCenter` object with your custom URL using the constructor provided on that class.

### Handling errors and the `TxException`
Every call to any of the methods in the `TxClient` should be wrapped in a `try/catch` block. Any 4xx/5xx level errors will cause a `TxException` to be thrown. Sometimes these are a normal and expected part of the Tx API. For example, if you have a website where users upload resumes, sometimes a user will upload a scanned image as their resume. Textkernel does not process these, and will return a `422 Unprocessable Entity` response which will throw a `TxException`. You should handle any `TxException` in a way that makes sense in your application.

Additionally, there are `TxUsableResumeException` and `TxUsableJobException` which are thrown when some error/issue occurs in the API, but the response still contains a usable resume/job. For example, if you are geocoding while parsing and there is a geocoding error (which happens after parsing is done), the `ParsedResume` might still be usable in your application.

### How to create a Matching UI session
You may be wondering, "where are the Matching UI endpoints/methods?". We have made the difference between a normal API call (such as `Search`) and its equivalent Matching UI call extremely trivial. See the following example: 

```java
TxClient client = new TxClient("12345678", "abcdefghijklmnopqrstuvwxyz", DataCenter.US);
List<String> indexesToSearch = ...;
FilterCriteria searchQuery = ...;

SearchResponse searchResponse = client.search(indexesToSearch, searchQuery, null, null);
```
To generate a Matching UI session with the above Search query, you simply need to call the `ui(...)` method on the `TxClient` object, pass in any UI settings, and then make the same call as above:
```java
MatchUISettings uiSettings = ...;
GenerateUIResponse uiResponse = client.ui(uiSettings).search(indexesToSearch, searchQuery, null, null);
```
For every relevant method in the `TxClient`, you can create a Matching UI session for that query by doing the same as above.

[javadoc_url]: https://textkernel.github.io/tx-java/
[gson_url]: https://github.com/google/gson
[okhttp_url]: https://github.com/square/okhttp
[examples]: https://github.com/textkernel/tx-java/tree/master/examples
[portal]: https://cloud.textkernel.com/tx/console
[api-docs]: https://developer.textkernel.com/tx-platform/v10/overview/