package io.github.techtastic.warpdrive_extras.util;

import cr0s.warpdrive.data.CelestialObject;
import cr0s.warpdrive.data.CelestialObjectManager;
import cr0s.warpdrive.data.VectorI;
import net.minecraft.util.AxisAlignedBB;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LuaConversionMethods {
    public static Map<String, Object> getAllCelestialObjects() {
        try {
            Field field = CelestialObjectManager.class.getDeclaredField("SERVER");
            field.setAccessible(true);

            CelestialObjectManager manager = (CelestialObjectManager) field.get(null);
            CelestialObject[] objects = manager.celestialObjects;

            return Arrays.stream(objects).collect(Collectors.toMap(object -> object.id, LuaConversionMethods::toLua));

        } catch (Exception ignored) {}

        return new HashMap<>();
    }

    public static Map<String, Object> toLua(@Nullable CelestialObject object) {
        HashMap<String, Object> table = new HashMap<>();

        if (object == null)
            return table;

        table.put("backgroundColor", toLua(object.backgroundColor));
        table.put("baseStarBrightness", (double) object.baseStarBrightness);
        table.put("borderRadiusX", object.borderRadiusX);
        table.put("borderRadiusZ", object.borderRadiusZ);
        table.put("colorFog", toLua(object.colorFog));
        table.put("dimensionCenterX", object.dimensionCenterX);
        table.put("dimensionCenterZ", object.dimensionCenterZ);
        table.put("dimensionId", object.dimensionId);
        table.put("factorFog", toLua(object.factorFog));
        table.put("opacityCelestialObjects", (double) object.opacityCelestialObjects);
        table.put("parent", toLua(object.parent));
        table.put("vanillaStarBrightness", (double) object.vanillaStarBrightness);
        table.put("gravity", object.getGravity());
        table.put("areaInParent", toLua(object.getAreaInParent()));
        table.put("areaToReachParent", toLua(object.getAreaToReachParent()));
        table.put("description", object.getDescription());
        table.put("displayName", object.getDisplayName());
        table.put("entryOffset", toLua(object.getEntryOffset()));
        table.put("name", object.getName());
        table.put("hasAtmosphere", object.hasAtmosphere());
        table.put("isHyperspace", object.isHyperspace());
        table.put("isSpace", object.isSpace());
        table.put("worldBorderArea", toLua(object.getWorldBorderArea()));

        return table;
    }

    public static HashMap<String, Object> toLua(CelestialObject.ColorData data) {
        HashMap<String, Object> dataTable = new HashMap<>();

        dataTable.put("red", data.red);
        dataTable.put("green", data.green);
        dataTable.put("blue", data.blue);

        return dataTable;
    }

    public static HashMap<String, Double> toLua(AxisAlignedBB aabb) {
        HashMap<String, Double> table = new HashMap<>();

        table.put("maxX", aabb.maxX);
        table.put("maxY", aabb.maxY);
        table.put("maxZ", aabb.maxZ);
        table.put("minX", aabb.minX);
        table.put("minY", aabb.minY);
        table.put("minZ", aabb.minZ);

        return table;
    }

    public static HashMap<String, Integer> toLua(VectorI vec) {
        HashMap<String, Integer> table = new HashMap<>();

        table.put("x", vec.x);
        table.put("y", vec.y);
        table.put("z", vec.z);

        return table;
    }
}
