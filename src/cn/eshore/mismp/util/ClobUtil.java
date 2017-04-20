/*    */ package cn.eshore.mismp.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.io.Reader;
/*    */ import java.sql.Clob;
/*    */ import javax.sql.rowset.serial.SerialClob;
/*    */ 
/*    */ public class ClobUtil
/*    */ {
/*    */   public static Clob stringToClob(String str)
/*    */   {
/* 15 */     if (str == null)
/* 16 */       return null;
/*    */     try
/*    */     {
/* 19 */       Clob c = new SerialClob(str.toCharArray());
/* 20 */       return c; } catch (Exception e) {
/*    */     }
/* 22 */     return null;
/*    */   }
/*    */ 
/*    */   public static String clobToString(Clob clob)
/*    */   {
/* 28 */     if (clob == null) {
/* 29 */       return null;
/*    */     }
/* 31 */     StringBuffer sb = new StringBuffer(65535);
/* 32 */     Reader clobStream = null;
/*    */     try {
/* 34 */       clobStream = clob.getCharacterStream();
/* 35 */       char[] b = new char[60000];
/* 36 */       int i = 0;
/* 37 */       while ((i = clobStream.read(b)) != -1)
/* 38 */         sb.append(b, 0, i);
/*    */     }
/*    */     catch (Exception ex) {
/* 41 */       sb = null;
/*    */       try
/*    */       {
/* 44 */         if (clobStream != null)
/* 45 */           clobStream.close();
/*    */       }
/*    */       catch (Exception localException1)
/*    */       {
/*    */       }
/*    */     }
/*    */     finally
/*    */     {
/*    */       try
/*    */       {
/* 44 */         if (clobStream != null)
/* 45 */           clobStream.close();
/*    */       }
/*    */       catch (Exception localException2) {
/*    */       }
/*    */     }
/* 50 */     if (sb == null) {
/* 51 */       return null;
/*    */     }
/* 53 */     return sb.toString();
/*    */   }
/*    */   public static void main(String[] args) {
/* 56 */     String test = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
/*    */ 
/* 58 */     int len = test.length();
/* 59 */     Clob clob = stringToClob(test);
/* 60 */     String reply = clobToString(clob);
/* 61 */     System.out.println("len: " + len + "\nreply: " + reply + "\nreply_len: " + reply.length());
/*    */   }
/*    */ }

/* Location:           C:\Users\wanglei\Desktop\nstravel\WEB-INF\classes\
 * Qualified Name:     cn.eshore.mismp.util.ClobUtil
 * JD-Core Version:    0.6.0
 */