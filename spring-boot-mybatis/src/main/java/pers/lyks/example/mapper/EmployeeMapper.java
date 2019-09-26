package pers.lyks.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.lyks.example.model.EmployeeModel;

/**
 * @author lawyerance
 * @version 1.0 2018-08-04
 */
@Mapper
public interface EmployeeMapper {
    EmployeeModel get(long id);
}
