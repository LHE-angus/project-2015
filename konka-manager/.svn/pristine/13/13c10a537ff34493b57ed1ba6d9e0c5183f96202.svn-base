package report.domain;

import java.io.Serializable;

/**
 * 获取可在指定类别中使用的表列的描述。 仅返回与类别、模式、表和列名称标准匹配的列描述。它们根据 TABLE_CAT、TABLE_SCHEM、TABLE_NAME 和 ORDINAL_POSITION 进行排序。
 * 
 * @author Liu,ZhiXiang
 * @version builder 2013.9.2
 */
public class ForeignKey implements Serializable {

	private static final long serialVersionUID = 1L;

	private String pk_table_cat;// 主键表类别（可为 null）

	private String pk_table_schem;// 主键表模式（可为 null）

	private String pk_table_name;// 主键表名称

	private String pk_column_name;// 主键表列名称

	private String fk_table_cat;// 外键表类别（可为 null）

	private String fk_table_schem;// 外键表模式（可为 null）

	private String fk_table_name;// 外键表名称

	private String fk_column_name;// 外键表列名称

	private Integer key_seq;// 外键中的序列号（值 1 表示外键中的第一列，值 2 表示外键中的第二列）

	private String pk_name;// 主键的名称（可为 null）

	private String fk_name;// 外键的名称（可为 null）

	public String getPk_table_cat() {
		return pk_table_cat;
	}

	public void setPk_table_cat(String pk_table_cat) {
		this.pk_table_cat = pk_table_cat;
	}

	public String getPk_table_schem() {
		return pk_table_schem;
	}

	public void setPk_table_schem(String pk_table_schem) {
		this.pk_table_schem = pk_table_schem;
	}

	public String getPk_table_name() {
		return pk_table_name;
	}

	public void setPk_table_name(String pk_table_name) {
		this.pk_table_name = pk_table_name;
	}

	public String getPk_column_name() {
		return pk_column_name;
	}

	public void setPk_column_name(String pk_column_name) {
		this.pk_column_name = pk_column_name;
	}

	public String getFk_table_cat() {
		return fk_table_cat;
	}

	public void setFk_table_cat(String fk_table_cat) {
		this.fk_table_cat = fk_table_cat;
	}

	public String getFk_table_schem() {
		return fk_table_schem;
	}

	public void setFk_table_schem(String fk_table_schem) {
		this.fk_table_schem = fk_table_schem;
	}

	public String getFk_table_name() {
		return fk_table_name;
	}

	public void setFk_table_name(String fk_table_name) {
		this.fk_table_name = fk_table_name;
	}

	public String getFk_column_name() {
		return fk_column_name;
	}

	public void setFk_column_name(String fk_column_name) {
		this.fk_column_name = fk_column_name;
	}

	public String getFk_name() {
		return fk_name;
	}

	public void setFk_name(String fk_name) {
		this.fk_name = fk_name;
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
