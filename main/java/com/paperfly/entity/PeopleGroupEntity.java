package com.paperfly.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 
 * 
 * @author paperfly
 * @email 1430978392@qq.com
 * @date 2020-09-08 21:16:50
 */
@Data
@TableName("people_group")

public class PeopleGroupEntity extends BaseRowModel implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@ExcelProperty(value = "姓名" ,index = 1)
	private String name;

	@ExcelProperty(value = "学号",index = 2)
	private String no;

	@ExcelProperty(value = "生成时间" ,index = 3,format = "yyyy-MM-dd")
	private Date orderTime;
	/**
	 * 
	 */
	@ExcelProperty(value = "ID",index = 0)
	@TableId
	private Long id;


	@TableField(exist = false)
	private Map<Integer, CellStyle> cellStyleMap = new HashMap<Integer,CellStyle>();

}
