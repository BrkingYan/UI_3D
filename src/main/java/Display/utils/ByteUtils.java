package Display.utils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Byte转换工具
 * 
 * @author yangle
 */
public class ByteUtils {

	/**
	 * 十六进制字符串转byte[]
	 * 
	 * @param hex
	 *            十六进制字符串
	 * @return byte[]
	 */
	public static byte[] hexStr2Byte(String hex) {
		if (hex == null) {
			return new byte[] {};
		}

		// 奇数位补0
		if (hex.length() % 2 != 0) {
			hex = "0" + hex;
		}

		int length = hex.length();
		ByteBuffer buffer = ByteBuffer.allocate(length / 2);
		for (int i = 0; i < length; i++) {
			String hexStr = hex.charAt(i) + "";
			i++;
			hexStr += hex.charAt(i);
			byte b = (byte) Integer.parseInt(hexStr, 16);
			buffer.put(b);
		}
		return buffer.array();
	}

	/**
	 * byte[]转十六进制字符串
	 * 
	 * @param array
	 *            byte[]
	 * @return 十六进制字符串
	 */
	public static String byteArrayToHexString(byte[] array) {
		if (array == null) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(byteToHex(array[i]));
		}
		return buffer.toString();
	}

	/**
	 * byte转十六进制字符
	 * 
	 * @param b
	 *            byte
	 * @return 十六进制字符
	 */
	public static String byteToHex(byte b) {
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex.toUpperCase(Locale.getDefault());
	}

	public static List<Float> bytesToFloat(List<Integer> list){
		List<Float> res = new ArrayList<Float>();
		for (int i = 1;i<list.size();i+=2){
			if (i+1 >= list.size()){
				break;
			}
			int a = list.get(i);
			int b = list.get(i+1);
			float ret = a * 256 + b;
			if (ret > 32767){
				ret -= 65536;
			}
			ret /= 100;
			res.add(ret);
		}
		return res;
	}

	public static List<Float> bytesToFloat_Degree(List<Integer> list){
		List<Float> res = new ArrayList<Float>();
		for (int i = 1;i<list.size();i++){
			int a = list.get(i);
			float aa = (float)a;
			if (aa > 128){
				aa -= 256;
			}
			aa /= 100;
			res.add(aa);
		}
		return res;
	}

	/*private static void concatHex(byte num1,byte num2){
		byte hex1 =
	}*/
}
