/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.netease.arctic.ams.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2023-05-08")
public class Schema implements org.apache.thrift.TBase<Schema, Schema._Fields>, java.io.Serializable, Cloneable, Comparable<Schema> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Schema");

  private static final org.apache.thrift.protocol.TField COLUMNS_FIELD_DESC = new org.apache.thrift.protocol.TField("columns", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField PKS_FIELD_DESC = new org.apache.thrift.protocol.TField("pks", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField PARTITION_COLUMNS_FIELD_DESC = new org.apache.thrift.protocol.TField("partitionColumns", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField SORT_COLUMNS_FIELD_DESC = new org.apache.thrift.protocol.TField("sortColumns", org.apache.thrift.protocol.TType.LIST, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new SchemaStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new SchemaTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> columns; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> pks; // optional
  public @org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> partitionColumns; // optional
  public @org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> sortColumns; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COLUMNS((short)1, "columns"),
    PKS((short)2, "pks"),
    PARTITION_COLUMNS((short)3, "partitionColumns"),
    SORT_COLUMNS((short)4, "sortColumns");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // COLUMNS
          return COLUMNS;
        case 2: // PKS
          return PKS;
        case 3: // PARTITION_COLUMNS
          return PARTITION_COLUMNS;
        case 4: // SORT_COLUMNS
          return SORT_COLUMNS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final _Fields optionals[] = {_Fields.PKS,_Fields.PARTITION_COLUMNS,_Fields.SORT_COLUMNS};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COLUMNS, new org.apache.thrift.meta_data.FieldMetaData("columns", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnInfo.class))));
    tmpMap.put(_Fields.PKS, new org.apache.thrift.meta_data.FieldMetaData("pks", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnInfo.class))));
    tmpMap.put(_Fields.PARTITION_COLUMNS, new org.apache.thrift.meta_data.FieldMetaData("partitionColumns", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnInfo.class))));
    tmpMap.put(_Fields.SORT_COLUMNS, new org.apache.thrift.meta_data.FieldMetaData("sortColumns", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ColumnInfo.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Schema.class, metaDataMap);
  }

  public Schema() {
  }

  public Schema(
    java.util.List<ColumnInfo> columns)
  {
    this();
    this.columns = columns;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Schema(Schema other) {
    if (other.isSetColumns()) {
      java.util.List<ColumnInfo> __this__columns = new java.util.ArrayList<ColumnInfo>(other.columns.size());
      for (ColumnInfo other_element : other.columns) {
        __this__columns.add(new ColumnInfo(other_element));
      }
      this.columns = __this__columns;
    }
    if (other.isSetPks()) {
      java.util.List<ColumnInfo> __this__pks = new java.util.ArrayList<ColumnInfo>(other.pks.size());
      for (ColumnInfo other_element : other.pks) {
        __this__pks.add(new ColumnInfo(other_element));
      }
      this.pks = __this__pks;
    }
    if (other.isSetPartitionColumns()) {
      java.util.List<ColumnInfo> __this__partitionColumns = new java.util.ArrayList<ColumnInfo>(other.partitionColumns.size());
      for (ColumnInfo other_element : other.partitionColumns) {
        __this__partitionColumns.add(new ColumnInfo(other_element));
      }
      this.partitionColumns = __this__partitionColumns;
    }
    if (other.isSetSortColumns()) {
      java.util.List<ColumnInfo> __this__sortColumns = new java.util.ArrayList<ColumnInfo>(other.sortColumns.size());
      for (ColumnInfo other_element : other.sortColumns) {
        __this__sortColumns.add(new ColumnInfo(other_element));
      }
      this.sortColumns = __this__sortColumns;
    }
  }

  public Schema deepCopy() {
    return new Schema(this);
  }

  @Override
  public void clear() {
    this.columns = null;
    this.pks = null;
    this.partitionColumns = null;
    this.sortColumns = null;
  }

  public int getColumnsSize() {
    return (this.columns == null) ? 0 : this.columns.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<ColumnInfo> getColumnsIterator() {
    return (this.columns == null) ? null : this.columns.iterator();
  }

  public void addToColumns(ColumnInfo elem) {
    if (this.columns == null) {
      this.columns = new java.util.ArrayList<ColumnInfo>();
    }
    this.columns.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<ColumnInfo> getColumns() {
    return this.columns;
  }

  public Schema setColumns(@org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> columns) {
    this.columns = columns;
    return this;
  }

  public void unsetColumns() {
    this.columns = null;
  }

  /** Returns true if field columns is set (has been assigned a value) and false otherwise */
  public boolean isSetColumns() {
    return this.columns != null;
  }

  public void setColumnsIsSet(boolean value) {
    if (!value) {
      this.columns = null;
    }
  }

  public int getPksSize() {
    return (this.pks == null) ? 0 : this.pks.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<ColumnInfo> getPksIterator() {
    return (this.pks == null) ? null : this.pks.iterator();
  }

  public void addToPks(ColumnInfo elem) {
    if (this.pks == null) {
      this.pks = new java.util.ArrayList<ColumnInfo>();
    }
    this.pks.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<ColumnInfo> getPks() {
    return this.pks;
  }

  public Schema setPks(@org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> pks) {
    this.pks = pks;
    return this;
  }

  public void unsetPks() {
    this.pks = null;
  }

  /** Returns true if field pks is set (has been assigned a value) and false otherwise */
  public boolean isSetPks() {
    return this.pks != null;
  }

  public void setPksIsSet(boolean value) {
    if (!value) {
      this.pks = null;
    }
  }

  public int getPartitionColumnsSize() {
    return (this.partitionColumns == null) ? 0 : this.partitionColumns.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<ColumnInfo> getPartitionColumnsIterator() {
    return (this.partitionColumns == null) ? null : this.partitionColumns.iterator();
  }

  public void addToPartitionColumns(ColumnInfo elem) {
    if (this.partitionColumns == null) {
      this.partitionColumns = new java.util.ArrayList<ColumnInfo>();
    }
    this.partitionColumns.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<ColumnInfo> getPartitionColumns() {
    return this.partitionColumns;
  }

  public Schema setPartitionColumns(@org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> partitionColumns) {
    this.partitionColumns = partitionColumns;
    return this;
  }

  public void unsetPartitionColumns() {
    this.partitionColumns = null;
  }

  /** Returns true if field partitionColumns is set (has been assigned a value) and false otherwise */
  public boolean isSetPartitionColumns() {
    return this.partitionColumns != null;
  }

  public void setPartitionColumnsIsSet(boolean value) {
    if (!value) {
      this.partitionColumns = null;
    }
  }

  public int getSortColumnsSize() {
    return (this.sortColumns == null) ? 0 : this.sortColumns.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<ColumnInfo> getSortColumnsIterator() {
    return (this.sortColumns == null) ? null : this.sortColumns.iterator();
  }

  public void addToSortColumns(ColumnInfo elem) {
    if (this.sortColumns == null) {
      this.sortColumns = new java.util.ArrayList<ColumnInfo>();
    }
    this.sortColumns.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<ColumnInfo> getSortColumns() {
    return this.sortColumns;
  }

  public Schema setSortColumns(@org.apache.thrift.annotation.Nullable java.util.List<ColumnInfo> sortColumns) {
    this.sortColumns = sortColumns;
    return this;
  }

  public void unsetSortColumns() {
    this.sortColumns = null;
  }

  /** Returns true if field sortColumns is set (has been assigned a value) and false otherwise */
  public boolean isSetSortColumns() {
    return this.sortColumns != null;
  }

  public void setSortColumnsIsSet(boolean value) {
    if (!value) {
      this.sortColumns = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case COLUMNS:
      if (value == null) {
        unsetColumns();
      } else {
        setColumns((java.util.List<ColumnInfo>)value);
      }
      break;

    case PKS:
      if (value == null) {
        unsetPks();
      } else {
        setPks((java.util.List<ColumnInfo>)value);
      }
      break;

    case PARTITION_COLUMNS:
      if (value == null) {
        unsetPartitionColumns();
      } else {
        setPartitionColumns((java.util.List<ColumnInfo>)value);
      }
      break;

    case SORT_COLUMNS:
      if (value == null) {
        unsetSortColumns();
      } else {
        setSortColumns((java.util.List<ColumnInfo>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case COLUMNS:
      return getColumns();

    case PKS:
      return getPks();

    case PARTITION_COLUMNS:
      return getPartitionColumns();

    case SORT_COLUMNS:
      return getSortColumns();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case COLUMNS:
      return isSetColumns();
    case PKS:
      return isSetPks();
    case PARTITION_COLUMNS:
      return isSetPartitionColumns();
    case SORT_COLUMNS:
      return isSetSortColumns();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Schema)
      return this.equals((Schema)that);
    return false;
  }

  public boolean equals(Schema that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_columns = true && this.isSetColumns();
    boolean that_present_columns = true && that.isSetColumns();
    if (this_present_columns || that_present_columns) {
      if (!(this_present_columns && that_present_columns))
        return false;
      if (!this.columns.equals(that.columns))
        return false;
    }

    boolean this_present_pks = true && this.isSetPks();
    boolean that_present_pks = true && that.isSetPks();
    if (this_present_pks || that_present_pks) {
      if (!(this_present_pks && that_present_pks))
        return false;
      if (!this.pks.equals(that.pks))
        return false;
    }

    boolean this_present_partitionColumns = true && this.isSetPartitionColumns();
    boolean that_present_partitionColumns = true && that.isSetPartitionColumns();
    if (this_present_partitionColumns || that_present_partitionColumns) {
      if (!(this_present_partitionColumns && that_present_partitionColumns))
        return false;
      if (!this.partitionColumns.equals(that.partitionColumns))
        return false;
    }

    boolean this_present_sortColumns = true && this.isSetSortColumns();
    boolean that_present_sortColumns = true && that.isSetSortColumns();
    if (this_present_sortColumns || that_present_sortColumns) {
      if (!(this_present_sortColumns && that_present_sortColumns))
        return false;
      if (!this.sortColumns.equals(that.sortColumns))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetColumns()) ? 131071 : 524287);
    if (isSetColumns())
      hashCode = hashCode * 8191 + columns.hashCode();

    hashCode = hashCode * 8191 + ((isSetPks()) ? 131071 : 524287);
    if (isSetPks())
      hashCode = hashCode * 8191 + pks.hashCode();

    hashCode = hashCode * 8191 + ((isSetPartitionColumns()) ? 131071 : 524287);
    if (isSetPartitionColumns())
      hashCode = hashCode * 8191 + partitionColumns.hashCode();

    hashCode = hashCode * 8191 + ((isSetSortColumns()) ? 131071 : 524287);
    if (isSetSortColumns())
      hashCode = hashCode * 8191 + sortColumns.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Schema other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetColumns()).compareTo(other.isSetColumns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColumns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.columns, other.columns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPks()).compareTo(other.isSetPks());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPks()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pks, other.pks);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPartitionColumns()).compareTo(other.isSetPartitionColumns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPartitionColumns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.partitionColumns, other.partitionColumns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSortColumns()).compareTo(other.isSetSortColumns());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSortColumns()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.sortColumns, other.sortColumns);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Schema(");
    boolean first = true;

    sb.append("columns:");
    if (this.columns == null) {
      sb.append("null");
    } else {
      sb.append(this.columns);
    }
    first = false;
    if (isSetPks()) {
      if (!first) sb.append(", ");
      sb.append("pks:");
      if (this.pks == null) {
        sb.append("null");
      } else {
        sb.append(this.pks);
      }
      first = false;
    }
    if (isSetPartitionColumns()) {
      if (!first) sb.append(", ");
      sb.append("partitionColumns:");
      if (this.partitionColumns == null) {
        sb.append("null");
      } else {
        sb.append(this.partitionColumns);
      }
      first = false;
    }
    if (isSetSortColumns()) {
      if (!first) sb.append(", ");
      sb.append("sortColumns:");
      if (this.sortColumns == null) {
        sb.append("null");
      } else {
        sb.append(this.sortColumns);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class SchemaStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SchemaStandardScheme getScheme() {
      return new SchemaStandardScheme();
    }
  }

  private static class SchemaStandardScheme extends org.apache.thrift.scheme.StandardScheme<Schema> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Schema struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COLUMNS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.columns = new java.util.ArrayList<ColumnInfo>(_list0.size);
                @org.apache.thrift.annotation.Nullable ColumnInfo _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new ColumnInfo();
                  _elem1.read(iprot);
                  struct.columns.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setColumnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // PKS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list3 = iprot.readListBegin();
                struct.pks = new java.util.ArrayList<ColumnInfo>(_list3.size);
                @org.apache.thrift.annotation.Nullable ColumnInfo _elem4;
                for (int _i5 = 0; _i5 < _list3.size; ++_i5)
                {
                  _elem4 = new ColumnInfo();
                  _elem4.read(iprot);
                  struct.pks.add(_elem4);
                }
                iprot.readListEnd();
              }
              struct.setPksIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PARTITION_COLUMNS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list6 = iprot.readListBegin();
                struct.partitionColumns = new java.util.ArrayList<ColumnInfo>(_list6.size);
                @org.apache.thrift.annotation.Nullable ColumnInfo _elem7;
                for (int _i8 = 0; _i8 < _list6.size; ++_i8)
                {
                  _elem7 = new ColumnInfo();
                  _elem7.read(iprot);
                  struct.partitionColumns.add(_elem7);
                }
                iprot.readListEnd();
              }
              struct.setPartitionColumnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SORT_COLUMNS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list9 = iprot.readListBegin();
                struct.sortColumns = new java.util.ArrayList<ColumnInfo>(_list9.size);
                @org.apache.thrift.annotation.Nullable ColumnInfo _elem10;
                for (int _i11 = 0; _i11 < _list9.size; ++_i11)
                {
                  _elem10 = new ColumnInfo();
                  _elem10.read(iprot);
                  struct.sortColumns.add(_elem10);
                }
                iprot.readListEnd();
              }
              struct.setSortColumnsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Schema struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.columns != null) {
        oprot.writeFieldBegin(COLUMNS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.columns.size()));
          for (ColumnInfo _iter12 : struct.columns)
          {
            _iter12.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.pks != null) {
        if (struct.isSetPks()) {
          oprot.writeFieldBegin(PKS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.pks.size()));
            for (ColumnInfo _iter13 : struct.pks)
            {
              _iter13.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.partitionColumns != null) {
        if (struct.isSetPartitionColumns()) {
          oprot.writeFieldBegin(PARTITION_COLUMNS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.partitionColumns.size()));
            for (ColumnInfo _iter14 : struct.partitionColumns)
            {
              _iter14.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      if (struct.sortColumns != null) {
        if (struct.isSetSortColumns()) {
          oprot.writeFieldBegin(SORT_COLUMNS_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.sortColumns.size()));
            for (ColumnInfo _iter15 : struct.sortColumns)
            {
              _iter15.write(oprot);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class SchemaTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public SchemaTupleScheme getScheme() {
      return new SchemaTupleScheme();
    }
  }

  private static class SchemaTupleScheme extends org.apache.thrift.scheme.TupleScheme<Schema> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Schema struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetColumns()) {
        optionals.set(0);
      }
      if (struct.isSetPks()) {
        optionals.set(1);
      }
      if (struct.isSetPartitionColumns()) {
        optionals.set(2);
      }
      if (struct.isSetSortColumns()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetColumns()) {
        {
          oprot.writeI32(struct.columns.size());
          for (ColumnInfo _iter16 : struct.columns)
          {
            _iter16.write(oprot);
          }
        }
      }
      if (struct.isSetPks()) {
        {
          oprot.writeI32(struct.pks.size());
          for (ColumnInfo _iter17 : struct.pks)
          {
            _iter17.write(oprot);
          }
        }
      }
      if (struct.isSetPartitionColumns()) {
        {
          oprot.writeI32(struct.partitionColumns.size());
          for (ColumnInfo _iter18 : struct.partitionColumns)
          {
            _iter18.write(oprot);
          }
        }
      }
      if (struct.isSetSortColumns()) {
        {
          oprot.writeI32(struct.sortColumns.size());
          for (ColumnInfo _iter19 : struct.sortColumns)
          {
            _iter19.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Schema struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list20 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.columns = new java.util.ArrayList<ColumnInfo>(_list20.size);
          @org.apache.thrift.annotation.Nullable ColumnInfo _elem21;
          for (int _i22 = 0; _i22 < _list20.size; ++_i22)
          {
            _elem21 = new ColumnInfo();
            _elem21.read(iprot);
            struct.columns.add(_elem21);
          }
        }
        struct.setColumnsIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list23 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.pks = new java.util.ArrayList<ColumnInfo>(_list23.size);
          @org.apache.thrift.annotation.Nullable ColumnInfo _elem24;
          for (int _i25 = 0; _i25 < _list23.size; ++_i25)
          {
            _elem24 = new ColumnInfo();
            _elem24.read(iprot);
            struct.pks.add(_elem24);
          }
        }
        struct.setPksIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list26 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.partitionColumns = new java.util.ArrayList<ColumnInfo>(_list26.size);
          @org.apache.thrift.annotation.Nullable ColumnInfo _elem27;
          for (int _i28 = 0; _i28 < _list26.size; ++_i28)
          {
            _elem27 = new ColumnInfo();
            _elem27.read(iprot);
            struct.partitionColumns.add(_elem27);
          }
        }
        struct.setPartitionColumnsIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list29 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.sortColumns = new java.util.ArrayList<ColumnInfo>(_list29.size);
          @org.apache.thrift.annotation.Nullable ColumnInfo _elem30;
          for (int _i31 = 0; _i31 < _list29.size; ++_i31)
          {
            _elem30 = new ColumnInfo();
            _elem30.read(iprot);
            struct.sortColumns.add(_elem30);
          }
        }
        struct.setSortColumnsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

