package pers.lyks.spring.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.lyks.spring.example.model.EmployeeModel;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Mapper
public interface EmployeeMapper {
    EmployeeModel get(long id);
}
