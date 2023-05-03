/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.netease.arctic.ams.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2023-05-03")
public class TableChange implements org.apache.thrift.TBase<TableChange, TableChange._Fields>, java.io.Serializable, Cloneable, Comparable<TableChange> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TableChange");

  private static final org.apache.thrift.protocol.TField INNER_TABLE_FIELD_DESC = new org.apache.thrift.protocol.TField("innerTable", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ADD_FILES_FIELD_DESC = new org.apache.thrift.protocol.TField("addFiles", org.apache.thrift.protocol.TType.LIST, (short)2);
  private static final org.apache.thrift.protocol.TField DELETE_FILES_FIELD_DESC = new org.apache.thrift.protocol.TField("deleteFiles", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField SNAPSHOT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("snapshotId", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField SNAPSHOT_SEQUENCE_FIELD_DESC = new org.apache.thrift.protocol.TField("snapshotSequence", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField PARENT_SNAPSHOT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("parentSnapshotId", org.apache.thrift.protocol.TType.I64, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TableChangeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TableChangeTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String innerTable; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<DataFile> addFiles; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<DataFile> deleteFiles; // required
  public long snapshotId; // required
  public long snapshotSequence; // required
  public long parentSnapshotId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INNER_TABLE((short)1, "innerTable"),
    ADD_FILES((short)2, "addFiles"),
    DELETE_FILES((short)3, "deleteFiles"),
    SNAPSHOT_ID((short)4, "snapshotId"),
    SNAPSHOT_SEQUENCE((short)5, "snapshotSequence"),
    PARENT_SNAPSHOT_ID((short)6, "parentSnapshotId");

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
        case 1: // INNER_TABLE
          return INNER_TABLE;
        case 2: // ADD_FILES
          return ADD_FILES;
        case 3: // DELETE_FILES
          return DELETE_FILES;
        case 4: // SNAPSHOT_ID
          return SNAPSHOT_ID;
        case 5: // SNAPSHOT_SEQUENCE
          return SNAPSHOT_SEQUENCE;
        case 6: // PARENT_SNAPSHOT_ID
          return PARENT_SNAPSHOT_ID;
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
  private static final int __SNAPSHOTID_ISSET_ID = 0;
  private static final int __SNAPSHOTSEQUENCE_ISSET_ID = 1;
  private static final int __PARENTSNAPSHOTID_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.INNER_TABLE, new org.apache.thrift.meta_data.FieldMetaData("innerTable", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ADD_FILES, new org.apache.thrift.meta_data.FieldMetaData("addFiles", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DataFile.class))));
    tmpMap.put(_Fields.DELETE_FILES, new org.apache.thrift.meta_data.FieldMetaData("deleteFiles", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, DataFile.class))));
    tmpMap.put(_Fields.SNAPSHOT_ID, new org.apache.thrift.meta_data.FieldMetaData("snapshotId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SNAPSHOT_SEQUENCE, new org.apache.thrift.meta_data.FieldMetaData("snapshotSequence", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.PARENT_SNAPSHOT_ID, new org.apache.thrift.meta_data.FieldMetaData("parentSnapshotId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TableChange.class, metaDataMap);
  }

  public TableChange() {
  }

  public TableChange(
    java.lang.String innerTable,
    java.util.List<DataFile> addFiles,
    java.util.List<DataFile> deleteFiles,
    long snapshotId,
    long snapshotSequence,
    long parentSnapshotId)
  {
    this();
    this.innerTable = innerTable;
    this.addFiles = addFiles;
    this.deleteFiles = deleteFiles;
    this.snapshotId = snapshotId;
    setSnapshotIdIsSet(true);
    this.snapshotSequence = snapshotSequence;
    setSnapshotSequenceIsSet(true);
    this.parentSnapshotId = parentSnapshotId;
    setParentSnapshotIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TableChange(TableChange other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetInnerTable()) {
      this.innerTable = other.innerTable;
    }
    if (other.isSetAddFiles()) {
      java.util.List<DataFile> __this__addFiles = new java.util.ArrayList<DataFile>(other.addFiles.size());
      for (DataFile other_element : other.addFiles) {
        __this__addFiles.add(new DataFile(other_element));
      }
      this.addFiles = __this__addFiles;
    }
    if (other.isSetDeleteFiles()) {
      java.util.List<DataFile> __this__deleteFiles = new java.util.ArrayList<DataFile>(other.deleteFiles.size());
      for (DataFile other_element : other.deleteFiles) {
        __this__deleteFiles.add(new DataFile(other_element));
      }
      this.deleteFiles = __this__deleteFiles;
    }
    this.snapshotId = other.snapshotId;
    this.snapshotSequence = other.snapshotSequence;
    this.parentSnapshotId = other.parentSnapshotId;
  }

  public TableChange deepCopy() {
    return new TableChange(this);
  }

  @Override
  public void clear() {
    this.innerTable = null;
    this.addFiles = null;
    this.deleteFiles = null;
    setSnapshotIdIsSet(false);
    this.snapshotId = 0;
    setSnapshotSequenceIsSet(false);
    this.snapshotSequence = 0;
    setParentSnapshotIdIsSet(false);
    this.parentSnapshotId = 0;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getInnerTable() {
    return this.innerTable;
  }

  public TableChange setInnerTable(@org.apache.thrift.annotation.Nullable java.lang.String innerTable) {
    this.innerTable = innerTable;
    return this;
  }

  public void unsetInnerTable() {
    this.innerTable = null;
  }

  /** Returns true if field innerTable is set (has been assigned a value) and false otherwise */
  public boolean isSetInnerTable() {
    return this.innerTable != null;
  }

  public void setInnerTableIsSet(boolean value) {
    if (!value) {
      this.innerTable = null;
    }
  }

  public int getAddFilesSize() {
    return (this.addFiles == null) ? 0 : this.addFiles.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<DataFile> getAddFilesIterator() {
    return (this.addFiles == null) ? null : this.addFiles.iterator();
  }

  public void addToAddFiles(DataFile elem) {
    if (this.addFiles == null) {
      this.addFiles = new java.util.ArrayList<DataFile>();
    }
    this.addFiles.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<DataFile> getAddFiles() {
    return this.addFiles;
  }

  public TableChange setAddFiles(@org.apache.thrift.annotation.Nullable java.util.List<DataFile> addFiles) {
    this.addFiles = addFiles;
    return this;
  }

  public void unsetAddFiles() {
    this.addFiles = null;
  }

  /** Returns true if field addFiles is set (has been assigned a value) and false otherwise */
  public boolean isSetAddFiles() {
    return this.addFiles != null;
  }

  public void setAddFilesIsSet(boolean value) {
    if (!value) {
      this.addFiles = null;
    }
  }

  public int getDeleteFilesSize() {
    return (this.deleteFiles == null) ? 0 : this.deleteFiles.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<DataFile> getDeleteFilesIterator() {
    return (this.deleteFiles == null) ? null : this.deleteFiles.iterator();
  }

  public void addToDeleteFiles(DataFile elem) {
    if (this.deleteFiles == null) {
      this.deleteFiles = new java.util.ArrayList<DataFile>();
    }
    this.deleteFiles.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<DataFile> getDeleteFiles() {
    return this.deleteFiles;
  }

  public TableChange setDeleteFiles(@org.apache.thrift.annotation.Nullable java.util.List<DataFile> deleteFiles) {
    this.deleteFiles = deleteFiles;
    return this;
  }

  public void unsetDeleteFiles() {
    this.deleteFiles = null;
  }

  /** Returns true if field deleteFiles is set (has been assigned a value) and false otherwise */
  public boolean isSetDeleteFiles() {
    return this.deleteFiles != null;
  }

  public void setDeleteFilesIsSet(boolean value) {
    if (!value) {
      this.deleteFiles = null;
    }
  }

  public long getSnapshotId() {
    return this.snapshotId;
  }

  public TableChange setSnapshotId(long snapshotId) {
    this.snapshotId = snapshotId;
    setSnapshotIdIsSet(true);
    return this;
  }

  public void unsetSnapshotId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SNAPSHOTID_ISSET_ID);
  }

  /** Returns true if field snapshotId is set (has been assigned a value) and false otherwise */
  public boolean isSetSnapshotId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SNAPSHOTID_ISSET_ID);
  }

  public void setSnapshotIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SNAPSHOTID_ISSET_ID, value);
  }

  public long getSnapshotSequence() {
    return this.snapshotSequence;
  }

  public TableChange setSnapshotSequence(long snapshotSequence) {
    this.snapshotSequence = snapshotSequence;
    setSnapshotSequenceIsSet(true);
    return this;
  }

  public void unsetSnapshotSequence() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __SNAPSHOTSEQUENCE_ISSET_ID);
  }

  /** Returns true if field snapshotSequence is set (has been assigned a value) and false otherwise */
  public boolean isSetSnapshotSequence() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __SNAPSHOTSEQUENCE_ISSET_ID);
  }

  public void setSnapshotSequenceIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __SNAPSHOTSEQUENCE_ISSET_ID, value);
  }

  public long getParentSnapshotId() {
    return this.parentSnapshotId;
  }

  public TableChange setParentSnapshotId(long parentSnapshotId) {
    this.parentSnapshotId = parentSnapshotId;
    setParentSnapshotIdIsSet(true);
    return this;
  }

  public void unsetParentSnapshotId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PARENTSNAPSHOTID_ISSET_ID);
  }

  /** Returns true if field parentSnapshotId is set (has been assigned a value) and false otherwise */
  public boolean isSetParentSnapshotId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PARENTSNAPSHOTID_ISSET_ID);
  }

  public void setParentSnapshotIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PARENTSNAPSHOTID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case INNER_TABLE:
      if (value == null) {
        unsetInnerTable();
      } else {
        setInnerTable((java.lang.String)value);
      }
      break;

    case ADD_FILES:
      if (value == null) {
        unsetAddFiles();
      } else {
        setAddFiles((java.util.List<DataFile>)value);
      }
      break;

    case DELETE_FILES:
      if (value == null) {
        unsetDeleteFiles();
      } else {
        setDeleteFiles((java.util.List<DataFile>)value);
      }
      break;

    case SNAPSHOT_ID:
      if (value == null) {
        unsetSnapshotId();
      } else {
        setSnapshotId((java.lang.Long)value);
      }
      break;

    case SNAPSHOT_SEQUENCE:
      if (value == null) {
        unsetSnapshotSequence();
      } else {
        setSnapshotSequence((java.lang.Long)value);
      }
      break;

    case PARENT_SNAPSHOT_ID:
      if (value == null) {
        unsetParentSnapshotId();
      } else {
        setParentSnapshotId((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case INNER_TABLE:
      return getInnerTable();

    case ADD_FILES:
      return getAddFiles();

    case DELETE_FILES:
      return getDeleteFiles();

    case SNAPSHOT_ID:
      return getSnapshotId();

    case SNAPSHOT_SEQUENCE:
      return getSnapshotSequence();

    case PARENT_SNAPSHOT_ID:
      return getParentSnapshotId();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case INNER_TABLE:
      return isSetInnerTable();
    case ADD_FILES:
      return isSetAddFiles();
    case DELETE_FILES:
      return isSetDeleteFiles();
    case SNAPSHOT_ID:
      return isSetSnapshotId();
    case SNAPSHOT_SEQUENCE:
      return isSetSnapshotSequence();
    case PARENT_SNAPSHOT_ID:
      return isSetParentSnapshotId();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TableChange)
      return this.equals((TableChange)that);
    return false;
  }

  public boolean equals(TableChange that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_innerTable = true && this.isSetInnerTable();
    boolean that_present_innerTable = true && that.isSetInnerTable();
    if (this_present_innerTable || that_present_innerTable) {
      if (!(this_present_innerTable && that_present_innerTable))
        return false;
      if (!this.innerTable.equals(that.innerTable))
        return false;
    }

    boolean this_present_addFiles = true && this.isSetAddFiles();
    boolean that_present_addFiles = true && that.isSetAddFiles();
    if (this_present_addFiles || that_present_addFiles) {
      if (!(this_present_addFiles && that_present_addFiles))
        return false;
      if (!this.addFiles.equals(that.addFiles))
        return false;
    }

    boolean this_present_deleteFiles = true && this.isSetDeleteFiles();
    boolean that_present_deleteFiles = true && that.isSetDeleteFiles();
    if (this_present_deleteFiles || that_present_deleteFiles) {
      if (!(this_present_deleteFiles && that_present_deleteFiles))
        return false;
      if (!this.deleteFiles.equals(that.deleteFiles))
        return false;
    }

    boolean this_present_snapshotId = true;
    boolean that_present_snapshotId = true;
    if (this_present_snapshotId || that_present_snapshotId) {
      if (!(this_present_snapshotId && that_present_snapshotId))
        return false;
      if (this.snapshotId != that.snapshotId)
        return false;
    }

    boolean this_present_snapshotSequence = true;
    boolean that_present_snapshotSequence = true;
    if (this_present_snapshotSequence || that_present_snapshotSequence) {
      if (!(this_present_snapshotSequence && that_present_snapshotSequence))
        return false;
      if (this.snapshotSequence != that.snapshotSequence)
        return false;
    }

    boolean this_present_parentSnapshotId = true;
    boolean that_present_parentSnapshotId = true;
    if (this_present_parentSnapshotId || that_present_parentSnapshotId) {
      if (!(this_present_parentSnapshotId && that_present_parentSnapshotId))
        return false;
      if (this.parentSnapshotId != that.parentSnapshotId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetInnerTable()) ? 131071 : 524287);
    if (isSetInnerTable())
      hashCode = hashCode * 8191 + innerTable.hashCode();

    hashCode = hashCode * 8191 + ((isSetAddFiles()) ? 131071 : 524287);
    if (isSetAddFiles())
      hashCode = hashCode * 8191 + addFiles.hashCode();

    hashCode = hashCode * 8191 + ((isSetDeleteFiles()) ? 131071 : 524287);
    if (isSetDeleteFiles())
      hashCode = hashCode * 8191 + deleteFiles.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(snapshotId);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(snapshotSequence);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(parentSnapshotId);

    return hashCode;
  }

  @Override
  public int compareTo(TableChange other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetInnerTable()).compareTo(other.isSetInnerTable());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInnerTable()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.innerTable, other.innerTable);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetAddFiles()).compareTo(other.isSetAddFiles());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAddFiles()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.addFiles, other.addFiles);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDeleteFiles()).compareTo(other.isSetDeleteFiles());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDeleteFiles()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deleteFiles, other.deleteFiles);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSnapshotId()).compareTo(other.isSetSnapshotId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSnapshotId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.snapshotId, other.snapshotId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSnapshotSequence()).compareTo(other.isSetSnapshotSequence());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSnapshotSequence()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.snapshotSequence, other.snapshotSequence);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetParentSnapshotId()).compareTo(other.isSetParentSnapshotId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParentSnapshotId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parentSnapshotId, other.parentSnapshotId);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TableChange(");
    boolean first = true;

    sb.append("innerTable:");
    if (this.innerTable == null) {
      sb.append("null");
    } else {
      sb.append(this.innerTable);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("addFiles:");
    if (this.addFiles == null) {
      sb.append("null");
    } else {
      sb.append(this.addFiles);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("deleteFiles:");
    if (this.deleteFiles == null) {
      sb.append("null");
    } else {
      sb.append(this.deleteFiles);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("snapshotId:");
    sb.append(this.snapshotId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("snapshotSequence:");
    sb.append(this.snapshotSequence);
    first = false;
    if (!first) sb.append(", ");
    sb.append("parentSnapshotId:");
    sb.append(this.parentSnapshotId);
    first = false;
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TableChangeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TableChangeStandardScheme getScheme() {
      return new TableChangeStandardScheme();
    }
  }

  private static class TableChangeStandardScheme extends org.apache.thrift.scheme.StandardScheme<TableChange> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TableChange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INNER_TABLE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.innerTable = iprot.readString();
              struct.setInnerTableIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ADD_FILES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list48 = iprot.readListBegin();
                struct.addFiles = new java.util.ArrayList<DataFile>(_list48.size);
                @org.apache.thrift.annotation.Nullable DataFile _elem49;
                for (int _i50 = 0; _i50 < _list48.size; ++_i50)
                {
                  _elem49 = new DataFile();
                  _elem49.read(iprot);
                  struct.addFiles.add(_elem49);
                }
                iprot.readListEnd();
              }
              struct.setAddFilesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DELETE_FILES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list51 = iprot.readListBegin();
                struct.deleteFiles = new java.util.ArrayList<DataFile>(_list51.size);
                @org.apache.thrift.annotation.Nullable DataFile _elem52;
                for (int _i53 = 0; _i53 < _list51.size; ++_i53)
                {
                  _elem52 = new DataFile();
                  _elem52.read(iprot);
                  struct.deleteFiles.add(_elem52);
                }
                iprot.readListEnd();
              }
              struct.setDeleteFilesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SNAPSHOT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.snapshotId = iprot.readI64();
              struct.setSnapshotIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SNAPSHOT_SEQUENCE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.snapshotSequence = iprot.readI64();
              struct.setSnapshotSequenceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // PARENT_SNAPSHOT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.parentSnapshotId = iprot.readI64();
              struct.setParentSnapshotIdIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TableChange struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.innerTable != null) {
        oprot.writeFieldBegin(INNER_TABLE_FIELD_DESC);
        oprot.writeString(struct.innerTable);
        oprot.writeFieldEnd();
      }
      if (struct.addFiles != null) {
        oprot.writeFieldBegin(ADD_FILES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.addFiles.size()));
          for (DataFile _iter54 : struct.addFiles)
          {
            _iter54.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.deleteFiles != null) {
        oprot.writeFieldBegin(DELETE_FILES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.deleteFiles.size()));
          for (DataFile _iter55 : struct.deleteFiles)
          {
            _iter55.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(SNAPSHOT_ID_FIELD_DESC);
      oprot.writeI64(struct.snapshotId);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(SNAPSHOT_SEQUENCE_FIELD_DESC);
      oprot.writeI64(struct.snapshotSequence);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(PARENT_SNAPSHOT_ID_FIELD_DESC);
      oprot.writeI64(struct.parentSnapshotId);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TableChangeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TableChangeTupleScheme getScheme() {
      return new TableChangeTupleScheme();
    }
  }

  private static class TableChangeTupleScheme extends org.apache.thrift.scheme.TupleScheme<TableChange> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TableChange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetInnerTable()) {
        optionals.set(0);
      }
      if (struct.isSetAddFiles()) {
        optionals.set(1);
      }
      if (struct.isSetDeleteFiles()) {
        optionals.set(2);
      }
      if (struct.isSetSnapshotId()) {
        optionals.set(3);
      }
      if (struct.isSetSnapshotSequence()) {
        optionals.set(4);
      }
      if (struct.isSetParentSnapshotId()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetInnerTable()) {
        oprot.writeString(struct.innerTable);
      }
      if (struct.isSetAddFiles()) {
        {
          oprot.writeI32(struct.addFiles.size());
          for (DataFile _iter56 : struct.addFiles)
          {
            _iter56.write(oprot);
          }
        }
      }
      if (struct.isSetDeleteFiles()) {
        {
          oprot.writeI32(struct.deleteFiles.size());
          for (DataFile _iter57 : struct.deleteFiles)
          {
            _iter57.write(oprot);
          }
        }
      }
      if (struct.isSetSnapshotId()) {
        oprot.writeI64(struct.snapshotId);
      }
      if (struct.isSetSnapshotSequence()) {
        oprot.writeI64(struct.snapshotSequence);
      }
      if (struct.isSetParentSnapshotId()) {
        oprot.writeI64(struct.parentSnapshotId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TableChange struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.innerTable = iprot.readString();
        struct.setInnerTableIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TList _list58 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.addFiles = new java.util.ArrayList<DataFile>(_list58.size);
          @org.apache.thrift.annotation.Nullable DataFile _elem59;
          for (int _i60 = 0; _i60 < _list58.size; ++_i60)
          {
            _elem59 = new DataFile();
            _elem59.read(iprot);
            struct.addFiles.add(_elem59);
          }
        }
        struct.setAddFilesIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list61 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.deleteFiles = new java.util.ArrayList<DataFile>(_list61.size);
          @org.apache.thrift.annotation.Nullable DataFile _elem62;
          for (int _i63 = 0; _i63 < _list61.size; ++_i63)
          {
            _elem62 = new DataFile();
            _elem62.read(iprot);
            struct.deleteFiles.add(_elem62);
          }
        }
        struct.setDeleteFilesIsSet(true);
      }
      if (incoming.get(3)) {
        struct.snapshotId = iprot.readI64();
        struct.setSnapshotIdIsSet(true);
      }
      if (incoming.get(4)) {
        struct.snapshotSequence = iprot.readI64();
        struct.setSnapshotSequenceIsSet(true);
      }
      if (incoming.get(5)) {
        struct.parentSnapshotId = iprot.readI64();
        struct.setParentSnapshotIdIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

