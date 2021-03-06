# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#okhttp
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

-keep class okio.** { *; }
-keep interface okio.** { *; }
-dontwarn okio.**

-keep class com.hitomi.** { *; }
-keep interface com.hitomi.** { *; }
-dontwarn com.hitomi.**


-keep class com.example.lenovo.myapplication.bean.** { *; }

-keepattributes SourceFile,LineNumberTable # keep住源文件以及行号

# Retrofit
#retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontnote retrofit2.Platform
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions

-keep public class javax.**

-keep class * implements java.io.Serializable # 保持 Serializable 不被混淆

#fresco
#-keep class com.facebook.** {*;}
#-dontwarn okio.**
#-dontwarn com.squareup.okhttp.**
#-dontwarn okhttp3.**
#-dontwarn javax.annotation.**
#-dontwarn com.android.volley.toolbox.**
#-dontwarn com.facebook.**
#-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

#EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}


# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

### greenDAO 3
# #  ######## greenDao混淆  ##########
#greendao3.2.0,此是针对3.2.0，如果是之前的，可能需要更换下包名
# # -------------------------------------------
-dontwarn org.greenrobot.greendao.**
-keep class org.greenrobot.greendao.** { *; }
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties

# glide 的混淆代码
-dontwarn com.bumptech.glide.**
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
# banner 的混淆代码
-keep class com.youth.banner.** {
    *;
 }


 #XSnow
 -dontwarn com.vise.utils.**
 -keep class com.vise.xsnow.event.inner.ThreadMode { *; }
 -keep class com.vise.xsnow.http.api.ApiService { *; }
 -keep class com.vise.xsnow.http.mode.CacheMode
 -keep class com.vise.xsnow.http.mode.CacheResult { *; }
 -keep class com.vise.xsnow.http.mode.DownProgress { *; }
 -keep class com.vise.xsnow.http.strategy.**
 -keepclassmembers class * {
     @com.vise.xsnow.event.Subscribe <methods>;
 }
 -keep class com.bumptech.glide.Glide

-dontwarn jp.co.cyberagent.android.gpuimage.**

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn androidx.annotation.**
-dontwarn androidx.renderscript.**
