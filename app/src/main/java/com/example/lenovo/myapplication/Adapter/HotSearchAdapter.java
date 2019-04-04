package com.example.lenovo.myapplication.Adapter;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.myapplication.R;
import com.example.lenovo.myapplication.bean.SearchType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lenovo on 2018/9/1.
 * auther:lenovo
 * Date：2018/9/1
 */
public class HotSearchAdapter extends BaseAdapter {
    private List<SearchType.DataObjBean.KeywordsListBean> beanList;
    private Context context;

    public HotSearchAdapter(Context context, List<SearchType.DataObjBean.KeywordsListBean> beanList) {
        this.beanList = beanList;
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return beanList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;

        if (convertView == null) {
            // construct an item tag
            convertView = LayoutInflater.from(context).inflate(R.layout.hot_search_item, parent, false);
            mHolder = new ViewHolder(convertView);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        switch (position){
            case 0:
                mHolder.mIndex.setBackgroundColor(context.getResources().getColor(R.color.index1));
                break;
            case 1:
                mHolder.mIndex.setBackgroundColor(context.getResources().getColor(R.color.index2));
                break;
            case 2:
                mHolder.mIndex.setBackgroundColor(context.getResources().getColor(R.color.index3));
                break;
        }
        mHolder.mIndex.setText(String.format(context.getString(R.string.num), position + 1));
        int mSearchTimes = beanList.get(position).getSearch_times();
        if (mSearchTimes > 9999) {
            BigDecimal b1 = new BigDecimal(mSearchTimes);
            BigDecimal b2 = new BigDecimal(10000);
            String count = b1.divide(b2, 2, BigDecimal.ROUND_DOWN).toString();
            mHolder.mCount.setText(String.format(context.getString(R.string.num_w), count));
        }else {
            mHolder.mCount.setText(String.format(context.getString(R.string.num), mSearchTimes));
        }
        mHolder.mKeywords.setText(beanList.get(position).getKeywords());
        return convertView;
    }

    class ViewHolder {
        TextView mIndex;
        TextView mKeywords;
        TextView mCount;

        public ViewHolder(View convertView) {
            mIndex = convertView.findViewById(R.id.index);
            mKeywords  =convertView.findViewById(R.id.tag);
            mCount =convertView.findViewById(R.id.count);
        }
    }
}
