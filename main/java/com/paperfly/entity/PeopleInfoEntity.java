package com.paperfly.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
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
	private Long id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String no;
	/**
	 * 
	 */
	private String sex;
	/**
	 * 
	 */
	private String phone;

}
