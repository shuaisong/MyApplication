package com.example.lenovo.myapplication.db;

import com.example.lenovo.myapplication.bean.PicListBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import java.util.Map;

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig authorModelDaoConfig;

    private final PicDao PicDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        authorModelDaoConfig = daoConfigMap.get(PicDao.class).clone();
        authorModelDaoConfig.initIdentityScope(type);

        PicDao = new PicDao(authorModelDaoConfig, this);

        registerDao(PicListBean.class, PicDao);
    }
    
    public void clear() {
        authorModelDaoConfig.clearIdentityScope();
    }

    public PicDao getAuthorModelDao() {
        return PicDao;
    }

}