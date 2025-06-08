package io.github.techtastic.warpdrive_extras.block;

import cr0s.warpdrive.WarpDrive;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import static io.github.techtastic.warpdrive_extras.WarpDriveExtras.MODID;

public class StarSystemViewerBlock extends Block {
    public StarSystemViewerBlock(Material p_i45401_1_) {
        super(p_i45401_1_);

        this.setBlockName("star_system_viewer");
        this.setCreativeTab(WarpDrive.creativeTabWarpDrive);
        this.setHardness(2f);
        this.setResistance(10f);
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockTextureName("warpdrive_extras:star_system_viewer");
        this.setBlockName("warpdrive_extras:star_system_viewer");
    }
}
