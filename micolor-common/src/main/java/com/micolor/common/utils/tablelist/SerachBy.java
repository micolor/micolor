package com.micolor.common.utils.tablelist;

/**
 * @Author Ann
 * @Date 2018/11/23 下午10:15
 * @Description
 */
public class SerachBy {
    //and 或者 or
    private String type;
    //运算符号 like, like_l, like_r, eq, in, lt, let, gt, gte
    private String operator;
    //值
    private String val;
    //key
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
