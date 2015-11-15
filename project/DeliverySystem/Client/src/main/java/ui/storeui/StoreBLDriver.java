package ui.storeui;

import bl.blImpl.storebl.*;
import bl.blService.storeblService.*;
import model.store.StoreAreaCode;

/**
 * Created by Sissel on 2015/10/26.
 */
public class StoreBLDriver {
    StockTackBLService stbls = new StockTackBLImpl();
    StoreInBLService sibls = new StoreInBLImpl();
    StoreOutBLService sobls = new StoreOutBLImpl();
    StoreIOBLService siobls = new StoreIOBLImpl();
    StoreModelBLService smbls = new StoreModelBLImpl();

    public void driveAll(){
        // StockTackBLService Test
        System.out.println("StockTackBLService Begin:");
        if(stbls.getOrder("222333") != null)
            System.out.println("getOrder tested");
        if(stbls.getStockTack() != null)
            System.out.println("getStockTack tested");
        if(stbls.getStoreInVO("222333") != null)
            System.out.println("getStoreInVO tested");
        if(stbls.makeExcel("hello") != null)
            System.out.println("makeExcel tested");
        System.out.println();

        // StoreInBLService Test
        System.out.println("StoreInBLService Begin:");
        if(sibls.clearLocalBuffer() != null)
            System.out.println("clearLocalBuffer tested");
        if(sibls.getOrderVO() != null)
            System.out.println("getOrderVO tested");
        if(sibls.loadOrder("222333") != null)
            System.out.println("loadOrder tested");
        if(sibls.getNewStoreInID("222") != null)
            System.out.println("getNewStoreInID tested");
        if(sibls.getAvailableLocation(StoreAreaCode.AIR) != null)
            System.out.println("getAvailableLocation tested");
        System.out.println();

        // StoreOutBLService Test
        System.out.println("StoreOutBLService Begin:");
        if(sobls.getOrderVO() != null)
            System.out.println("getOrderVO tested");
        if(sobls.loadOrder("222") != null)
            System.out.println("loadOrder tested");
        if(sobls.clearLocalBuffer() != null)
            System.out.println("clearLocalBuffer tested");
        if(sobls.getNewStoreOutID("222") != null)
            System.out.println("getNewStoreOutID tested");
        if(sobls.getTransportVO() != null)
            System.out.println("getTransportVO tested");
        System.out.println();

        // StoreIOBLService Test
        System.out.println("StoreIOBLService Begin:");
        if(siobls.getGoodsInfo("1", "2", "3", "4") != null)
            System.out.println("StoreIOBLService tested");
        if(siobls.filterGoods("ok") != null)
            System.out.println("filterGoods tested");
        if(siobls.checkFormat("12", "233", "12", "333") != null)
            System.out.println("checkFormat tested");
        System.out.println();

        // StoreModelBLService Test
        System.out.println("StoreModelBLService Begin:");
        if(smbls.clearLocalBuffer() != null)
            System.out.println("clearLocalBuffer");
        if(smbls.setWarningLine(23.33) != null)
            System.out.println("setWarningLine tested");
        if(smbls.addRow(StoreAreaCode.AIR, 123) != null)
            System.out.println("addRow tested");
        if(smbls.deleteRow(StoreAreaCode.AIR, 123, true) != null)
            System.out.println("deleteRow tested");
        if(smbls.adjustRow(StoreAreaCode.AIR, 12, 23, true) != null)
            System.out.println("adjustRow tested");
        if(smbls.expandPartition(StoreAreaCode.AIR, 123) != null)
            System.out.println("expandPartition tested");
        if(smbls.reducePartition(StoreAreaCode.RAIL, 233) != null)
            System.out.println("reducePartition tested");

    }

    public static void main(String[] args) {
        new StoreBLDriver().driveAll();
    }
}
