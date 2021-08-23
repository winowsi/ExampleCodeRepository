package com.winowsi.studySwagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 学生实体类
 * @author: zhaoyao
 * @date: 2021/08/22
 */
@Data
@ApiModel("学生实体类")
public class Student {
    /**
     * 姓名
     */
    @ApiModelProperty("学生姓名")
    private String name;
    /**
     * 性别
     */
    @ApiModelProperty("学生性别")
    private boolean sex;
    /**
     * 成绩
     */
    @ApiModelProperty("学生成绩")
    private Long performance;
}
