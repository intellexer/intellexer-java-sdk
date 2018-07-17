package by.effectivesoft.intellexer.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DocumentStructure {
    AUTODETECT("Autodetect"),
    FICTION_BOOK("FictionBook"),
    NEWS_ARTICLE("NewsArticle"),
    RESEARCH_PAPER("ResearchPaper"),
    PATENT("Patent"),
    GENERAL("General");

    private final String name;

    DocumentStructure(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

}
