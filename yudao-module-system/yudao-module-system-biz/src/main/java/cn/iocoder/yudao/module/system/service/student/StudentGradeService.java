package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradeSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentGradeDO;

import javax.validation.Valid;

/**
 * 学生班级 Service 接口
 *
 * @author 芋道源码
 */
public interface StudentGradeService {

    /**
     * 创建学生班级
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createStudentGrade(@Valid StudentGradeSaveReqVO createReqVO);

    /**
     * 更新学生班级
     *
     * @param updateReqVO 更新信息
     */
    void updateStudentGrade(@Valid StudentGradeSaveReqVO updateReqVO);

    /**
     * 删除学生班级
     *
     * @param id 编号
     */
    void deleteStudentGrade(Long id);

    /**
     * 获得学生班级
     *
     * @param id 编号
     * @return 学生班级
     */
    StudentGradeDO getStudentGrade(Long id);

    /**
     * 获得学生班级分页
     *
     * @param pageReqVO 分页查询
     * @return 学生班级分页
     */
    PageResult<StudentGradeDO> getStudentGradePage(StudentGradePageReqVO pageReqVO);

}