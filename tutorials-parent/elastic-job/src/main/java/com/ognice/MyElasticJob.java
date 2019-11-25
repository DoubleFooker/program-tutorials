package com.ognice;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by huangkaifu on 2017/6/7.
 */
public class MyElasticJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
        }

    }
}