/*
 * �������� 2006-11-11
 * 
 * TODO Ҫ��Ĵ���ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package cn.eshore.mismp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author JimmyLee
 *
 * TODO Ҫ��Ĵ���ɵ�����ע�͵�ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */

public class MD5 {
    /**
     * Encodes a string
     * 
     * @param str String to encode
     * @return Encoded String
     * @throws Exception
     */
    public static String crypt(String str)
    {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        
        StringBuffer hexString = new StringBuffer();
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                }               
                else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }               
            }
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return hexString.toString();
    }
}

