package com.ll.domain;

public class Quote {
    private String quote;
    private String writer;

    Quote(String quote, String writer){
        this.quote=quote;
        this.writer=writer;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}