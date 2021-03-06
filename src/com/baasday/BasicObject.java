package com.baasday;

import java.util.Map;
import java.util.List;
import java.util.Date;

public abstract class BasicObject {
    private Map<String, Object> values;

    BasicObject(final Map<String, Object> values) {
        this.values = values;
    }

    /**
     * <p>全てのフィールドの値をマップとして返します。</p>
     * @return 全てのフィールドの値
     */
    public Map<String, Object> getValues() {
        return this.values;
    }

    void setValues(final Map<String, Object> values) {
        this.values = values;
    }

    void setValue(final String key, final Object value) {
        this.values.put(key, value);
    }

    /**
     * <p>指定されたフィールドの値を返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しない場合はnull
     */
    public Object get(final String field) {
        return this.values.get(field);
    }

    /**
     * <p>指定されたフィールドが存在するかどうかを返します。</p>
     * @param field フィールド名
     * @return フィールドが存在する場合はtrue、存在しない場合はfalse
     */
    public boolean has(final String field) {
        return this.values.containsKey(field);
    }

    /**
     * <p>指定されたフィールドが値としてnullを持つかどうかを返します。</p>
     * @param field フィールド名
     * @return フィールドが存在し、値がnullの場合はtrue、それ以外はfalse
     */
    public boolean isNull(final String field) {
        return this.has(field) && this.get(field) == null;
    }

    private static BaasdayException exceptionForInvalidType(final String field, final String typeName) throws BaasdayException {
        return new BaasdayException("The value of the field " + field + " cannot be coerced to " + typeName);
    }

    /**
     * <p>指定されたフィールドの値を文字列として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合はnull
     * @throws BaasdayException フィールドの値が文字列でない場合
     */
    public String getString(final String field) throws BaasdayException {
        final Object value = this.get(field);
        if (value == null) return null;
        if (!(value instanceof String)) throw exceptionForInvalidType(field, "a string");
        return (String) value;
    }

    /**
     * <p>指定されたフィールドの値を数値として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合はnull
     * @throws BaasdayException フィールドの値が数値でない場合
     */
    public Number getNumber(final String field) throws BaasdayException {
        final Object value = this.get(field);
        if (value == null) return null;
        if (!(value instanceof Number)) throw exceptionForInvalidType(field, "a number");
        return (Number) value;
    }

    /**
     * <p>指定されたフィールドの値をint値として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合は0
     * @throws BaasdayException フィールドの値が数値でない場合
     */
    public int getInt(final String field) throws BaasdayException {
        final Number value = this.getNumber(field);
        return value == null ? 0 : value.intValue();
    }

    /**
     * <p>指定されたフィールドの値をlong値として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合は0
     * @throws BaasdayException フィールドの値が数値でない場合
     */
    public long getLong(final String field) throws BaasdayException {
        final Number value = this.getNumber(field);
        return value == null ? 0 : value.longValue();
    }

    /**
     * <p>指定されたフィールドの値をdouble値として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合は0
     * @throws BaasdayException フィールドの値が数値でない場合
     */
    public double getDouble(final String field) throws BaasdayException {
        final Number value = this.getNumber(field);
        return value == null ? 0 : value.doubleValue();
    }

    /**
     * <p>指定されたフィールドの値をboolean値として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合はfalse
     * @throws BaasdayException フィールドの値がブールでない場合
     */
    public boolean getBoolean(final String field) throws BaasdayException {
        final Object value = this.get(field);
        if (value == null) return false;
        if (!(value instanceof Boolean)) throw exceptionForInvalidType(field, "a boolean");
        return (Boolean) this.get(field);
    }

    /**
     * <p>指定されたフィールドの値をリストとして返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合はnull
     * @throws BaasdayException フィールドの値がリストでない場合
     */
    public List<Object> getList(final String field) throws BaasdayException {
        final Object value = this.get(field);
        if (value == null) return null;
        if (!(value instanceof List)) throw exceptionForInvalidType(field, "a list");
        @SuppressWarnings("unchecked")
        final List<Object> result = (List<Object>) this.get(field);
        return result;
    }

    /**
     * <p>指定されたフィールドの値をマップとして返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合はnull
     * @throws BaasdayException フィールドの値がマップでない場合
     */
    public Map<String, Object> getMap(final String field) throws BaasdayException {
        final Object value = this.get(field);
        if (value == null) return null;
        if (!(value instanceof Map)) throw exceptionForInvalidType(field, "a map");
        @SuppressWarnings("unchecked")
        final Map<String, Object> result = (Map<String, Object>) this.get(field);
        return result;
    }

    /**
     * <p>指定されたフィールドの値を日付として返します。</p>
     * @param field フィールド名
     * @return フィールドの値。フィールドが存在しないか値がnullの場合はnull
     * @throws BaasdayException フィールドの値が日付でない場合
     */
    public Date getDate(final String field) throws BaasdayException {
        final Object value = this.get(field);
        if (value == null) return null;
        if (!(value instanceof Date)) throw exceptionForInvalidType(field, "a date");
        return (Date) value;
    }
}
