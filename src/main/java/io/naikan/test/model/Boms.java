package io.naikan.test.model;

import java.io.InputStream;

public final class Boms {

  private Boms() {
  }

  public static InputStream validBom0asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/valid-0-bom.json");
  }

  public static InputStream validBom1asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/valid-1-bom.json");
  }

  public static InputStream validBom2asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/valid-2-bom.json");
  }

  public static InputStream invalidBom0asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/invalid-0-bom.json");
  }

  public static InputStream invalidBom1asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/invalid-1-bom.json");
  }

  public static InputStream invalidBom2asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/invalid-2-bom.json");
  }

  public static InputStream invalidBom3asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/invalid-3-bom.json");
  }

  public static InputStream invalidBom4asInputStream() {
    return Boms.class.getResourceAsStream("/1.0/invalid-4-bom.json");
  }
}
