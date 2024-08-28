package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCoursePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCourseSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentCourseMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_COURSE_NOT_EXISTS;

/**
 * 学生课程 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StudentCourseServiceImpl implements StudentCourseService {

    @Resource
    private StudentCourseMapper studentCourseMapper;

    @Override
    public Long createStudentCourse(StudentCourseSaveReqVO createReqVO) {
        // 插入
        StudentCourseDO studentCourse = BeanUtils.toBean(createReqVO, StudentCourseDO.class);
        studentCourseMapper.insert(studentCourse);
        // 返回
        return studentCourse.getId();
    }

    @Override
    public void updateStudentCourse(StudentCourseSaveReqVO updateReqVO) {
        // 校验存在
        validateStudentCourseExists(updateReqVO.getId());
        // 更新
        StudentCourseDO updateObj = BeanUtils.toBean(updateReqVO, StudentCourseDO.class);
        studentCourseMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudentCourse(Long id) {
        // 校验存在
        validateStudentCourseExists(id);
        // 删除
        studentCourseMapper.deleteById(id);
    }

    private void validateStudentCourseExists(Long id) {
        if (studentCourseMapper.selectById(id) == null) {
            throw exception(STUDENT_COURSE_NOT_EXISTS);
        }
    }

    @Override
    public StudentCourseDO getStudentCourse(Long id) {
        return studentCourseMapper.selectById(id);
    }

    @Override
    public PageResult<StudentCourseDO> getStudentCoursePage(StudentCoursePageReqVO pageReqVO) {
        return studentCourseMapper.selectPage(pageReqVO);
    }

}