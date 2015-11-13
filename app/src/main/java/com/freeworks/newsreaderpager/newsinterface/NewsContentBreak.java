package com.freeworks.newsreaderpager.newsinterface;

import com.freeworks.newsreaderpager.common.Quote;

import java.util.List;

/**
 * Created by vdua on 29/10/15.
 */
public interface NewsContentBreak {

    public List<String> getParts(String newsContent);

    public List<Quote> getQuotes(String newsContent);
}
