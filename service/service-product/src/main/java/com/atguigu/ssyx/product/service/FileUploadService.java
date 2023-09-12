/**
 * Copyright (c) 2023, CCSSOFT All Rights Reserved.
 */
package com.atguigu.ssyx.product.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * FileUploadService
 * </p>
 *
 * @author wangfeifan
 * @Version: 1.0
 * @since 7月 27, 2023
 */
public interface FileUploadService {
    //文件上传
    String fileUpload(MultipartFile file) throws Exception;
}
