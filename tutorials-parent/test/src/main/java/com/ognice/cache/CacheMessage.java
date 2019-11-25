package com.ognice.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: CacheMessage</p>
 * <p>Description:  </p>
 *
 * @author huangkaifu
 * @date 2019/11/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CacheMessage implements Serializable {
    private String appKey;
    private Long versionId;
    private List<String> userIds;


}
