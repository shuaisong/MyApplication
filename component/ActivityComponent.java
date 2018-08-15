package com.example.lenovo.myapplication.component;

import com.example.lenovo.myapplication.view.activity.DetailImgActivity;
import com.example.lenovo.myapplication.view.activity.LeadActivity;
import com.example.lenovo.myapplication.view.activity.MainActivity;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    MainActivity inject(MainActivity activity);

    LeadActivity inject(LeadActivity activity);

    DetailImgActivity inject(DetailImgActivity activity);
}
