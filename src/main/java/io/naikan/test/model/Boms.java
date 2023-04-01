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
        return Boms.class.getResourceAsStream("/valid-0-bom-1.0.json");
    }

    public static Bom validBom1() {
        return DeserializerFactory.newJsonDeserializer().of(validBom1asInputStream());
    }

    public static InputStream validBom1asInputStream() {
        return Boms.class.getResourceAsStream("/valid-1-bom-1.0.json");
    }

    public static Bom invalidBom0() {
        return DeserializerFactory.newJsonDeserializer().of(invalidBom0asInputStream());
    }

    public static InputStream invalidBom0asInputStream() {
        return Boms.class.getResourceAsStream("/invalid-0-bom-1.0.json");
    }
}
