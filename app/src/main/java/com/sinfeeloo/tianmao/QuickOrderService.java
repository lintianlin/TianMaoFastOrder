package com.sinfeeloo.tianmao;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * @author: mhj
 * @date: 2017/11/10 13:57
 * @desc:
 */

public class QuickOrderService extends AccessibilityService {

    public static final String TAG = "mhj";
    private int num = 0;

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate-----------------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand-----------------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy-----------------");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        Log.e(TAG, eventType + "-----------------");
        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                String className = event.getClassName().toString();
                Log.e(TAG, className + "*******************");
                //确认订单页面
                if (className.equals("com.tmall.wireless.maintab.module.TMMainTabActivity")) {
                    goClearing();
                } else if (className.equals("com.tmall.wireless.mbuy.ui.TMOrderMbuyActivity")) {
                    Log.e(TAG, "点击了提交按钮*******************");
                    //点击提交按钮
                    commit();
                } else if (className.equals("com.tmall.wireless.ordermanager.list.TMOrderListActivity")) {//待付款
                    Log.e(TAG, "点击了提交按钮*******************");
                    //点击提交按钮
//                    paying();
                } else if (className.equals("com.alipay.android.app.flybird.ui.window.FlyBirdWindowActivity")) {
                    num++;
                    Log.e(TAG, "支付*******************" + num);
                    if (1 == num) {
                        confirmpay();
                    }
                    if (2 == num) {
                        pay();
                    }
                }
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                String className2 = event.getClassName().toString();
                Log.e(TAG, className2 + "*********className2**********");
                break;
        }
    }

    /**
     * 从购物车中寻找 “结算” 按钮并点击
     */

    private void goClearing() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
//            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("button_cart_charge");
//            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("@id/tm_mcart_root");
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("结算");
            for (AccessibilityNodeInfo n : list) {
                Log.e(TAG, "点击了结算");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        } else {
            Log.e(TAG, "getRootInActiveWindow=null");
        }

    }

    /**
     * 从待付款中寻找 “付款” 按钮并点击
     */

    private void paying() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("付款");
            for (AccessibilityNodeInfo n : list) {
                Log.e(TAG, "点击了结算");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        } else {
            Log.e(TAG, "getRootInActiveWindow=null");
        }

    }

    /**
     * 从确认订单页面中寻找 “提交订单” 按钮并点击
     */
    private void commit() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
//            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("btn_sumit");
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("提交订单");
            for (AccessibilityNodeInfo n : list) {
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        } else {
            Log.e(TAG, "getRootInActiveWindow=null");
        }
    }

    /**
     * 从确认订单页面中寻找 “立即支付” 按钮并点击
     */
    private void confirmpay() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
//            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByViewId("btn_sumit");
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText("立即付款");
            for (AccessibilityNodeInfo n : list) {
                Log.e(TAG, "点击了立即付款");
                n.getParent().performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        } else {
            Log.e(TAG, "getRootInActiveWindow=null");
        }
    }

    /**
     * 从确认订单页面中寻找 “立即支付” 按钮并点击
     */
    private void pay() {
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
        if (nodeInfo != null) {
//            List<AccessibilityNodeInfo> list1 = nodeInfo.findAccessibilityNodeInfosByViewId("@id/key_num_6");
            List<AccessibilityNodeInfo> list1 = nodeInfo.findAccessibilityNodeInfosByText("6");
            for (AccessibilityNodeInfo n : list1) {
                Log.e(TAG, "1");
                n.performAction(AccessibilityNodeInfo.ACTION_LONG_CLICK);
            }
            List<AccessibilityNodeInfo> list2 = nodeInfo.findAccessibilityNodeInfosByText("6");
//            List<AccessibilityNodeInfo> list2 = nodeInfo.findAccessibilityNodeInfosByViewId("@id/key_num_6");
            for (AccessibilityNodeInfo n : list2) {
                Log.e(TAG, "2");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            List<AccessibilityNodeInfo> list3 = nodeInfo.findAccessibilityNodeInfosByText("6");
//            List<AccessibilityNodeInfo> list3 = nodeInfo.findAccessibilityNodeInfosByViewId("@id/key_num_6");
            for (AccessibilityNodeInfo n : list3) {
                Log.e(TAG, "3");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            List<AccessibilityNodeInfo> list4 = nodeInfo.findAccessibilityNodeInfosByText("6");
//            List<AccessibilityNodeInfo> list4 = nodeInfo.findAccessibilityNodeInfosByViewId("@id/key_num_6");
            for (AccessibilityNodeInfo n : list4) {
                Log.e(TAG, "4");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            List<AccessibilityNodeInfo> list5 = nodeInfo.findAccessibilityNodeInfosByText("6");
//            List<AccessibilityNodeInfo> list5 = nodeInfo.findAccessibilityNodeInfosByViewId("@id/key_num_6");
            for (AccessibilityNodeInfo n : list5) {
                Log.e(TAG, "5");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
            List<AccessibilityNodeInfo> list6 = nodeInfo.findAccessibilityNodeInfosByText("6");
//            List<AccessibilityNodeInfo> list6 = nodeInfo.findAccessibilityNodeInfosByViewId("@id/key_num_6");
            for (AccessibilityNodeInfo n : list6) {
                Log.e(TAG, "6");
                n.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            }
        } else {
            Log.e(TAG, "getRootInActiveWindow=null");
        }
    }

    @Override
    public void onInterrupt() {

    }
}
