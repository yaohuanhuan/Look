package com.look.yx.look.presenter.implView;

import com.look.yx.look.bean.meizhi.Gank;
import com.look.yx.look.bean.meizhi.Meizi;

import java.util.ArrayList;

/**
 * Created by yx on 2016/10/28.
 */

public interface IMeiziFragment extends IBaseFragment {
    void updateMeiziData(ArrayList<Meizi> list);
    void updateVedioData(ArrayList<Gank> list);
}
