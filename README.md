# Intellexer Java SDK

This is a Java wrapper/client for the Intellexer API

## Getting started

Getting started with Intellexer API is simple, all you need to do is to [create an account](http://esapi.intellexer.com/Account/Register) 
and grab your free API key from user dashboard or invitation email.

## Installation

Use of the library requires Java 7 or higher and pre-installed Apache Maven.

To build a .jar file run:
```
mvn package
```
To skip tests:
```
mvn package -DskipTests
```

To install library to your local Maven repository run:
 ```
 mvn install -DapiKey=YOU_API_KEY
 ```
where *YOU_API_KEY* is an API key. It's required to run integration tests. To install library without running tests run:
```
mvn install -DskipTests
```

### Maven users

Add the following dependency to your project's ``pom.xml``:

```xml
<dependency>
  <groupId>by.effectivesoft.intellexer</groupId>
  <artifactId>intellexer-api</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```gradle
repositories {
    mavenLocal()
}

dependencies {
    compile "by.effectivesoft.intellexer:intellexer-api:1.0.0"
}
```

## External dependencies
This client has the following external dependencies:

* Apache HttpClient 4.5.4
* Apache HttpMime 4.5.4
* Fasterxml Jackson 2.8.3

Test scope:
* JUnit 4.12
* Square OkHttp MockWebServer 2.7.5

## Tests
Run the test suite with ``mvn test``.

## Usage
To use the API you need to create an IntellexerClient instance:

```java
IntellexerClient client = new IntellexerClient("YOUR_API_KEY");
```
List of available text processors:
* [Clusterizer](#clusterizer) - Enables to organize, normalize, link, and process documents.
* [Comparator](#comparator) - Evaluates degree of similarity between documents.
* [LanguageRecognizer](#language-recognizer) - Identifies the language and character encoding of input documents.
* [LinguisticProcessor](#linguistic-processor) - Parses input text and extracts multiple kinds of relations.
* [NamedEntityRecognizer](#named-entity-recognizer) -  Identifies and classifies elements in text into predefined categories such as personal names, names of organizations, position/occupation, nationality, geographical location, date, age, duration and names of events
* [NaturalLanguageInterface](#natural-language-interface) - Transforms Natural Language Queries into Boolean queries, expanding them with synonyms and possible ways of combining and paraphrases.
* [Preformator](#preformator) - Extracts plain text and information about the text layout from documents of different formats and identify the structure and topic of input documents.
* [SentimentAnalyzer](#sentiment-analyzer) - Extracts sentiments (positivity/negativity), opinion objects (e.g., product features with associated sentiment phrases) and emotions (liking, anger, disgust, etc.) from unstructured text data.
* [SpellChecker](#spell-checker) - Finds and corrects spelling mistakes.
* [Summarizer](#summarizer) - Creates a short summary retaining the most important parts of the original document.


### Clusterizer

Example how to clusterize URL:
```java
Clusterizer clusterizer = new Clusterizer(client);

ClusterizeURLParams params = new ClusterizeURLParams.Builder()
        .setLoadSentences(true)
        .setFullTextTrees(true)
        .build();
        
ClusterizeResult result = clusterizer.clusterizeURL("https://www.intellexer.com/about_us.html", params);
for (ConceptTree child : result.getConceptTree().getChildren()) {
    System.out.println(child.getText() + " - " + child.getWeight());
}
```

### Comparator

Example how to compare texts:
```java
Comparator comparator = new Comparator(client);

String text1 = "Hello, world";
String text2 = "Morning, beautiful world!";
CompareResult result = comparator.compareTexts(text1, text2);
System.out.println(result.getProximity());
```

### Linguistic Processor
Example how to analyze text:
```java
LinguisticProcessor linguisticProcessor = new LinguisticProcessor(client);

AnalyzeTextParams params = new AnalyzeTextParams.Builder()
        .setLoadSentences(true)
        .setLoadTokens(true)
        .setLoadRelations(true)
        .build();
        
String text = "Intellexer Summarizer has an unique feature";
List<Sentence> sentences = linguisticProcessor.analyzeText(text, params);
List<Token> tokens = sentences.get(0).getTokens();
for (Token token : tokens){
    System.out.println(token.getText().getContent() + " - " + token.getLemma());
}
```

### Language Recognizer

Example how to recognize text's language:
```java
LanguageRecognizer languageRecognizer = new LanguageRecognizer(client);

String text = "The good thing about this way of building objects of the class is that it works.";
List<Language> languageList = languageRecognizer.recognizeLanguage(text);
for (Language language : languageList) {
    System.out.println(language.getLanguageName() + " - " + language.getEncoding());
}
```

### Named Entity Recognizer

Example how to identify and classify elements in text:

```java 
NamedEntityRecognizer recognizer = new NamedEntityRecognizer(client);
String text = "Walter Elias Disney was an American entrepreneur, animator, voice actor and film producer";

RecognizeParams params = new RecognizeParams.Builder()
        .setLoadSentences(true)
        .setLoadNamedEntities(true)
        .setLoadRelationsTree(true)
        .build();

NamedEntityRecognizerResult result = recognizer.recognizeFromText(text, params);
List<NamedEntity> entities = result.getEntities();
for (NamedEntity entity : entities) {
    System.out.println(entity.getText() + " - " + entity.getType().getName());
}
```

### Natural Language Interface

Example how to transform natural language query into boolean query:

```java
NaturalLanguageInterface languageInterface = new NaturalLanguageInterface(client);

String text = "That is a solution that automatically extracts sentiments";
String result = languageInterface.convertQueryToBool(text);
System.out.println(result);
```

### Preformator

Example how to extract plain text from URL and information about it:

```java
Preformator preformator = new Preformator(client);

String url = "https://www.intellexer.com/about_us.html";
ParseResult result = preformator.parse(url, true);
List<String> topics = result.getTopics();
System.out.println(topics);
System.out.println(result.getLanguage());
```

### Sentiment Analyzer

Example how to extracts sentiments, opinion objects and emotions from unstructured text data:
```java
SentimentAnalyzer analyzer = new SentimentAnalyzer(client);

AnalyzeSentimentsParams params = new AnalyzeSentimentsParams.Builder()
        .setLoadSentences(true)
        .build();
        
List<Review> reviews = Arrays.asList(
        new Review("1", "Hello, world! It's been a great day."),
        new Review("2", "Intellexer Summarizer has an unique feature.")
);
AnalyzeSentimentsResult result = analyzer.analyzeSentiments(reviews, params);
for (SentimentSentence sentence : result.getSentences()){
    System.out.println(sentence.getText() + " - " + sentence.getWeight());
    System.out.println(sentence.getWords(SentimentWordType.POSITIVE));
    System.out.println(sentence.getWords(SentimentWordType.NEGATIVE));
}
```

### Spell Checker

Example how to perform search and correction of spelling mistakes:

```java
SpellChecker spellChecker = new SpellChecker(client);

SpellCheckerParams params = new SpellCheckerParams.Builder()
        .setMinProbabilityWeight(5)
        .setErrorBound(3)
        .build();

String text = "Helllo, worlt. It's benn a great day";
SpellCheckResult result = spellChecker.checkTextSpelling(text, params);
for (String sentence : result.getProcessedSentences()) {
    System.out.println(sentence);
}
```

### Summarizer

Example how to generate a summary of a document with its main ideas:

```java
Summarizer summarizer = new Summarizer(client);

SummarizeURLParams params = new SummarizeURLParams.Builder()
        .setUseCache(true)
        .setFullTextTrees(true)
        .setSummaryRestriction(5)
        .build();

String url = "https://www.intellexer.com/about_us.html";
SummarizeResult result = summarizer.summarizeURL(url, params);
System.out.println(result.summary());
```

