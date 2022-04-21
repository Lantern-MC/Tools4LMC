package net.lanternmc.AllUtils.JavaUtils;

public class Column {
    private final String name;
    
    private Object type;
    
    private int a;
    
    private int b;
    
    public Column(String paramString) {
        this.name = paramString;
        this.type = ColumnString.TEXT;
    }
    
    public Column(String paramString, ColumnInteger paramColumnInteger) {
        this(paramString);
        this.type = paramColumnInteger;
        this.a = 12;
    }
    
    public Column(String paramString, ColumnInteger paramColumnInteger, int paramInt) {
        this(paramString);
        this.type = paramColumnInteger;
        this.a = paramInt;
    }
    
    public Column(String paramString, ColumnFloat paramColumnFloat, int paramInt1, int paramInt2) {
        this(paramString);
        this.type = paramColumnFloat;
        this.a = paramInt1;
        this.b = paramInt2;
    }
    
    public Column(String paramString, ColumnChar paramColumnChar, int paramInt) {
        this(paramString);
        this.type = paramColumnChar;
        this.a = paramInt;
    }
    
    public Column(String paramString, ColumnString paramColumnString) {
        this(paramString);
        this.type = paramColumnString;
    }
    
    public String toString() {
        return (this.type instanceof ColumnInteger || this.type instanceof ColumnChar) ? ("`" + this.name + "` " + this.type.toString().toLowerCase() + "(" + this.a + ")") : ((this.type instanceof ColumnFloat) ? ("`" + this.name + "` " + this.type.toString().toLowerCase() + "(" + this.a + "," + this.b + ")") : ("`" + this.name + "` " + this.type.toString().toLowerCase()));
    }
    
    public enum ColumnString {
        TINYTEXT, TEXT, MEDIUMTEXT, LONGTEXT;
    }
    
    public enum ColumnChar {
        CHAR, VARCHAR;
    }
    
    public enum ColumnFloat {
        FLOAT, DOUBLE;
    }
    
    public enum ColumnInteger {
        TINYINT, SMALLINT, MEDIUMINT, INT, BIGINT;
    }
}
