package pers.lyks.spring.example.base;

import java.util.List;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
public interface BaseDao<T> {
    T get(long id);

    List<T> list();

    int insert(T t);

    int delete(T t);

    int update(T t);
}
