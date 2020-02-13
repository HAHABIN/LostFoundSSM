package dao;

import java.util.List;

/**
 * 基类
 * @param <T>
 */
public interface BaseDao<T> {

    /**
     * 查询
     */
    List<T> query();

    /**
     * 插入
     */
    int insert(T t);

    /**
     * 更新
     */
    int update(T t);


    /**
     * 删除
     */
    int delete(long id);



}
