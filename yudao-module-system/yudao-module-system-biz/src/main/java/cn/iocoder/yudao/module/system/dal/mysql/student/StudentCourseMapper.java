package cn.iocoder.yudao.module.system.dal.mysql.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCoursePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生课程 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentCourseMapper extends BaseMapperX<StudentCourseDO> {

    default PageResult<StudentCourseDO> selectPage(StudentCoursePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentCourseDO>()
            .eqIfPresent(StudentCourseDO::getStudentId, reqVO.getStudentId())
            .likeIfPresent(StudentCourseDO::getName, reqVO.getName())
            .eqIfPresent(StudentCourseDO::getScore, reqVO.getScore())
            .betweenIfPresent(StudentCourseDO::getCreateTime, reqVO.getCreateTime())
            .orderByDesc(StudentCourseDO::getId));
    }

    default List<StudentCourseDO> selectListByStudentId(Long studentId) {
        return selectList(StudentCourseDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(StudentCourseDO::getStudentId, studentId);
    }
}