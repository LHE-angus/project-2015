package report.domain;

import java.io.Serializable;

/**
 * 获取可在指定类别中使用的表列的描述。 仅返回与类别、模式、表和列名称标准匹配的列描述。它们根据 TABLE_CAT、TABLE_SCHEM、TABLE_NAME 和 ORDINAL_POSITION 进行排序。
 * 
 * @author Liu,ZhiXiang
 * @version builder 2013.9.2
 */
public class PrimaryKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String table_cat;// 表类别（可为 null）

	private String table_schem;// 表模式（可为 null）

	private String table_name;// 表名称

	private String column_name;// 列名称

	private Integer key_seq;// 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）

	private String pk_name;// 主键的名称（可为 null）

	public String getTable_cat() {
		return table_cat;
	}

	public void setTable_cat(String table_cat) {
		this.table_cat = table_cat;
	}

	public String getTable_schem() {
		return table_schem;
	}

	public void setTable_schem(String table_schem) {
		this.table_schem = table_schem;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public Integer getKey_seq() {
		return key_seq;
	}

	public void setKey_seq(Integer key_seq) {
		this.key_seq = key_seq;
	}

	public String getPk_name() {
		return pk_name;
	}

	public void setPk_name(String pk_name) {
		this.pk_name = pk_name;
	}

}
