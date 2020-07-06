package com.shenzc.netty.protocoltcp;

/**
 * @author shenzc
 * @create 2020-07-04-15:30
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 协议包
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageProtocol {
    private int len;        //关键
    private byte[] content;
}
