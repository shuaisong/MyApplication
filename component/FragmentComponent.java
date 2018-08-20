package com.example.lenovo.myapplication.component;

import com.example.lenovo.myapplication.view.fragment.ImgFG;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    ImgFG inject(ImgFG fg);
}
