package com.itsdf.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * 手机参数信息 工具类
 *
 * @author dengfu.su
 */
public class Tool {
    public static boolean LOG = true;
    private static Tool instance = null;
    private static DateFormat dfLog = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Context mApplicationContext = null;
    private Activity mActivityContext = null;

    /*------------------  LOG处理  --------------*/
    private DisplayMetrics mDisplayMetrics;
    private TelephonyManager mTelephonyManager;

    private static String getLogMsg(String msg) {
        return dfLog.format(new Date()) + ":" + msg;
    }

    public static boolean getLogStatus() {
        return LOG;
    }

    /**
     * @param show true：打印Log信息 ， false：不打印Log信息
     * @since 2013.12.26
     * 这边增加Log信息打印处理
     */
    public static void setLogStatus(boolean show) {
        LOG = show;
    }

    public static void LOG_I(String tag, String msg) {
        if (LOG) Log.i(tag, getLogMsg(msg));
    }

    public static void LOG_D(String tag, String msg) {
        if (LOG) Log.d(tag, getLogMsg(msg));
    }

    public static void LOG_W(String tag, String msg) {
        if (LOG) Log.w(tag, getLogMsg(msg));
    }

    public static void LOG_E(String tag, String msg) {
        if (LOG) Log.e(tag, getLogMsg(msg));
    }

    public static void LOG_E(String tag, String msg, Throwable e) {
        if (LOG) Log.e(tag, getLogMsg(msg), e);
    }

    public static void LOG_V(String tag, String msg) {
        if (LOG) Log.v(tag, getLogMsg(msg));
    }

    public static Tool getTool() {
        if (instance == null)
            instance = new Tool();
        return instance;
    }

    public void init() {
        mDisplayMetrics = new DisplayMetrics();
        mActivityContext.getWindowManager().getDefaultDisplay()
                .getMetrics(mDisplayMetrics);

        mTelephonyManager = (TelephonyManager) mActivityContext
                .getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * ***************************************************
     */

    public Context getApplicationContext() {
        return mApplicationContext;
    }

    /**
     * @param context getApplicationContext()
     */
    public void setApplicationContext(Context context) {
        mApplicationContext = context;
    }

    public Context getActivityContext() {
        return mActivityContext;
    }

    /**
     * @param context Activity
     */
    public void setActivityContext(Context context) {
        mActivityContext = (Activity) context;
        init();
    }

    /*******************************************************/

    /**
     * @param id R.string.xxx
     * @return 对应R.string.xxx取出的信息
     */
    public String getResourcesString(int id) {
        String values = mActivityContext.getResources().getString(id);
        return values;
    }


    /*******************************************************/

    /**
     * @return 屏幕分辨率
     */
    public String getScreenPixels() {
        String values = mDisplayMetrics.widthPixels + " * "
                + mDisplayMetrics.heightPixels;
        return values;
    }

    /**
     * @return 密度
     */
    public String getDensity() {
        String values = mDisplayMetrics.density + "";
        return values;
    }

    /**
     * @return 像素密度(每英寸点数:dots per inch)
     */
    public String getDensityDpi() {
        String values = mDisplayMetrics.densityDpi + "";
        return values;
    }

    /**
     * 单位 sp 的 换算值: 一般用在设定字体大小中。
     * <p/>
     * 伸缩密度，图片、字体在不同分辨率上面运行，分辨率不一样导致设置的大小也就不一样。
     * <p/>
     * 一般字体大小设置为：
     * <p/>
     * DisplayMetrics dm= new DisplayMetrics();
     * getWindowManager().getDefaultDisplay().getMetrics(dm);
     * <p/>
     * pixelSize = (int)scaledPixelSize * dm.scaledDensity;
     * <p/>
     * 这样可以适配在多个分辨率上面
     *
     * @return 伸缩密度:单位 sp 的 换算值
     */
    public String getscaledDensity() {
        String values = mDisplayMetrics.scaledDensity + "";
        return values;
    }

	/* ------------------- BOARD 主板 ------------------- */

    /**
     * @return 主板名称
     */
    public String getBoard() {
        return android.os.Build.BOARD;
    }

    /**
     * @return 系统引导程序版本号
     */
    public String getBootLoader() {
        return android.os.Build.BOOTLOADER;
    }

	/*------------- BRAND 运营商  ------------------------*/
    // phoneInfo += ", CPU_ABI2: " + android.os.Build.CPU_ABI2;

    /**
     * @return 品牌
     */
    public String getBrand() {
        return android.os.Build.BRAND;
    }

    public String getCPU_ABI() {
        return android.os.Build.CPU_ABI;
    }

	/*------------------ DEVICE 驱动 ----------------------*/

    /**
     * @return 设备驱动
     */
    public String getDevice() {
        return android.os.Build.DEVICE;
    }

	/*------------------ DISPLAY 显示 ----------------------*/

    /**
     * @return 一个构建ID字符串意味着显示给用户
     */
    public String getDisplay() {
        return android.os.Build.DISPLAY;
    }

	/*--------------------- 指纹 -----------------*/

    /**
     * @return 一个字符串, 唯一地标识此构建
     */
    public String getFingerprint() {
        return android.os.Build.FINGERPRINT;
    }

	/*-------------- HARDWARE 硬件  -----------------*/

    /**
     * 硬件的名称(从内核命令行或/ proc)。
     *
     * @return 硬件的名称
     */
    public String getHardware() {
        return android.os.Build.HARDWARE;
    }

    public String getHost() {
        return android.os.Build.HOST;
    }

    public String getId() {
        return android.os.Build.ID;
    }

	/*------------------  MANUFACTURER 生产厂家  --------------*/

    /**
     * @return 手机制造商
     */
    public String getManufacturer() {
        return android.os.Build.MANUFACTURER;
    }

	/*------------------------MODEL 机型  ****************/

    /**
     * @return 手机型号
     */
    public String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * @return IMEI
     */
    public String getIMEI() {
        String value = mTelephonyManager.getDeviceId();
        return value;
    }

    /**
     * @return 产品名称
     */
    public String getProduct() {
        return android.os.Build.PRODUCT;
    }

    public String getRadio() {
        return android.os.Build.RADIO;
    }

    public String getTags() {
        return android.os.Build.TAGS;
    }

    public long getTime() {
        return android.os.Build.TIME;
    }

    /**
     * @return 构建的类型, 如“用户”或“eng”。
     */
    public String getType() {
        return android.os.Build.TYPE;
    }

    public String getUser() {
        return android.os.Build.USER;
    }

	/*-------------------  VERSION.RELEASE 固件版本  -----------*/

    /**
     * @return 系统版本号
     */
    public String getVersionRelease() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * @return 开发代号 REL
     */
    public String getVersionCodeName() {
        return android.os.Build.VERSION.CODENAME;
    }

	/*----------------VERSION.SDK SDK版本  --------------------*/

    /**
     * @return SDK版本号
     */
    public String getVersionSDK() {
        return android.os.Build.VERSION.SDK;
    }

    /**
     * @return SDK版本号
     */
    public int getVersionSDK_INF() {
        return android.os.Build.VERSION.SDK_INT;
    }

	/*-----------  VERSION.INCREMENTAL 基带版本 ---------------*/

    /**
     * @return 基带版本
     */
    public String getIncremental() {
        return android.os.Build.VERSION.INCREMENTAL;
    }

}
