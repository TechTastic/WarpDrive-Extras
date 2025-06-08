package io.github.techtastic.warpdrive_extras.block;

import cr0s.warpdrive.WarpDrive;
import cr0s.warpdrive.data.CelestialObject;
import cr0s.warpdrive.data.CelestialObjectManager;
import cr0s.warpdrive.data.VectorI;
import io.github.techtastic.warpdrive_extras.block.entity.StarSystemViewerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StarSystemViewerBlock extends Block {
    public StarSystemViewerBlock(Material p_i45401_1_) {
        super(p_i45401_1_);

        this.setBlockName("star_system_viewer");
        this.setCreativeTab(WarpDrive.creativeTabWarpDrive);
        this.setHardness(2f);
        this.setResistance(10f);
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockTextureName("warpdrive_extras:star_system_viewer");
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new StarSystemViewerTileEntity();
    }
}
