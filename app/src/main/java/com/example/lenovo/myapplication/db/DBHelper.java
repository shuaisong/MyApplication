package com.example.lenovo.myapplication.db;

import android.content.Context;

import com.example.lenovo.myapplication.bean.PicListBean;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by lenovo on 2018/8/25.
 * auther:lenovo
 * Date：2018/8/25
 */
public class DBHelper {
    private static final String DB_NAME = "pic.db";//数据库名称
    private static DBHelper instance;
    private static DBManager<PicListBean, Long> author;
    private DaoMaster.DevOpenHelper mHelper;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    private DBHelper() {

    }

    public void init(Context context) {
        mHelper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public void init(Context context, String dbName) {
        mHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper();
                }
            }
        }
        return instance;
    }

    public DBManager<PicListBean, Long> author() {
        if (author == null) {
            author = new DBManager<PicListBean, Long>() {

                @Override
                public AbstractDao<PicListBean, Long> getAbstractDao() {
                    return mDaoSession.getAuthorModelDao();
                }
            };
        }
        return author;
    }

    public DaoMaster getDaoMaster() {
        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public void clear() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }

    public void close() {
        clear();
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }
}
