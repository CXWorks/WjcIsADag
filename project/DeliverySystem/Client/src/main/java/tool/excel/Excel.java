package tool.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import message.OperationMessage;
import model.store.StoreArea;
import model.store.StoreAreaCode;
import model.store.StoreLocation;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import util.R.string;

/** 
 * Client//tool.excel//Excel.java
 * @author CXWorks
 * @date 2015年11月25日 下午11:00:49
 * @version 1.0 
 */
public class Excel {
	public static OperationMessage exportToExcel(String path,ArrayList<StoreArea> area){
		OperationMessage ans=new OperationMessage();
		//
		HSSFWorkbook workbook=new HSSFWorkbook();
		//
		HSSFCellStyle headStyle=workbook.createCellStyle();
		headStyle.setAlignment(HSSFCellStyle.BORDER_MEDIUM_DASH_DOT_DOT);
		HSSFCellStyle dataStyle=workbook.createCellStyle();
		dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		for (int i = 0; i < area.size(); i++) {
			StoreArea storeArea=area.get(i);
			HSSFSheet sheet=workbook.createSheet(storeArea.getAreaID().name());
			//head line
			HSSFRow head=sheet.createRow(0);
			HSSFCell cell=head.createCell(0);
			cell.setCellValue("行号");
			cell.setCellStyle(headStyle);
			cell=head.createCell(1);
			cell.setCellValue("货架号");
			cell.setCellStyle(headStyle);
			cell=head.createCell(2);
			cell.setCellValue("位置号");
			cell.setCellStyle(headStyle);
			cell=head.createCell(3);
			cell.setCellValue("订单编号");
			cell.setCellStyle(headStyle);
			//
			ArrayList<StoreLocation> storeLocation=storeArea.getList();
			for (int j = 1; j <= storeLocation.size(); j++) {
				StoreLocation location=storeLocation.get(j-1);
				HSSFRow row=sheet.createRow(j);
				cell=row.createCell(0);
				cell.setCellValue(location.getRow());
				cell.setCellStyle(dataStyle);
				cell=row.createCell(1);
				cell.setCellValue(location.getShelf());
				cell.setCellStyle(dataStyle);
				cell=row.createCell(2);
				cell.setCellValue(location.getPosition());
				cell.setCellStyle(dataStyle);
				cell=row.createCell(3);
				cell.setCellValue(location.getOrderID());
				cell.setCellStyle(dataStyle);
			}
		}
		try {
			FileOutputStream out=new FileOutputStream(new File("dump/dump.xls"));
			workbook.write(out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return new OperationMessage(false, e.getMessage());
		} catch (IOException e) {
			return new OperationMessage(false, e.getMessage());
		}
		//
		return ans;
	}
	
//	public static void main(String[] args){
//		StoreLocation o;
//		ArrayList<StoreLocation > li1=new ArrayList<StoreLocation>();
//		for (int i = 0; i < 30; i++) {
//			o=new StoreLocation(StoreAreaCode.AIR, i, i, i, "3453"+Integer.toString(i));
//			li1.add(o);
//		}
//		StoreArea s1=new StoreArea(StoreAreaCode.AIR, li1);
//		//
//		ArrayList<StoreLocation > li2=new ArrayList<StoreLocation>();
//		for (int i = 0; i < 30; i++) {
//			o=new StoreLocation(StoreAreaCode.FLEX, i, i, i, "3453"+Integer.toString(i));
//			li2.add(o);
//		}
//		StoreArea s2=new StoreArea(StoreAreaCode.FLEX, li2);
//		//
//		ArrayList<StoreLocation > li3=new ArrayList<StoreLocation>();
//		for (int i = 0; i < 30; i++) {
//			o=new StoreLocation(StoreAreaCode.RAIL, i, i, i, "3453"+Integer.toString(i));
//			li3.add(o);
//		}
//		StoreArea s3=new StoreArea(StoreAreaCode.RAIL, li3);
//		//
//		ArrayList<StoreLocation > li4=new ArrayList<StoreLocation>();
//		for (int i = 0; i < 30; i++) {
//			o=new StoreLocation(StoreAreaCode.ROAD, i, i, i, "3453"+Integer.toString(i));
//			li4.add(o);
//		}
//		StoreArea s4=new StoreArea(StoreAreaCode.ROAD, li4);
//		ArrayList<StoreArea > st=new ArrayList<StoreArea>(4);
//		st.add(s1);
//		st.add(s2);
//		st.add(s3);
//		st.add(s4);
//		Excel.exportToExcel("", st);
//	}
}
