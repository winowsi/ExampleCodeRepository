package com.winowsi.studySwagger.controller;

import com.sun.source.tree.NewArrayTree;
import com.winowsi.studySwagger.entity.Student;
import com.winowsi.studySwagger.util.Information;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 学习Swagger的前端控制器
 * @author: zhaoyao
 * @date: 2021/08/22
 */

@ApiModel("学生控制类")
@RestController
public class StudyController {
    /**
     *@ApiOperation：注释接口的注释
     * @return:student
     */
    @ApiOperation("获取到学生的接口")
    @GetMapping("/getStudent")
    public Information getStudent(){
        Student student = new Student();
        return Information.Success().AddData("student",student);
    }
    @PostMapping("/postStudent")
    public Information postStudent(){
        Student student = new Student();
        student.setName("HelloWord");
        student.setSex(true);
        student.setPerformance(100L);
        return Information.Success().AddData("student",student);
    }

    /**
     *
     * @return返回值类型只要是实体类就会被扫描到swagger的model中
     */
    @PutMapping("/putStudent")
    @ApiOperation("推送学生")
    public Student putStudent(@ApiParam("学生对象") Student student){
        return null;
    }

}
