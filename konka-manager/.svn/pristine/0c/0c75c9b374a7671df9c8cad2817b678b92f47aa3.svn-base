package report.dao.db2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import report.domain.Column;
import report.domain.EnumEntity;
import report.domain.FiltersBean;
import report.domain.ForeignKey;
import report.domain.PrimaryKey;

/**
 * @author Liu,ZhiXiang
 * @version 2013-09-05
 */
public class ColumnsForDb2 {

	public static List<Column> selectColumnList(String tableName) throws SQLException {
		if (StringUtils.isBlank(tableName)) {
			throw new NullPointerException("Table name for searching column must not be empty, please check yourself!");
		}

		// String driver = "com.ibm.db2.jcc.DB2Driver";
		// String url = "jdbc:db2://220.178.14.98:50000/devdb";
		// String username = "konka";
		// String password = "konka";

		String driver = ResourceBundle.getBundle("jdbc").getString("jdbc.driverClassName");
		String url = ResourceBundle.getBundle("jdbc").getString("jdbc.url");
		String username = ResourceBundle.getBundle("jdbc").getString("jdbc.username");
		String password = ResourceBundle.getBundle("jdbc").getString("jdbc.password");

		Connection conn = null;
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url, username, password);
			//System.out.println("获得数据连接！");
		} catch (Exception e) {
			System.err.println("数据库驱动异常！");
			e.printStackTrace();
		}

		List<Column> columnList = new ArrayList<Column>();
		List<PrimaryKey> primaryKeyList = new ArrayList<PrimaryKey>();
		List<ForeignKey> foreignKeyList = new ArrayList<ForeignKey>();

		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet rs = metaData.getColumns(null, username.toUpperCase(), tableName, null);

		// 表主键
		ResultSet primaryKeyResultSet = metaData.getPrimaryKeys(null, username.toUpperCase(), tableName);
		while (primaryKeyResultSet.next()) {
			PrimaryKey primaryKey = new PrimaryKey();
			primaryKey.setTable_name(primaryKeyResultSet.getString("TABLE_NAME"));
			primaryKey.setColumn_name(primaryKeyResultSet.getString("COLUMN_NAME"));
			// primaryKey.setKey_seq(primaryKeyResultSet.getInt("KEY_SEQ "));
			primaryKey.setPk_name(primaryKeyResultSet.getString("PK_NAME"));
			primaryKeyList.add(primaryKey);
		}
		// 表外键
		ResultSet foreignKeyResultSet = metaData.getImportedKeys(null, username.toUpperCase(), tableName);
		while (foreignKeyResultSet.next()) {
			ForeignKey foreignKey = new ForeignKey();
			foreignKey.setPk_table_name(foreignKeyResultSet.getString("PKTABLE_NAME"));
			foreignKey.setPk_column_name(foreignKeyResultSet.getString("PKCOLUMN_NAME"));
			foreignKey.setFk_table_name(foreignKeyResultSet.getString("FKTABLE_NAME"));
			foreignKey.setFk_column_name(foreignKeyResultSet.getString("FKCOLUMN_NAM"));
			// foreignKey.setKey_seq(foreignKeyResultSet.getInt("KEY_SEQ"));
			foreignKey.setFk_name(foreignKeyResultSet.getString("FK_NAME"));
			foreignKeyList.add(foreignKey);
		}

		// 表结构
		while (rs.next()) {
			Column column = new Column();
			column.setTable_name(rs.getString("TABLE_NAME"));
			column.setColumn_name(rs.getString("COLUMN_NAME"));
			column.setData_type(rs.getInt("DATA_TYPE"));
			column.setType_name(rs.getString("TYPE_NAME"));
			column.setColumn_size(rs.getInt("COLUMN_SIZE"));
			column.setDecimal_digits(rs.getInt("DECIMAL_DIGITS"));
			column.setNum_prec_radix(rs.getInt("NUM_PREC_RADIX"));
			column.setRemarks(rs.getString("REMARKS"));
			column.setColumn_def(rs.getString("COLUMN_DEF"));
			column.setIs_nullable(rs.getString("IS_NULLABLE"));

			// 判断是否为主键
			column.setIsPrimaryKey(false);
			if (null != primaryKeyList && primaryKeyList.size() > 0) {
				for (int i = 0; i < primaryKeyList.size(); i++) {
					PrimaryKey p = primaryKeyList.get(i);
					if (column.getColumn_name().equals(p.getColumn_name())) {
						column.setIsPrimaryKey(true);
						break;
					}
				}
			}
			// 判断是否为外键
			column.setIsForeignKey(false);
			if (null != foreignKeyList && foreignKeyList.size() > 0) {
				for (int i = 0; i < foreignKeyList.size(); i++) {
					ForeignKey f = foreignKeyList.get(i);
					if (column.getColumn_name().equals(f.getPk_column_name())) {
						column.setIsForeignKey(true);
						break;
					}
				}
			}
			// column.setIsPrimaryKey(rs.getBoolean("IS_PRIMARY_KEY"));

			// is_select 是否作为查询条件;0:否,1:是
			// select_type 查询条件类型;1:数字,2:日期/时间,3:字符串,4:枚举

			if (!column.getIsPrimaryKey() && !column.getIsForeignKey()) {

				if (StringUtils.isNotBlank(column.getRemarks())) {
					// 有备注comment
					// 数据库字段备注约定：字段名称|字段描述[:定义值1-定义值1描述,定义值2-定义值2描述,...]
					String[] remarks = column.getRemarks().split("\\|");
					column.setChina_column_name(remarks[0]);
					if (remarks.length == 2 && remarks[1].contains("[:") && remarks[1].contains("]")) {
						int start_index = remarks[1].indexOf("[");
						int end_index = remarks[1].indexOf("]");
						List<EnumEntity> enumEntityList = new ArrayList<EnumEntity>();
						String enum_string = remarks[1].substring(start_index + 2, end_index);
						if (StringUtils.isNotBlank(enum_string)) {
							String[] array_1 = enum_string.split(",");
							Boolean flag = false;
							if (null != array_1 && array_1.length > 1) {
								for (int i = 0; i < array_1.length; i++) {
									String[] array_2 = array_1[i].split("-");
									if (null != array_2 && array_2.length == 2) {
										EnumEntity e = new EnumEntity();
										e.setIndex(array_2[0]);
										e.setName(array_2[1]);
										enumEntityList.add(e);
										flag = true;
									} else {
										flag = false;
										break;
									}
								}
							}

							if (flag) {
								column.setIs_select(1);
								column.setSelect_type(4);
								column.setEnumEntityList(enumEntityList);
							}
						}
					}

				} else {
					// 无备注comment
					column.setChina_column_name(column.getColumn_name());
				}
				if ("BIGINT".equals(column.getType_name())) {
					column.setIs_select(1);
					if (null == column.getSelect_type() || column.getSelect_type() != 4) {
						column.setSelect_type(1);
					}
				} else if ("SMALLINT".equals(column.getType_name())) {
					column.setIs_select(1);
					if (null == column.getSelect_type() || column.getSelect_type() != 4) {
						column.setSelect_type(1);
					}
				} else if ("DECIMAL".equals(column.getType_name())) {
					column.setIs_select(1);
					if (null == column.getSelect_type() || column.getSelect_type() != 4) {
						column.setSelect_type(1);
					}
				} else if ("TIMESTAMP".equals(column.getType_name())) {
					column.setIs_select(1);
					column.setSelect_type(2);
				} else if ("VARCHAR".equals(column.getType_name())) {
					column.setIs_select(1);
					if (null == column.getSelect_type() || column.getSelect_type() != 4) {
						column.setSelect_type(3);
					}
				} else if ("CLOB".equals(column.getType_name())) {
					column.setIs_select(0);
				} else if ("BLOB".equals(column.getType_name())) {
					column.setIs_select(0);
				}
			} else {
				column.setIs_select(0);
			}
			if (null != column.getIs_select() && column.getIs_select() == 1) {
				columnList.add(column);
			}

			//System.out.println(column.toString());
		}
		rs.close();
		return columnList;
	}

	public static FiltersBean getReportForResult(FiltersBean f) throws SQLException {
		String sql = "";
		List<String> params = new ArrayList<String>();
		String select = " select ";
		String from = " from " + f.getTable_name();
		String where = " where 1 = 1 ";
		String group = "";
		String order = "";

		List<Column> columnList = selectColumnList(f.getTable_name());
		List<String> diplay_name = new ArrayList<String>();// 页面显示字段名称

		String[] filter_params = f.getFilter_params();// 过滤器名称
		String[] filter_operators = f.getFilter_operators();// 过滤器运算符
		String[] filter_values = f.getFilter_values();// 过滤器值域
		String[] filter_types = f.getFilter_types();// 过滤器数据类型
		if (filter_params != null && filter_params.length > 0 && filter_operators != null
				&& filter_operators.length > 0 && filter_values != null && filter_values.length > 0
				&& filter_types != null && filter_types.length > 0 && filter_params.length == filter_operators.length
				&& filter_params.length == filter_values.length && filter_params.length == filter_types.length) {

			for (int i = 0; i < filter_params.length; i++) {
				if (StringUtils.isNotBlank(filter_params[i]) && StringUtils.isNotBlank(filter_operators[i])
						&& StringUtils.isNotBlank(filter_values[i]) && StringUtils.isNotBlank(filter_types[i])) {
					if (filter_operators[i].equals("1")) {
						if (filter_types[i].equals("1")) {
							where += " and " + filter_params[i] + " = ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("2")) {

						} else if (filter_types[i].equals("3")) {
							where += " and " + filter_params[i] + " = ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("4")) {
							where += " and " + filter_params[i] + " = ? ";
							params.add(filter_values[i]);
						}
					} else if (filter_operators[i].equals("2")) {
						if (filter_types[i].equals("1")) {
							where += " and " + filter_params[i] + " != ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("2")) {

						} else if (filter_types[i].equals("3")) {
							where += " and " + filter_params[i] + " != ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("4")) {
							where += " and " + filter_params[i] + " != ? ";
							params.add(filter_values[i]);
						}
					} else if (filter_operators[i].equals("3")) {
						if (filter_types[i].equals("1")) {
							where += " and " + filter_params[i] + " > ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("2")) {
							where += " and " + filter_params[i] + " > to_date(?,'yyyy-MM-dd') ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("3")) {

						} else if (filter_types[i].equals("4")) {

						}
					} else if (filter_operators[i].equals("4")) {
						if (filter_types[i].equals("1")) {
							where += " and " + filter_params[i] + " >= ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("2")) {

						} else if (filter_types[i].equals("3")) {

						} else if (filter_types[i].equals("4")) {

						}
					} else if (filter_operators[i].equals("5")) {
						if (filter_types[i].equals("1")) {
							where += " and " + filter_params[i] + " < ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("2")) {
							where += " and " + filter_params[i] + " < to_date(?,'yyyy-MM-dd') ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("3")) {

						} else if (filter_types[i].equals("4")) {

						}
					} else if (filter_operators[i].equals("6")) {
						if (filter_types[i].equals("1")) {
							where += " and " + filter_params[i] + " <= ? ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("2")) {

						} else if (filter_types[i].equals("3")) {

						} else if (filter_types[i].equals("4")) {

						}
					} else if (filter_operators[i].equals("7")) {
						if (filter_types[i].equals("1")) {

						} else if (filter_types[i].equals("2")) {

						} else if (filter_types[i].equals("3")) {
							where += " and " + filter_params[i] + " like '%' ||?|| '%' ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("4")) {

						}
					} else if (filter_operators[i].equals("8")) {
						if (filter_types[i].equals("1")) {

						} else if (filter_types[i].equals("2")) {

						} else if (filter_types[i].equals("3")) {
							where += " and " + filter_params[i] + " not like '%' ||?|| '%' ";
							params.add(filter_values[i]);
						} else if (filter_types[i].equals("4")) {

						}
					}

				}
			}
		}

		String[] group_params = f.getGroup_params();
		if (f.getFilter_group_flag() != null && f.getFilter_group_flag().length == 1
				&& "1".equals(f.getFilter_group_flag()[0])) {
			// 分组
			// group += " group by ";

			if (null != group_params && group_params.length > 0) {
				for (int i = 0; i < group_params.length; i++) {
					if (StringUtils.isNotBlank(group_params[i])) {

						group += group_params[i] + ",";

						// 配置页面显示字段
						if (null != columnList && columnList.size() > 0) {
							for (int j = 0; j < columnList.size(); j++) {
								Column column = columnList.get(j);
								if (column.getColumn_name().equals(group_params[i])) {
									diplay_name.add(column.getChina_column_name());
									if (column.getSelect_type() == 4) {
										// 枚举类型
										if (null != column.getEnumEntityList() && column.getEnumEntityList().size() > 0) {
											select += "decode(" + group_params[i];
											for (int k = 0; k < column.getEnumEntityList().size(); k++) {
												EnumEntity e = column.getEnumEntityList().get(k);
												select += "," + e.getIndex() + ",'" + e.getName() + "'";
											}
											select += ",'无此类别'),";
										}
									} else if (column.getSelect_type() == 2) {
										// 时间格式
										select += "to_char(" + group_params[i] + ",'yyyy-MM-dd')" + ",";
									} else {
										select += group_params[i] + ",";
									}

									break;
								}
							}
						} else {
							select += group_params[i] + ",";
							diplay_name.add(group_params[i]);
						}

					}
				}
				String[] group_out_params = f.getGroup_out_params();
				String[] group_out_operators = f.getGroup_out_operators();
				if (null != group_out_params && group_out_params.length > 0 && null != group_out_operators
						&& group_out_operators.length > 0 && group_out_params.length == group_out_operators.length) {
					for (int i = 0; i < group_out_params.length; i++) {
						if (StringUtils.isNotBlank(group_out_params[i])
								&& StringUtils.isNotBlank(group_out_operators[i])) {
							select += group_out_operators[i] + "(" + group_out_params[i] + "),";

							// 配置页面显示字段
							String operator = "";
							if (group_out_operators[i].equals("sum")) {
								operator = "合计";
							} else if (group_out_operators[i].equals("avg")) {
								operator = "平均值";
							} else if (group_out_operators[i].equals("max")) {
								operator = "最大值";
							} else if (group_out_operators[i].equals("min")) {
								operator = "最小值";
							}
							if (null != columnList && columnList.size() > 0) {
								for (int j = 0; j < columnList.size(); j++) {
									Column column = columnList.get(j);
									if (column.getColumn_name().equals(group_out_params[i])) {
										diplay_name.add(column.getChina_column_name() + operator);
										break;
									}
								}
							} else {
								diplay_name.add(group_out_params[i] + operator);
							}
						}
					}
				}
				f.setError("0");
			} else {
				f.setError("1");
			}
		} else {
			// 不分组
			String[] out_params = f.getOut_params();
			if (null != out_params && out_params.length > 0) {
				for (int i = 0; i < out_params.length; i++) {
					if (StringUtils.isNotBlank(out_params[i])) {

						// 配置页面显示字段
						if (null != columnList && columnList.size() > 0) {
							for (int j = 0; j < columnList.size(); j++) {
								Column column = columnList.get(j);
								if (column.getColumn_name().equals(out_params[i])) {
									diplay_name.add(column.getChina_column_name());
									if (column.getSelect_type() == 4) {
										// 枚举类型
										if (null != column.getEnumEntityList() && column.getEnumEntityList().size() > 0) {
											select += "decode(" + out_params[i];
											for (int k = 0; k < column.getEnumEntityList().size(); k++) {
												EnumEntity e = column.getEnumEntityList().get(k);
												select += "," + e.getIndex() + ",'" + e.getName() + "'";
											}
											select += ",'无此类别'),";
										}
									} else if (column.getSelect_type() == 2) {
										// 时间格式
										select += "to_char(" + out_params[i] + ",'yyyy-MM-dd')" + ",";
									} else {
										select += out_params[i] + ",";
									}

									break;
								}
							}
						} else {
							select += out_params[i] + ",";
							diplay_name.add(out_params[i]);
						}
					}
				}
				f.setError("0");
			} else {
				f.setError("1");
			}
		}

		// 排序
		String[] order_params = f.getOrder_params();
		String[] order_values = f.getOrder_values();
		if (null != order_params && order_params.length > 0 && null != order_values && order_values.length > 0
				&& order_params.length == order_values.length) {
			// order += " order by ";

			for (int i = 0; i < order_params.length; i++) {
				if (StringUtils.isNotBlank(order_params[i]) && StringUtils.isNotBlank(order_values[i])) {

					if (null != group_params && group_params.length > 0) {
						// 分组group by 查询，排序必须在分组的范围内
						for (int j = 0; j < group_params.length; j++) {
							if (group_params[j].equals(order_params[i])) {
								order += order_params[i] + " " + order_values[i] + ",";
								break;
							}
						}
					} else {
						order += order_params[i] + " " + order_values[i] + ",";
					}

				}
			}
		}
		if (StringUtils.isNotBlank(group)) {
			group = " group by " + group;
		}
		if (StringUtils.isNotBlank(order)) {
			order = " order by " + order;
		}
		select = StringUtils.removeEnd(select, ",");
		group = StringUtils.removeEnd(group, ",");
		order = StringUtils.removeEnd(order, ",");
		sql = select + from + where + group + order;
		f.setSql(sql);
		f.setParams(params);
		f.setDiplay_name(diplay_name);

		//System.out.println("**********sql:" + sql);
		if (null != params && params.size() > 0) {
			String params_string = "";
			for (int i = 0; i < params.size(); i++) {
				params_string += params.get(i) + ",";
			}
			//System.out.println("**********params:" + params.size());
			//System.out.println("**********params_string:" + params_string);
		}
		if (null != diplay_name && diplay_name.size() > 0) {
			String diplay_name_string = "";
			for (int i = 0; i < diplay_name.size(); i++) {
				diplay_name_string += diplay_name.get(i) + ",";
			}
			//System.out.println("**********diplay_name:" + diplay_name.size());
			//System.out.println("**********diplay_name_string:" + diplay_name_string);
		}
		return f;
	}
}
