package com.lalameow.chocolatedungeonworld;

import com.google.common.collect.Lists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

public class VoidBiomeProvider extends BiomeProvider {

    private static final Biome VOID_BIOME = Biome.getBiome(127);
    private static final List<Biome> BIOME_LIST = Lists.newArrayList(VOID_BIOME);
    private static final Biome[] BIOME_ARRAY = new Biome[]{VOID_BIOME};

    @Override public List<Biome> getBiomesToSpawnIn() {
        return BIOME_LIST;
    }

    @Override public Biome getBiome(BlockPos p_180300_1_, Biome p_180300_2_) {
        return VOID_BIOME;
    }

    @Override public Biome[] getBiomes(@Nullable Biome[] p_76933_1_, int p_76933_2_, int p_76933_3_, int p_76933_4_, int p_76933_5_) {
        return BIOME_ARRAY;
    }

    @Override public Biome[] getBiomesForGeneration(Biome[] p_76937_1_, int p_76937_2_, int p_76937_3_, int p_76937_4_, int p_76937_5_) {
        return BIOME_ARRAY;
    }

    @Nullable @Override public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        return BlockPos.ORIGIN;
    }
}
