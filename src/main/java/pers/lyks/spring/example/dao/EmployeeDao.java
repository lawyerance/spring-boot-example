package pers.lyks.spring.example.dao;

import org.springframework.stereotype.Repository;
import pers.lyks.spring.example.base.BaseDao;
import pers.lyks.spring.example.model.EmployeeModel;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Repository
public interface EmployeeDao extends BaseDao<EmployeeModel> {
}
