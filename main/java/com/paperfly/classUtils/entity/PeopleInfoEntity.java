package com.paperfly.classUtils.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * 
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@Data
@TableName("people_info")
public class PeopleInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ExcelProperty(value = "ID",index = 0)
	private Long id;
	/**
	 * 
	 */
	@ExcelProperty(value = "姓名",index = 1)
	private String name;
	/**
	 * 
	 */
	@ExcelProperty(value = "学号",index = 2)
	private String no;
	/**
	 * 
	 */
	@ExcelProperty(value = "性别",index = 3)
	private String sex;
	/**
	 * 
	 */
	@ExcelProperty(value = "手机号",index = 4)
	private String phone;

}
