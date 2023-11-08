package com.ll.domain.quote;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quote {
    @Getter
    @Setter
    private String quote;
    @Getter
    @Setter
    private String writer;


}