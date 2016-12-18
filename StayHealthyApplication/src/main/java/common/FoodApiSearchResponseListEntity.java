package common;

import java.util.List;

public class FoodApiSearchResponseListEntity {
	public String q;
	public String sr;
	public String ds;
	public int start;
	public int end;
	public int total;
	public String group;
	public String sort;
	public List<FoodApiSearchResponseListItemEntity> item;
}
