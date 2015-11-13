package com.freeworks.newsreaderpager.common;

/**
 * Created by vdua on 29/10/15.
 */
public class Quote {
    private String content;
    private String subtitle;

    public Quote(String content, String subtitle) {
        this.content = content;
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
