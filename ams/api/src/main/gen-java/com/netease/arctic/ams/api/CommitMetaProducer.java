/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.netease.arctic.ams.api;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2023-05-08")
public enum CommitMetaProducer implements org.apache.thrift.TEnum {
  OPTIMIZE(0),
  INGESTION(1);

  private final int value;

  private CommitMetaProducer(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static CommitMetaProducer findByValue(int value) { 
    switch (value) {
      case 0:
        return OPTIMIZE;
      case 1:
        return INGESTION;
      default:
        return null;
    }
  }
}
