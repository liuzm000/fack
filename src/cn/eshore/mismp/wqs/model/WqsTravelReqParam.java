/*    */ package cn.eshore.mismp.wqs.model;
		/**
		 * 
		 * <p> 客户端请求参数封装类<p>
		 * <p> @author jianghuan <p>
		 * <p> 时间：2012-12-18下午03:22:04<p>
		 * <p> CopyRight 2012 <p>
		 */
/*    */ public class WqsTravelReqParam
/*    */ {
/*    */   private String imsi;
/*    */   private String esn;
/*    */   private String category;
/*    */   private String phone;
/*    */   private String page;
/*    */   private String pagesize;
/*    */   private String typeId;
/*    */   private String phoneNumber;   
		   private String hotelid;
		   private String orderid;
		   private String key;
/*    */   public String getImsi()
/*    */   {
/* 21 */     return this.imsi;
/*    */   }
/*    */   public void setImsi(String imsi) {
/* 24 */     this.imsi = imsi;
/*    */   }
/*    */   public String getEsn() {
/* 27 */     return this.esn;
/*    */   }
/*    */   public void setEsn(String esn) {
/* 30 */     this.esn = esn;
/*    */   }
/*    */   public String getCategory() {
/* 33 */     return this.category;
/*    */   }
/*    */   public void setCategory(String category) {
/* 36 */     this.category = category;
/*    */   }
/*    */   public String getPhone() {
/* 39 */     return this.phone;
/*    */   }
/*    */   public void setPhone(String phone) {
/* 42 */     this.phone = phone;
/*    */   }
/*    */   public String getPage() {
/* 45 */     return this.page;
/*    */   }
/*    */   public void setPage(String page) {
/* 48 */     this.page = page;
/*    */   }
/*    */   public String getPagesize() {
/* 51 */     return this.pagesize;
/*    */   }
/*    */   public void setPagesize(String pagesize) {
/* 54 */     this.pagesize = pagesize;
/*    */   }
		   
			
			public String getOrderid() {
	return orderid;
}
public void setOrderid(String orderid) {
	this.orderid = orderid;
}
			public String getTypeId() {
				return typeId;
			}
			public void setTypeId(String typeId) {
				this.typeId = typeId;
			}
			public String getPhoneNumber() {
				return phoneNumber;
			}
			public void setPhoneNumber(String phoneNumber) {
				this.phoneNumber = phoneNumber;
			}
			
			
			public String getKey() {
				return key;
			}
			public void setKey(String key) {
				this.key = key;
			}
			public String getHotelid() {
				return hotelid;
			}
			public void setHotelid(String hotelid) {
				this.hotelid = hotelid;
			}
/*    */ 
/*    */   public boolean isValid() {
/* 64 */     boolean isValid = true;
/* 65 */     return isValid;
/*    */   }
/*    */ 
/*    */   public String toString() {
/* 69 */     StringBuffer strBuffer = new StringBuffer();
/* 70 */     strBuffer.append("imsi=").append(this.imsi)
/* 71 */       .append("&esn=").append(this.esn)
/* 72 */       .append("&category=").append(this.category)
/* 73 */       .append("&phone=").append(this.phone)
/* 74 */       .append("&page=").append(this.page)
/* 75 */       .append("&pagesize=").append(this.pagesize)
/* 76 */       .append("&typeid=").append(this.typeId)
			   .append("&phoneNumber=").append(this.phoneNumber)
			   .append("&hotelid=").append(this.hotelid);
/* 77 */     return strBuffer.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\wanglei\Desktop\nstravel\WEB-INF\classes\
 * Qualified Name:     cn.eshore.mismp.iface.model.NsTravelReqParam
 * JD-Core Version:    0.6.0
 */