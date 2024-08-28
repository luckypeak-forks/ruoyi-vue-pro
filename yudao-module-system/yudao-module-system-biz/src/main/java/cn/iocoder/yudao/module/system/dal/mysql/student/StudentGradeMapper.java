package cn.iocoder.yudao.module.system.dal.mysql.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradePageReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentGradeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 学生班级 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface StudentGradeMapper extends BaseMapperX<StudentGradeDO> {

    default PageResult<StudentGradeDO> selectPage(StudentGradePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<StudentGradeDO>()
            .eqIfPresent(StudentGradeDO::getStudentId, reqVO.getStudentId())
            .likeIfPresent(StudentGradeDO::getName, reqVO.getName())
            .eqIfPresent(StudentGradeDO::getTeacher, reqVO.getTeacher())
            .betweenIfPresent(StudentGradeDO::getCreateTime, reqVO.getCreateTime())
            .orderByDesc(StudentGradeDO::getId));
    }

    default StudentGradeDO selectByStudentId(Long studentId) {
        return selectOne(StudentGradeDO::getStudentId, studentId);
    }

    default int deleteByStudentId(Long studentId) {
        return delete(StudentGradeDO::getStudentId, studentId);
    }
}