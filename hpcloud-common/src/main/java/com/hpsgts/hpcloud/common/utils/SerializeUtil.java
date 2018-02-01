package com.hpsgts.hpcloud.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * 对象序列化及反序列化
 * @author 黄朴（Herper.Plain）
 * @Date 2018/02/01 下午12:30
 */
public class SerializeUtil {

	private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 关闭此流并释放与此流关联的所有系统资源。如果已经关闭该流，则调用此方法无效
	 * 
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				logger.info("Unable to close " + closeable, e);
			}
		}
	}

	/**
	 * 反序列化举例：
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object unserialize(byte[] bytes) {

		ByteArrayInputStream bais = null;

		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);

			ObjectInputStream ois = new ObjectInputStream(bais);

			return ois.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对象序列化
	 * 
	 * @param value
	 * @return byte[]
	 */
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (Exception e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	/**
	 * 对象反序列化
	 * 
	 * @param in
	 * @return
	 */
	public static Object deserialize(byte[] in) {
		if (in == null) {
			throw new NullPointerException("Can't deserialize null");
		}
		Object result = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				result = is.readObject();
				is.close();
				bis.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("对象反序列化异常:" + e);
		} finally {
			close(is);
			close(bis);
		}
		return result;
	}

	/**
	 * 序列化List对象
	 * 
	 * @param <T>
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> byte[] serializeList(List<T> value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		List<Object> values = (List<Object>) value;

		byte[] results = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;

		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			for (Object m : values) {
				os.writeObject(m);
			}
			os.close();
			bos.close();
			results = bos.toByteArray();
		} catch (IOException e) {
			throw new IllegalArgumentException("Non-serializable object", e);
		} finally {
			close(os);
			close(bos);
		}
		return results;
	}

	/**
	 * 反序列化List对象
	 * 
	 * @param <T>
	 * @param in
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> deserializeList(byte[] in) {
		List<T> list = new ArrayList<T>();
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				while (true) {
					T m = (T) is.readObject();
					if (m == null) {
						break;
					}
					list.add(m);
				}
				is.close();
				bis.close();
			}
		} catch (Exception e) {
			throw new RuntimeException("反序列化异常:" + e);
		} finally {
			close(is);
			close(bis);
		}
		return list;
	}

	/**
	 * 序列化Map对象
	 * 
	 * @param hash
	 * @return Map<byte[], byte[]>
	 */
	public static Map<byte[], byte[]> serializehmoo2mbb(Map<Object, Object> hash) {
		Map<byte[], byte[]> result = new HashMap<byte[], byte[]>();
		try {
			Set<Object> keys = hash.keySet();
			if (keys != null && keys.size() > 0) {
				for (Object key : keys) {
					result.put(serialize(key), serialize(hash.get(key)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 反序列化Map对象
	 * 
	 * @param hash
	 * @return Map<Object, Object>
	 */
	public static Map<Object, Object> unserializehmbb2moo(final Map<byte[], byte[]> hash) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		try {
			Set<byte[]> keys = hash.keySet();
			if (keys != null && keys.size() > 0) {
				for (byte[] key : keys) {
					result.put(deserialize(key), deserialize(hash.get(key)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
