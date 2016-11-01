package com.look.yx.look.presenter.implView;

import com.look.yx.look.bean.zhihu.ZhihuDaily;

/**
 * 子类实现这个接口，接口定义了子类需要做的事，细节由子类实现
 * Created by yx on 2016/10/28.
 */

public interface IZhihuFragment extends IBaseFragment {
    //刷新列表
    void updateList(ZhihuDaily zhihuDaily);
}
