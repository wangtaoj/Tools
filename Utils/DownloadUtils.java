package com.wangtao.springboot3.util;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author wangtao
 * Created at 2024-06-14
 */
public final class DownloadUtils {

    private DownloadUtils() {

    }

    public static void download(HttpServletResponse response, InputStream is, String filename) {
        addHeader(response, filename);
        try {
            IOUtils.copyLarge(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败, 原因: " + e.getMessage(), e);
        }
    }

    public static void download(HttpServletResponse response, byte[] bytes, String filename) {
        addHeader(response, filename);
        try {
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException("文件下载失败, 原因: " + e.getMessage(), e);
        }
    }

    public static void download(HttpServletResponse response, File file, String filename) {
        try (InputStream is = new FileInputStream(file)) {
            download(response, is, filename);
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败, 原因: " + e.getMessage(), e);
        }
    }

    public static void download(HttpServletResponse response, File file) {
        download(response, file, file.getName());
    }

    /**
     * 设置下载响应头
     */
    private static void addHeader(HttpServletResponse response, String filename) {
        Assert.hasText(filename, "filename cannot be empty");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        /*
         * 形如attachment; filename*=UTF-8''in.txt
         * 中文会使用URLEncoder进行编码
         */
        String contentDisposition = ContentDisposition.attachment()
                .filename(filename, StandardCharsets.UTF_8)
                .build()
                .toString();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
    }
}
