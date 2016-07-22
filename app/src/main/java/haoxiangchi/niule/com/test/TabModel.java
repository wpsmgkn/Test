package haoxiangchi.niule.com.test;

import java.io.Serializable;
import java.util.List;

/**
 * 作者： Hokas
 * 时间： 2016/7/22
 * 类别：
 */

public class TabModel implements Serializable {

    private List<List<String>> date;

    public List<List<String>> getDate() {
        return date;
    }

    public void setDate(List<List<String>> date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TabModel{" +
                "date=" + date +
                '}';
    }
}
