package com.freeworks.newsreaderpager.common;

import com.freeworks.newsreaderpager.newsinterface.NewsContentBreak;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdua on 13/11/15.
 */
public class NewsContent implements NewsContentBreak {

    private final String mData;
    private List<String> mParts;
    private List<Quote> mQuotes;

    public NewsContent(String data) {
        mData = data;
        getParts(data);
        getQuotes(data);
    }

    private int countCharacter(String str, char ch, int startIndex, int endIndex) {
        int start = Math.max(0, startIndex);
        int end = Math.min(endIndex, str.length());
        int count = 0;
        for (int i = start ; i < end; i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<String> getParts(String newsContent) {
        if (mParts != null) {
            return mParts;
        }
        int length = mData.length();
        int countQuote = countCharacter(mData, '"', 0, length) / 2;
        int partsLength = length / countQuote;
        int lastPart = length - partsLength*countQuote;
        mParts = new ArrayList<String>();
        int start = 0;
        for (int i = 0; i < countQuote; i++) {
            int end = start + partsLength;
            if (i == countQuote - 1) {
                end += lastPart;
            }
            while(end < length && !Character.isWhitespace(mData.charAt(end - 1))) {
                end++;
            }
            mParts.add(mData.substring(start, end));
            start = end;
        }
        return mParts;
    }

    @Override
    public List<Quote> getQuotes(String newsContent) {
        if (mQuotes != null) {
            return mQuotes;
        }
        mQuotes = new ArrayList<Quote>();
        int quoteStartIndex = mData.indexOf('"'), quoteEndIndex;
        while((quoteEndIndex = mData.indexOf('"', quoteStartIndex + 1)) != -1) {
            Quote quote = new Quote(mData.substring(quoteStartIndex, quoteEndIndex),"");
            mQuotes.add(quote);
            quoteStartIndex = quoteEndIndex;
        };
        return mQuotes;
    }
}
