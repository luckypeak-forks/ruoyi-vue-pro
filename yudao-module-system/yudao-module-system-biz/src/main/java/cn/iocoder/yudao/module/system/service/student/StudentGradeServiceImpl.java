package cn.iocoder.yudao.module.system.service.student;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradePageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.student.vo.StudentGradeSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.student.StudentGradeDO;
import cn.iocoder.yudao.module.system.dal.mysql.student.StudentGradeMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.STUDENT_GRADE_NOT_EXISTS;

/**
 * 学生班级 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class StudentGradeServiceImpl implements StudentGradeService {

    @Resource
    private StudentGradeMapper studentGradeMapper;

    @Override
    public Long createStudentGrade(StudentGradeSaveReqVO createReqVO) {
        // 插入
        StudentGradeDO studentGrade = BeanUtils.toBean(createReqVO, StudentGradeDO.class);
        studentGradeMapper.insert(studentGrade);
        // 返回
        return studentGrade.getId();
    }

    @Override
    public void updateStudentGrade(StudentGradeSaveReqVO updateReqVO) {
        // 校验存在
        validateStudentGradeExists(updateReqVO.getId());
        // 更新
        StudentGradeDO updateObj = BeanUtils.toBean(updateReqVO, StudentGradeDO.class);
        studentGradeMapper.updateById(updateObj);
    }

    @Override
    public void deleteStudentGrade(Long id) {
        // 校验存在
        validateStudentGradeExists(id);
        // 删除
        studentGradeMapper.deleteById(id);
    }

    private void validateStudentGradeExists(Long id) {
        if (studentGradeMapper.selectById(id) == null) {
            throw exception(STUDENT_GRADE_NOT_EXISTS);
        }
    }

    @Override
    public StudentGradeDO getStudentGrade(Long id) {
        return studentGradeMapper.selectById(id);
    }

    @Override
    public PageResult<StudentGradeDO> getStudentGradePage(StudentGradePageReqVO pageReqVO) {
        return studentGradeMapper.selectPage(pageReqVO);
    }

}