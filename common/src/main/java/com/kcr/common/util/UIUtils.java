package com.kcr.common.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;



public class UIUtils {
	/**
	 * 弹出窗口风格
	 */
	// 默认Toast界面
	public static final int CUSTOMIZED_TOAST_NONE = 0;
	// 对话框代替Toast
	public static final int CUSTOMIZED_TOAST_DIALOG = 1;
	// 自定义Toast
	public static final int CUSTOMIZED_TOAST = 2;

	private static Toast toast;
	private static Dialog dialog;




	public static void showToast(Context context, int id) {
		showMessage(context, id, false);
	}

	public static void showToast(Context context, int id, boolean longToast) {
		showMessage(context, id, longToast);
	}

	public static Toast showMessage(Context context, String msg, boolean longToast, int customizedUI) {
		if (TextUtils.isEmpty(msg))
			return null;
		// change to customized Dialog to better UI
		if (customizedUI == CUSTOMIZED_TOAST_NONE) {
			Toast toast = Toast.makeText(context, msg, longToast ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
			toast.show();
			return toast;
		} else if (customizedUI == CUSTOMIZED_TOAST) {
			// Customized UI
//			View toastRoot = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
			Toast toast = new Toast(context);
//			toast.setView(toastRoot);
//			TextView tv = (TextView) toastRoot.findViewById(R.id.text_view_info);
//			tv.setText(msg);
//			if (longToast) {
//				toast.setDuration(Toast.LENGTH_LONG);
//			} else {
//				toast.setDuration(Toast.LENGTH_SHORT);
//			}
//			toast.show();
//			// toast.setGravity(Gravity.CENTER, 20, 20);
//			toast.setGravity(Gravity.CENTER, 0, 0);
			return toast;
		} else if (customizedUI == CUSTOMIZED_TOAST_DIALOG) {
//			showAlertDialog(context, msg, null);
		}
		return null;
	}


	public static Toast showMessage(Context context, String msg, boolean longToast) {
		// customized UI
		return showMessage(context, msg, longToast, CUSTOMIZED_TOAST_NONE);
	}

	public static Toast showMessage(Context context, int id, boolean longToast) {
		return showMessage(context, context.getString(id), longToast);
	}

	public static Toast showMessage(Context context, String msg) {
		return showMessage(context, msg, false);
	}

	public static Toast showMessage(Context context, int id) {
		return showMessage(context, id, false);
	}



	public static void showUnRpToast(Context context, int id) {
		String msg = context.getString(id);
		showUnRpToast(context, msg);
	}

	public static void showUnRpToast(Context context, String msg) {
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
		toast = showMessage(context, msg);
	}

	public static void dismissToast() {
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
	}


	public static int getStatusBarHeight(Activity context) {
		Rect frame = new Rect();
		context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}


	public static int getTitleBarHeight(Activity context) {
		int contentTop = context.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
		return contentTop - getStatusBarHeight(context);
	}

	/* dp,sp,px conversions */
	public static float convertPixelsToDp(Context ctx, float px) {
		DisplayMetrics metrics = ctx.getResources().getDisplayMetrics();
		return px / (metrics.densityDpi / 160f);
	}

	public static int convertDpToPixelInt(Context context, float dp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return (int) (dp * (metrics.densityDpi / 160f));
	}

	public static float convertDpToPixel(Context context, float dp) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		return  dp * (metrics.densityDpi / 160f);
	}

	public static int convertPxToSp(Context context, float px) {
		DisplayMetrics metric = context.getResources().getDisplayMetrics();
		float scaledDensity = metric.scaledDensity;
		return (int) (px / scaledDensity + 0.5f);
	}

	public static float spToPixels(Context context, float sp) {
		float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
		return sp * scaledDensity;
	}


}
