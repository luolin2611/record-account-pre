package cn.recordaccount.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author luolin
 * @date 2021-03-01 16:29:35
 */
@Data
public class User implements Serializable {
    private Integer userId;
    private String userName;
    private String personalResume;
}
