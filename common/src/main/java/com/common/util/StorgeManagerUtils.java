package com.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.common.base.BaseApplication;

import java.io.File;
import java.math.BigDecimal;

public class StorgeManagerUtils {

    /**
     * 判断是否又SD卡挂载
     *
     * @return ture
     */
    private static boolean isExistSdCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取SD卡剩余空间
     *
     * @return sd卡剩余空间 单位MB
     */
    public static long getSDFreeSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSizeLong();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocksLong();
        //返回SD卡空闲大小
        return (freeBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    /**
     * 获取SD卡总容量
     *
     * @return SD卡总容量 单位MB
     */
    public static long getSDAllSize() {
        //取得SD卡文件路径
        File path = Environment.getExternalStorageDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSizeLong();
        //获取所有数据块数
        long allBlocks = sf.getBlockCountLong();
        //返回SD卡大小
        return (allBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    /**
     * 获取手机剩余可用空间
     *
     * @return 剩余空间 单位MB
     */
    public static long getDataFreeSize() {
        //取得DATA文件路径
        File path = Environment.getDataDirectory();
        StatFs sf = new StatFs(path.getPath());
        //获取单个数据块的大小(Byte)
        long blockSize = sf.getBlockSizeLong();
        //空闲的数据块的数量
        long freeBlocks = sf.getAvailableBlocksLong();
        //返回SD卡空闲大小
        return (freeBlocks * blockSize) / 1024 / 1024; //单位MB
    }

    /**
     * 获取本应用的缓存
     *
     * @param context
     * @return
     */
    public static String getTotalCacheSize(Context context) {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除本应用的缓存
     *
     * @param context 上下文对象
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    /**
     * 删除某个文件
     *
     * @param dir 文件对象
     * @return true false
     */
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        }
        if (dir != null) {
            return dir.delete();
        } else {
            return false;
        }
    }

    /**
     * 获取文件尺寸
     *
     * @param file 文件
     * @return 尺寸
     */
    public static long getFolderSize(File file) {
        long size = 0;
        if (file != null) {
            File[] fileList = file.listFiles();
            if (fileList != null && fileList.length > 0) {
                for (int i = 0; i < fileList.length; i++) {
                    // 如果下面还有文件
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i]);
                    } else {
                        size = size + fileList[i].length();
                    }
                }
            }
        }
        return size;
    }

    /**
     * 格式化
     *
     * @param size 需要格式化的尺寸单位byte
     * @return 格式化后的值
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        double megaByte = kiloByte / 1024;
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = BigDecimal.valueOf(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

    /**
     *
     * @param packageName 要清除数据的应用的包名
     */
    public static void clearUserData(String packageName){
        try {

            // 获取其他应用的上下文
            Context c = BaseApplication.getApplication().createPackageContext(packageName,
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);
            ActivityManager am = (ActivityManager)
                    c.getSystemService(Context.ACTIVITY_SERVICE);
            // 清除对应应用的数据 需要 这个权限(这个权限是系统应用才能有的)"android.permission.CLEAR_APP_USER_DATA"
            am.clearApplicationUserData();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }



}
