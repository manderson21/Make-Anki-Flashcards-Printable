package com.mattapplications.pdf;

import java.util.List;

public class Content {

    private List<String> content;
    private String x;
    private String y;

    public Content(List<String> content, String x, String y) {
        this.content = content;
        this.x = x;
        this.y = y;
    }
}
