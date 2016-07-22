package haoxiangchi.niule.com.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.List;

import static android.media.CamcorderProfile.get;

/**
 * 作者： Hokas
 * 时间： 2016/7/22
 * 类别：
 */

public class TabAdapter extends BaseAdapter {

    List<List<String>> date;
    Context context;

    public TabAdapter(Context context, List<List<String>> date) {
        this.date = date;
        this.context = context;
    }

    @Override
    public int getCount() {
        return date.size();
    }

    @Override
    public Object getItem(int position) {
        return date.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ch, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else
            viewHolder = (ViewHolder) convertView.getTag();
        List<String> strings =  date.get(position);
        viewHolder.cbOne.setText(strings.get(0));
        viewHolder.cbTwo.setText(strings.get(1));
        viewHolder.cbThree.setText(strings.get(2));
        viewHolder.cbFour.setText(strings.get(3));
        return convertView;
    }

    static class ViewHolder {

        CheckBox cbOne;
        CheckBox cbTwo;
        CheckBox cbThree;
        CheckBox cbFour;

        ViewHolder(View view) {
            cbOne = (CheckBox) view.findViewById(R.id.cbOne);
            cbTwo = (CheckBox) view.findViewById(R.id.cbTwo);
            cbThree = (CheckBox) view.findViewById(R.id.cbThree);
            cbFour = (CheckBox) view.findViewById(R.id.cbFour);
        }
    }
}
