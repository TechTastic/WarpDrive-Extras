package io.github.techtastic.warpdrive_extras;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import io.github.techtastic.warpdrive_extras.block.StarSystemViewerBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public class CommonProxy {

    public static Block STAR_SYSTEM_VIEWER;

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        WarpDriveExtras.LOG.info("I am WarpDriveExtras at version 1.0.0");

        STAR_SYSTEM_VIEWER = new StarSystemViewerBlock(Material.anvil);

        GameRegistry.registerBlock(STAR_SYSTEM_VIEWER, ItemBlock.class, "star_system_viewer");

        // Debug output
        System.out.println("[WarpDrive Extras] Registered block: " + STAR_SYSTEM_VIEWER.getUnlocalizedName());
    }

    // load "Do your mod setup. Build whatever data structures you care about. Register recipes." (Remove if not needed)
    public void init(FMLInitializationEvent event) {}

    // postInit "Handle interaction with other mods, complete your setup based on this." (Remove if not needed)
    public void postInit(FMLPostInitializationEvent event) {
        // CC / OC integration
    }
}
