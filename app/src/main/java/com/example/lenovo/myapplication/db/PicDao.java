package com.example.lenovo.myapplication.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.example.lenovo.myapplication.bean.PicListBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/**
 * Created by lenovo on 2018/8/25.
 * auther:lenovo
 * Date：2018/8/25
 */
public class PicDao extends AbstractDao<PicListBean, Long> {
    public PicDao(DaoConfig config) {
        super(config);
    }

    public PicDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    public static final String TABLENAME = "AUTHOR_PIC";

    public static class Properties {
        public final static Property Apid = new Property(0, Integer.class, "apid", true, "APID");
        public final static Property Comment_num = new Property(1, String.class, "comment_num", false, "COMMENT_NUM");
        public final static Property Collect_num = new Property(2, String.class, "collect_num", false, "COLLECT_NUM");
        public final static Property Title = new Property(3, String.class, "title", false, "TITLE");
        public final static Property Pic_num = new Property(4, Integer.class, "pic_num", false, "PIC_NUM");
        public final static Property Click_num = new Property(5, Integer.class, "click_num", false, "CLICK_NUM");
        public final static Property tag_name = new Property(6, String.class, "tag_name", false, "TAG_NAME");
        public final static Property Thumb_num = new Property(7, String.class, "thumb_num", false, "THUMB_NUM");
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "\"AUTHOR_PIC\" (" + //
                "\"APID\" INTEGER PRIMARY KEY ," + // 1: apid
                "\"COMMENT_NUM\" TEXT ," + // 2: Comment_num
                "\"COLLECT_NUM\" TEXT," + // 3: Collect_num
                "\"TITLE\" TEXT," + // 4: Title
                "\"PIC_NUM\" INTEGER NOT NULL," + // 5: Pic_num
                "\"CLICK_NUM\" INTEGER NOT NULL," + // 6: Click_num
                "\"TAG_NAME\" TEXT," + // 7: tag_name
                "\"THUMB_NUM\" TEXT);"
        ); // 8: Thumb_num
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"AUTHOR_PIC\"";
        db.execSQL(sql);
    }

    /**
     * Reads the values from the current position of the given cursor and returns a new entity.
     *
     * @param cursor
     * @param offset
     */
    @Override
    protected PicListBean readEntity(Cursor cursor, int offset) {
        PicListBean entity = new PicListBean(); //
        entity.setApid(cursor.getInt(offset)); // id
        entity.setComment_num(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCollect_num(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPic_num(cursor.getInt(offset + 4));
        entity.setClick_num(cursor.getInt(offset + 5));
        entity.setTag_name(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setThumb_num(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        return entity;
    }

    /**
     * Reads the key from the current position of the given cursor, or returns null if there's no single-value key.
     *
     * @param cursor
     * @param offset
     */
    @Override
    protected Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getLong(offset);
    }

    /**
     * Reads the values from the current position of the given cursor into an existing entity.
     *
     * @param cursor
     * @param entity
     * @param offset
     */
    @Override
    protected void readEntity(Cursor cursor, PicListBean entity, int offset) {
        entity.setApid(cursor.getInt(offset)); // id
        entity.setComment_num(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCollect_num(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTitle(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPic_num(cursor.getInt(offset + 4));
        entity.setClick_num(cursor.getInt(offset + 5));
        entity.setTag_name(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setThumb_num(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
    }

    /**
     * Binds the entity's values to the statement. Make sure to synchronize the statement outside of the method.
     *
     * @param stmt
     * @param entity
     */
    @Override
    protected void bindValues(DatabaseStatement stmt, PicListBean entity) {
        stmt.clearBindings();

        stmt.bindLong(1, entity.getApid());
        stmt.bindString(2, entity.getComment_num());

        stmt.bindString(3, entity.getCollect_num());

        stmt.bindString(4, entity.getTitle());

        stmt.bindLong(5, entity.getPic_num());

        stmt.bindLong(6, entity.getClick_num());

        stmt.bindString(7, entity.getTag_name());

        stmt.bindString(8, entity.getThumb_num());

    }

    /**
     * Binds the entity's values to the statement. Make sure to synchronize the enclosing DatabaseStatement outside
     * of the method.
     *
     * @param stmt
     * @param entity
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, PicListBean entity) {
        stmt.clearBindings();

        stmt.bindLong(1, entity.getApid());
        stmt.bindString(2, entity.getComment_num());

        stmt.bindString(3, entity.getCollect_num());

        stmt.bindString(4, entity.getTitle());

        stmt.bindLong(5, entity.getPic_num());

        stmt.bindLong(6, entity.getClick_num());

        stmt.bindString(7, entity.getTag_name());

        stmt.bindString(8, entity.getThumb_num());
    }

    /**
     * Updates the entity's key if possible (only for Long PKs currently). This method must always return the entity's
     * key regardless of whether the key existed before or not.
     *
     * @param entity
     * @param rowId
     */
    @Override
    protected Long updateKeyAfterInsert(PicListBean entity, long rowId) {
        entity.setApid((int) rowId);
        return rowId;
    }

    /**
     * Returns the value of the primary key, if the entity has a single primary key, or, if not, null. Returns null if
     * entity is null.
     *
     * @param entity
     */
    @Override
    protected Long getKey(PicListBean entity) {
        if (entity != null) {
            return (long) entity.getApid();
        } else {
            return null;
        }
    }

    /**
     * Returns true if the entity is not null, and has a non-null key, which is also != 0.
     * entity is null.
     *
     * @param entity
     */
    @Override
    protected boolean hasKey(PicListBean entity) {
        return entity.getApid() != 0;
    }

    /**
     * Returns true if the Entity class can be updated, e.g. for setting the PK after insert.
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
}
