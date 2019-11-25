package com.ognice.kafka.listener;

import com.ognice.jdbc.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/25
 */
@Component
public class KafkaConsumerListener2 {

    @Autowired
    DataRepository dataRepository;

    /**
     * 保存用户行为信息
     *
     * @param content
     */
    @KafkaListener(groupId = "dbfk-group2", topics = "dbfk-test")
    public void processMessage(String content) {
        System.out.println("2接收消息：" + content);
        //持久化
        String sql = "insert into log_data (descinfo)values('" + content + "');";
        dataRepository.jdbcInsertData(sql);

    }

}
