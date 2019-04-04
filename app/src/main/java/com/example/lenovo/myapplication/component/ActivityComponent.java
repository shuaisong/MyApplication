package com.example.lenovo.myapplication.component;

import com.example.lenovo.myapplication.view.activity.VideoActivity;
import com.example.lenovo.myapplication.view.activity.CommentActivity;
import com.example.lenovo.myapplication.view.activity.DetailImgActivity;
import com.example.lenovo.myapplication.view.activity.Img_Video_Result;
import com.example.lenovo.myapplication.view.activity.LeadActivity;
import com.example.lenovo.myapplication.view.activity.MainActivity;
import com.example.lenovo.myapplication.view.activity.SearchActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(MainActivity activity);

    void inject(LeadActivity activity);

    void inject(DetailImgActivity activity);
    void inject(CommentActivity activity);
    void inject(VideoActivity activity);
    void inject(SearchActivity activity);
    void inject(Img_Video_Result activity);
}
