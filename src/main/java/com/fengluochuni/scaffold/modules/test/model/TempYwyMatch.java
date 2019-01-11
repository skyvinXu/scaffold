package com.fengluochuni.scaffold.modules.test.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * 业务员对应表
 *
 * @author rongsheng.xu
 * @since 2019-01-07
 */
@TableName("temp_ywy_match")
public class TempYwyMatch extends Model<TempYwyMatch> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 营业员编码
     */
	@TableField("yyb_code")
	private String yybCode;
    /**
     * 营业员姓名
     */
	@TableField("yyb_name")
	private String yybName;
    /**
     * 客户经理姓名
     */
	@TableField("jl_name")
	private String jlName;
    /**
     * 客户经理ID
     */
	@TableField("jl_id")
	private String jlId;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * id
     */
	@TableField("delete_flag")
	private Integer deleteFlag;
	private Date tsRefreshTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYybCode() {
		return yybCode;
	}

	public void setYybCode(String yybCode) {
		this.yybCode = yybCode;
	}

	public String getYybName() {
		return yybName;
	}

	public void setYybName(String yybName) {
		this.yybName = yybName;
	}

	public String getJlName() {
		return jlName;
	}

	public void setJlName(String jlName) {
		this.jlName = jlName;
	}

	public String getJlId() {
		return jlId;
	}

	public void setJlId(String jlId) {
		this.jlId = jlId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getTsRefreshTime() {
		return tsRefreshTime;
	}

	public void setTsRefreshTime(Date tsRefreshTime) {
		this.tsRefreshTime = tsRefreshTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
