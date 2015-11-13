package com.freeworks.newsreaderpager.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freeworks.newsreaderpager.R;
import com.freeworks.newsreaderpager.common.Quote;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    private static final String NEWS_READER_SERVICE = "news_reader_service";

    private String mNewsReaderService;

    private ImageView mNewsImageView;
    private ViewGroup mNewsContainer;
    private String mImageUri;
    private List<String> mNewsContent;
    private List<Quote> mNewsQuotes;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sNewsReaderService Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String sNewsReaderService) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(NEWS_READER_SERVICE, sNewsReaderService);
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsReaderService = getArguments().getString(NEWS_READER_SERVICE);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (mImageUri != null) {
            showNews(mImageUri, mNewsContent, mNewsQuotes);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newsdetail, container, false);
        mNewsImageView = (ImageView) view.findViewById(R.id.news_image);
        mNewsContainer = (ViewGroup) view.findViewById(R.id.news_container);
        return view;
    }

    private TextView getNewsContentView(String content) {
        TextView textView = new TextView(getActivity());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(content);
        return textView;
    }

    private void showNews(String imageUri, List<String> content, List<Quote> quotes) {
        int contentIndex = 0;
        int quoteIndex = 0;
        while(contentIndex < content.size() && quoteIndex < quotes.size()) {
            mNewsContainer.addView(getNewsContentView(content.get(contentIndex++)));
            mNewsContainer.addView(getNewsQuoteView(quotes.get(quoteIndex++)));
        }
        while(contentIndex < content.size()) {
            mNewsContainer.addView(getNewsContentView(content.get(contentIndex++)));
        }
    }

    public void setNewsContent(String imageUri, List<String> content, List<Quote> quotes) {
        if (getActivity() == null) {
            mImageUri = imageUri;
            mNewsContent = content;
            mNewsQuotes = quotes;
        } else {
            showNews(imageUri, content, quotes);
        }
    }

    private View getNewsQuoteView(Quote s) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.quotelayout, mNewsContainer, false);
        TextView contentView = (TextView) view.findViewById(R.id.quote_content);
        contentView.setText(s.getContent());
        TextView subtitleView = (TextView) view.findViewById(R.id.quote_subtitle);
        subtitleView.setText(s.getSubtitle());
        return view;
    }
}
