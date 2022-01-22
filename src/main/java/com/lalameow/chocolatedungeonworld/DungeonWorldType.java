package com.lalameow.chocolatedungeonworld;

import static com.lalameow.chocolatedungeonworld.DungeonChunkGenerator.DUNGEON_POS;

import com.lalameow.chocolatedungeonworld.client.gui.GuiDungeonSelect;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiCreateWorld;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class DungeonWorldType extends WorldType {

    private static final BiomeProvider BIOME_PROVIDER = new VoidBiomeProvider();

    /**
     * Creates a new world type, the ID is hidden and should not be referenced by modders.
     * It will automatically expand the underlying workdType array if there are no IDs left.
     * @param name
     */
    public DungeonWorldType() {
        super("chocolateDungeon");
    }

    @Override public boolean isCustomizable() {
        return true;
    }

    @Override public void onCustomizeButton(Minecraft mc, GuiCreateWorld gui) {
        mc.displayGuiScreen(new GuiDungeonSelect(gui, gui.chunkProviderSettingsJson));
    }

    @Override public BiomeProvider getBiomeProvider(World world) {
        return BIOME_PROVIDER;
    }

    @Override public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new DungeonChunkGenerator(world);
    }

    @Override public int getMinimumSpawnHeight(World world) {
        return DUNGEON_POS.getY();
    }

    @Override public int getSpawnFuzz(WorldServer world, MinecraftServer server) {
        return 1;
    }
}
