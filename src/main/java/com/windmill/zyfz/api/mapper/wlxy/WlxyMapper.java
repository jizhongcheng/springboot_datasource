package com.windmill.zyfz.api.mapper.wlxy;

import java.util.List;

import com.windmill.zyfz.api.entity.wlxy.Wlxy;

import tk.mybatis.mapper.common.Mapper;

public interface WlxyMapper extends Mapper<Wlxy> {

    List<Wlxy> queryYgjbxxById(Wlxy ygjbxx);

}
