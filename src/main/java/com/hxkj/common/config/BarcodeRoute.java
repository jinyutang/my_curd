/**
 * 
 */
package com.hxkj.common.config;

import com.hxkj.Barcode.controller.BcAppController;
import com.hxkj.Barcode.controller.BcBarcodeController;
import com.hxkj.Barcode.controller.BcEventsController;
import com.hxkj.Barcode.controller.BcScanlogController;
import com.hxkj.Barcode.controller.BcUserController;
import com.hxkj.common.constant.Constant;
import com.jfinal.config.Routes;

/**
 * @author yutang.jin@finisar.com
 *
 */
public class BarcodeRoute extends Routes{

    /* (non-Javadoc)
     * @see com.jfinal.config.Routes#config()
     */
    @Override
    public void config() {
        add("/bcBarcode", BcBarcodeController.class, Constant.VIEW_PATH);
        add("/bcScanlog", BcScanlogController.class, Constant.VIEW_PATH);
        add("/bcUser", BcUserController.class, Constant.VIEW_PATH);
        add("/bcApp", BcAppController.class, Constant.VIEW_PATH);
        add("/bcEvents", BcEventsController.class, Constant.VIEW_PATH);
    }

}
