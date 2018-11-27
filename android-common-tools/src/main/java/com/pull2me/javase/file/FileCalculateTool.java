package com.pull2me.javase.file;


import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by drz on 2016/4/3.
 */
public class FileCalculateTool {
    public static final String KB = "KB";
    public static final String MB = "MB";
    public static final String GB = "GB";
    public static final String TB = "TB";
    public static final String PB = "PB";

    /**
     * 返回文件长度的字节长度
     *
     * @param size
     * @param sizePart 文件长度单位 1--KB 2--MB 3--GB
     * @return
     */
    public static long getByteSize(int size, String sizePart) {
        long length = 0;
        switch (sizePart) {
            case "1":
                length = size * 1024;
                break;
            case "2":
                length = size * (1024 * 1024);
                break;
            case "3":
                length = size * (1024 * 1024 * 1024);

                break;

            default:
                break;
        }
        return length;
    }

    private static void ioClose(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size 注意单位是Byte字节
     * @return
     */
    public static String getDataSize(long size) {
        DecimalFormat format = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "Bytes";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return format.format(kbsize) + "KB";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return format.format(mbsize) + "MB";
        } else if (size < 1024l * 1024l * 1024l * 1024l) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return format.format(gbsize) + "GB";
        } else {
            return "size: error";
        }

    }

    /**
     * 返回文件的格式化带单位的长度，注意这个值只应该做显示信息，不应参与运算
     *
     * @param file
     * @param sizePart
     * @return
     */
    private static long getUnAccurateSize(File file, String sizePart) {
        long length = file.length();
        switch (sizePart) {
            case "1":
                length = length / 1024;
                break;
            case "2":
                length = length / (1024 * 1024);
                break;
            case "3":
                length = length / (1024 * 1024 * 1024);
                break;
            default:
                break;
        }
        return length;
    }

    /**
     * 单线程获取文件或者目录的总大小，单位byte字节
     *
     * @param file
     * @return
     */
    public static long getFileOrForderSize(File file) {
        if (file.isFile()) {
            return file.length();
        }
        long totalSize = 0L;
        File[] children = file.listFiles();
        for (File f : children) {
            totalSize += getFileOrForderSize(f);
        }
        return totalSize;
    }
}
