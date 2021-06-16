package net.dodogang.plume.datagen.models.stategen;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dodogang.plume.datagen.models.modelgen.ModelGen;

import java.util.function.BiConsumer;

@SuppressWarnings("unused")
public final class ModelInfo {
    private final String model;
    private ModelGen modelgen;
    private int x;
    private int y;
    private boolean uvlock;
    private int weight = 1;

    private ModelInfo(String model) {
        this.model = model;
    }

    private ModelInfo modelGen(ModelGen modelgen) {
        this.modelgen = modelgen;
        return this;
    }

    public ModelInfo rotate(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public ModelInfo uvlock(boolean uvlock) {
        this.uvlock = uvlock;
        return this;
    }

    public ModelInfo weight(int weight) {
        this.weight = weight;
        return this;
    }

    public void getModels(BiConsumer<String, ModelGen> consumer) {
        if (modelgen != null) {
            consumer.accept(model, modelgen);
        }
    }

    public JsonObject makeJson(boolean withWeight) {
        JsonObject obj = new JsonObject();
        obj.addProperty("model", model);
        if (x != 0) {
            obj.addProperty("x", x);
        }
        if (y != 0) {
            obj.addProperty("y", y);
        }
        if (uvlock) {
            obj.addProperty("uvlock", true);
        }
        if (withWeight && weight != 1) {
            obj.addProperty("weight", weight);
        }
        return obj;
    }

    public static JsonElement makeJson(ModelInfo... variants) {
        if (variants.length == 0) return new JsonArray();
        if (variants.length == 1) return variants[0].makeJson(false);
        JsonArray arr = new JsonArray();
        for (ModelInfo variant : variants) {
            arr.add(variant.makeJson(true));
        }
        return arr;
    }

    public static ModelInfo create(String model) {
        return new ModelInfo(model);
    }

    public static ModelInfo create(String model, ModelGen gen) {
        return new ModelInfo(model).modelGen(gen);
    }
}
