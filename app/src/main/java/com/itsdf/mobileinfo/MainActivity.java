package com.itsdf.mobileinfo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.itsdf.utility.Tool;

/**
 * 手机参数信息
 *
 * @author dengfu.su
 *
 */
public class MainActivity extends Activity {
	/** 显示System信息 */
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Tool.getTool().setActivityContext(this);

		mTextView = (TextView) findViewById(R.id.showSystemInfo);
		mTextView.setMovementMethod(new ScrollingMovementMethod());
		mTextView.setText(getInfo());
		Log.d("sdf", "[MainActivity] onCreate : getDeviceInfo = " + getDeviceInfo(this));

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		Log.d("sdf", "[MainActivity] onWindowFocusChanged : hasFocus = " + hasFocus);

	}

	/**
	 *
	 * @return 手机参数信息
	 */
	private String getInfo() {
		Log.e("sdf", "mTool.getModel() = " + Tool.getTool().getModel());
		// 所查信息容器
		StringBuffer buf = new StringBuffer();
		buf.append("资源分辨率：" + Tool.getTool().getResourcesString(R.string.system_dpi));
		buf.append("\n");
		buf.append("屏幕分辨率：" + Tool.getTool().getScreenPixels());
		buf.append("\n");
		buf.append("密\t\t\t\t度：" + Tool.getTool().getDensity());
		buf.append("\n");
		buf.append("像素密度：" + Tool.getTool().getDensityDpi());
		buf.append("\n");
		buf.append("伸缩密度：" + Tool.getTool().getscaledDensity());
		buf.append("\n");

		buf.append("----------------------------------");
		buf.append("\n");

		buf.append("系统版本号：Android" + Tool.getTool().getVersionRelease());
		buf.append("\n");
		buf.append("SDK版本号：" + Tool.getTool().getVersionSDK_INF());
		buf.append("\n");
		buf.append("系统定制商：");
		buf.append("\n");
		buf.append("基带版本：" + Tool.getTool().getIncremental());
		buf.append("\n");
		buf.append("CPU_ABI：" + Tool.getTool().getCPU_ABI());
		buf.append("\n");
		buf.append("Hardware：" + Tool.getTool().getHardware());
		buf.append("\n");
		buf.append("手机制造商：" + Tool.getTool().getManufacturer());
		buf.append("\n");
		buf.append("品\t\t\t\t牌：" + Tool.getTool().getBrand());
		buf.append("\n");
		buf.append("手机型号：" + Tool.getTool().getModel());
		buf.append("\n");
		buf.append("IMEI：" + Tool.getTool().getIMEI());
		buf.append("\n");
		buf.append("Host：" + Tool.getTool().getHost());
		buf.append("\n");
		buf.append("ID：" + Tool.getTool().getId());
		buf.append("\n");
		buf.append("产品名称：" + Tool.getTool().getProduct());
		buf.append("\n");
		buf.append("Tags：" + Tool.getTool().getTags());
		buf.append("\n");
		// buf.append("Time：" + mTool.getTime());
		// buf.append("\n");
		buf.append("构建的类型：" + Tool.getTool().getType());
		buf.append("\n");
		buf.append("User：" + Tool.getTool().getUser());
		buf.append("\n");
		buf.append("主板名称：" + Tool.getTool().getBoard());
		buf.append("\n");
		buf.append("系统引导程序版本号：" + Tool.getTool().getBootLoader());
		buf.append("\n");
		buf.append("设备驱动：" + Tool.getTool().getDevice());
		buf.append("\n");
		buf.append("Display：" + Tool.getTool().getDisplay());
		buf.append("\n");
		buf.append("指\t\t\t\t纹：" + Tool.getTool().getFingerprint());
		buf.append("\n");

		buf.append("----------------------------------");
		buf.append("\n");

		return buf.toString();
	}

	public static String getDeviceInfo(Context context) {
		try{
			org.json.JSONObject json = new org.json.JSONObject();
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

//      String mac = wifi.getConnectionInfo().getMacAddress();
//      json.put("mac", mac);

//      if( TextUtils.isEmpty(device_id) ){
//        device_id = mac;
//      }

			if( TextUtils.isEmpty(device_id) ){
				device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);
			}

			json.put("device_id", device_id);

			return json.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
