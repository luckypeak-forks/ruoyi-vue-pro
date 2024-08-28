package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCoursePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentCourseSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentCourseDO;

import javax.validation.Valid;

/**
 * 学生课程 Service 接口
 *
 * @author 芋道源码
 */
public interface StudentCourseService {

    /**
     * 创建学生课程
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStudentCourse(@Valid StudentCourseSaveReqVO createReqVO);

    /**
     * 更新学生课程
     *
     * @param updateReqVO 更新信息
     */
    void updateStudentCourse(@Valid StudentCourseSaveReqVO updateReqVO);

    /**
     * 删除学生课程
     *
     * @param id 编号
     */
    void deleteStudentCourse(Long id);

    /**
     * 获得学生课程
     *
     * @param id 编号
     * @return 学生课程
     */
    StudentCourseDO getStudentCourse(Long id);

    /**
     * 获得学生课程分页
     *
     * @param pageReqVO 分页查询
     * @return 学生课程分页
     */
    PageResult<StudentCourseDO> getStudentCoursePage(StudentCoursePageReqVO pageReqVO);

}