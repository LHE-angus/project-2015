package com.ebiz.mmt.web.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

public class BeanToMapUtil {

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Object convertMap(Class type, Map map) throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				try {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					descriptor.getWriteMethod().invoke(obj, args);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return obj;
	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 */
	public static Map convertBean(Object bean) throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	/**
	 * 根据导入的Excel获取类相对应的的list<br/>
	 * Map<Object, String> map：中文字段对应在Excel中的第几列，存入map中<br/>
	 * Class type：对象的类型，如JBaseGoodsInitStock.class即可<br/>
	 * File file：file文件，如果知道路径的话则new File(fileSavePath)即可 <br/>
	 * int startrow：Excel中的有效数据（汉字）是从第几行开始的，即Excel最左边对应的数字<br/>
	 * 注意：此有效数据第一列为序号，第二列开始就是有效数据了<br/>
	 * 示例如下：<br/>
	 * Map<Object, String> map = new HashMap<Object, String>(); <br/>
	 * map.put(1, "store_id"); <br/>
	 * map.put(2, "goods_id");
	 * map.put(3, "init_count"); <br/>
	 * map.put(4, "buy_price");
	 * map.put(5, "init_desc"); <br/>
	 * map.put(6, "init_date"); <br/>
	 * List<\Object\> list = BeanToMapUtil.getExcelList(map, JBaseGoodsInitStock.class, new File(fileSavePath), 1); <br/>
	 * if (null != list) { <br/>
	 * 	  for (int i = 0; i < list.size(); i++) { <br/>
	 * 		 JBaseGoodsInitStock s = (JBaseGoodsInitStock) list.get(i); <br/>
	 * 			//System.out.println("store_id="+s.getStore_id()+"goods_id="+s.getGoods_id()+"init_count=" <br/>
	 * 				+s.getInit_count()+"buy_price="+s.getBuy_price()+"init_desc="+s.getInit_desc() <br/>
	 * 				+"init_date="+s.getInit_date()); <br/>
	 * 	  } <br/>
	 * } <br/>
	 * @param map
	 * @param type
	 * @param file
	 * @param startrow
	 * @return List<Object>
	 * @author Xiao,GuoJian
	 */
	public static List<Object> getExcelList(Map<Object, String> map, Class type, File file, int startrow) {
		try {
			String[][] arrays = ExcelUtil.getExcelData(file, startrow);
			if (null != arrays) {
				List<Object> entityList = new ArrayList<Object>();

				// for (int i = 0; i < arrays.length; i++) {// 遍历每行
				// for (int j = 1; j <= map.size(); j++) {// 遍历
				// //System.out.print("    array[" + i + "][" + j + "]=" +
				// arrays[i][j]);
				// }
				// //System.out.println();
				// }

				for (int i = 0; i < arrays.length; i++) {// 遍历每行
					Map<Object, Object> map1 = new HashMap<Object, Object>();
					for (int j = 1; j <= map.size(); j++) {// 遍历每行的每个cell
						map1.put(map.get(j), arrays[i][j]);
					}
					if (null != map1) {
						// entity = util.convertMap(o.getClass(), map1);
						BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
						Object obj = type.newInstance(); // 创建 JavaBean对象

						// 给 JavaBean 对象的属性赋值
						PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
						for (int m = 0; m < propertyDescriptors.length; m++) {
							PropertyDescriptor descriptor = propertyDescriptors[m];
							String propertyName = descriptor.getName();
							Class<?> propertyType = descriptor.getPropertyType();

							if (map1.containsKey(propertyName)) {
								// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
								try {
									// 判断属性的类型
									if (null != map1.get(propertyName) && !"".equals(map1.get(propertyName))) {
										try {
											if (propertyType == String.class) {// 如果是字符串类型的不处理

											} else if (propertyType == Integer.class) {// 如果是Integer
												map1.put(propertyName, Integer.parseInt(map1.get(propertyName) + ""));
											} else if (propertyType == int.class) {// 如果是int
												map1.put(propertyName, Integer.parseInt(map1.get(propertyName) + ""));
											} else if (propertyType == Long.class) {// 如果是Long
												map1.put(propertyName, Long.parseLong(map1.get(propertyName) + ""));
											} else if (propertyType == long.class) {// 如果是long
												map1.put(propertyName, Long.parseLong(map1.get(propertyName) + ""));
											} else if (propertyType == BigDecimal.class) {// 如果是BigDecimal
												map1.put(propertyName, new BigDecimal((String) map1.get(propertyName)));
											} else if (propertyType == Date.class) {// 如果是Date
												map1.put(propertyName, DateUtils.parseDate(map1.get(propertyName)
														.toString(), new String[] { "yyyy-MM-dd" }));
											}
										} catch (Exception e) {
											map1.put(propertyName, null);
											e.printStackTrace();
										}
									} else {
										map1.put(propertyName, null);
									}

									Object value = map1.get(propertyName);
									Object[] args = new Object[1];
									args[0] = value;

									descriptor.getWriteMethod().invoke(obj, args);
								} catch (IllegalAccessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						entityList.add(obj);
					}
				}
				return entityList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}
