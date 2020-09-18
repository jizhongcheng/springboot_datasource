package com.windmill.zyfz.api.entity.wlxy;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;


import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "WlxyYgjbxx", description = "用户实体类")
@Table(name = "HR_EL_YGJBXX")
@Getter
@Setter
public class Wlxy{

	private static final long serialVersionUID =  1L;

	/**
	 * 员工基本信息
	 */
	@Id
	@Column(name = "YG_ID")
	private String ygId;

	@Column(name = "XM")
	@Length(min = 0, max = 30, message = "字段长度不能小于{min}大于{max}")
	private String  xm;
	
	@Column(name = "XMPY")
	@Length(min = 0, max = 30, message = "字段长度不能小于{min}大于{max}")
	private String  xmpy;
	
	@Column(name = "XB")
	@Length(min = 0, max = 10, message = "字段长度不能小于{min}大于{max}")
	private String  xb;
	
	@Column(name = "GH")
	@Length(min = 0, max = 32, message = "字段长度不能小于{min}大于{max}")
	private String  gh;
	
//	@Column(name = "ID")
//	@Length(min = 0, max = 32, message = "字段长度不能小于{min}大于{max}")
//	private String  id;
	
	@Column(name = "ZWJB")
	@Length(min = 0, max = 32, message = "字段长度不能小于{min}大于{max}")
	private String  zwjb;
	
	@Column(name = "UTIME")
	@Length(min = 0, max = 32, message = "字段长度不能小于{min}大于{max}")
	private String  utime;

	public Wlxy() {
		super();
	}
	public Wlxy(String ygId, String xm, String xmpy, String xb, String gh, String zwjb, String utime) {
		super();
		this.ygId = ygId;
		this.xm = xm;
		this.xmpy = xmpy;
		this.xb = xb;
		this.gh = gh;
		this.zwjb = zwjb;
		this.utime = utime;
	}
	
}
