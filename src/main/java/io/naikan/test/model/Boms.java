package io.naikan.test.model;

import java.io.InputStream;

import io.naikan.model.Bom;
import io.naikan.model.deserializer.DeserializerFactory;

public final class Boms {

    private Boms() {
    }

    public static Bom validBom0() {
        return DeserializerFactory.newJsonDeserializer().of(validBom0asInputStream());
    }

    public static InputStream validBom0asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/valid-0-bom-1.0.json");
    }

    public static Bom validBom1() {
        return DeserializerFactory.newJsonDeserializer().of(validBom1asInputStream());
    }

    public static InputStream validBom1asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/valid-1-bom-1.0.json");
    }

    public static Bom validBom2() {
        return DeserializerFactory.newJsonDeserializer().of(validBom2asInputStream());
    }

    public static InputStream validBom2asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/valid-2-bom-1.0.json");
    }

    public static Bom invalidBom0() {
        return DeserializerFactory.newJsonDeserializer().of(invalidBom0asInputStream());
    }

    public static InputStream invalidBom0asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/invalid-0-bom-1.0.json");
    }

    public static Bom invalidBom1() {
        return DeserializerFactory.newJsonDeserializer().of(invalidBom1asInputStream());
    }

    public static InputStream invalidBom1asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/invalid-1-bom-1.0.json");
    }

    public static Bom invalidBom2() {
        return DeserializerFactory.newJsonDeserializer().of(invalidBom2asInputStream());
    }

    public static InputStream invalidBom2asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/invalid-2-bom-1.0.json");
    }

    public static Bom invalidBom3() {
        return DeserializerFactory.newJsonDeserializer().of(invalidBom3asInputStream());
    }

    public static InputStream invalidBom3asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/invalid-3-bom-1.0.json");
    }

    public static Bom invalidBom4() {
        return DeserializerFactory.newJsonDeserializer().of(invalidBom4asInputStream());
    }

    public static InputStream invalidBom4asInputStream() {
        return Boms.class.getResourceAsStream("/1.0/invalid-4-bom-1.0.json");
    }
}
