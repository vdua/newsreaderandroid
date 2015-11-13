package com.freeworks.newsreaderpager.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.freeworks.newsreaderpager.R;
import com.freeworks.newsreaderpager.common.NewsContent;
import com.freeworks.newsreaderpager.fragment.BlankFragment;
import com.freeworks.newsreaderpager.newsinterface.NewsContentBreak;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdua on 29/10/15.
 */
public class NewsReaderAdapter extends FragmentPagerAdapter {


    private final Context mContext;
    private int mCount;
    private List<NewsContentBreak> mNews;

    public NewsReaderAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mNews = new ArrayList<NewsContentBreak>();
        loadContent();
    }

    private void loadContent() {
        DownloadNewsTask task = new DownloadNewsTask();
        task.execute();
    }

    @Override
    public Fragment getItem(int position) {
        BlankFragment frag = BlankFragment.newInstance("test-service");
        NewsContentBreak news = mNews.get(position);
        if ((position*100)/mNews.size() > 50) {
            loadContent();
        }
        frag.setNewsContent("test", news.getParts(""), news.getQuotes(""));
        return frag;
    }

    @Override
    public int getCount() {
        return mNews.size();
    }

    private class DownloadNewsTask extends AsyncTask<Void, Integer, List<NewsContentBreak>> {

        @Override
        protected List<NewsContentBreak> doInBackground(Void... params) {
            List<NewsContentBreak> result = new ArrayList<NewsContentBreak>();
            result.add(new NewsContent(mContext.getString(R.string.lorem_ipsum_content_1)));
            result.add(new NewsContent(mContext.getString(R.string.lorem_ipsum_content_2)));
            result.add(new NewsContent(mContext.getString(R.string.lorem_ipsum_content_3)));
            result.add(new NewsContent(mContext.getString(R.string.lorem_ipsum_content_4)));
            return result;
        }

        protected void onPostExecute(List<NewsContentBreak> result) {
            mNews.addAll(result);
            NewsReaderAdapter.this.notifyDataSetChanged();
        }
    }
}
