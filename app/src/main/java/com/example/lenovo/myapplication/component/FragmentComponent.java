package com.example.lenovo.myapplication.component;

import com.example.lenovo.myapplication.view.fragment.CollectFG;
import com.example.lenovo.myapplication.view.fragment.ImgFG;
import com.example.lenovo.myapplication.view.fragment.MineFG;
import com.example.lenovo.myapplication.view.fragment.PhotoFG;
import com.example.lenovo.myapplication.view.fragment.VideoFG;

import dagger.Component;

/**
 * Created by lenovo on 2018/8/12.
 * auther:lenovo
 * Date：2018/8/12
 */
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
    ImgFG inject(ImgFG fg);
    VideoFG inject(VideoFG fg);
    PhotoFG inject(PhotoFG fg);
    CollectFG inject(CollectFG fg);
    MineFG inject(MineFG fg);
}
