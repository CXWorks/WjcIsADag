package po.orderdata;

import java.io.Serializable;

import po.FormPO;


public class OrderPO extends FormPO implements Serializable{
  private String nameFrom;
  private String nameTo;
  private String location;
  private String loacal;
  private String unitFrom;
  private String unitTo;
  private String phoneNumFrom;
  private String phoneNumTo;
  private String telNumFrom;
  private String telNumTo;
  private String goodsNum;
  private String goodsName;
  private String weight;
  private String volume;
  private String money;
  private TypeEnum type;
}
