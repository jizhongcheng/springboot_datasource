package com.windmill.zyfz.api.entity.zyfz;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import com.windmill.common.entity.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZyfzYjxx extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "业绩表名称")
    @Transient
    private String hrTableName;

    public ZyfzYjxx() {
        super();
    }

    public ZyfzYjxx(String ygId, String xm, String xmpy, String xb, String gh, String zwjb, String utime) {
        super();
    }
}
