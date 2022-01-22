package com.lalameow.chocolatedungeonworld;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import team.cqr.cqrepoured.world.structure.generation.DungeonDataManager;
import team.cqr.cqrepoured.world.structure.generation.DungeonRegistry;
import team.cqr.cqrepoured.world.structure.generation.dungeons.DungeonBase;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

public class DungeonChunkGenerator implements IChunkGenerator {

    public static final BlockPos DUNGEON_POS = new BlockPos(0, 127, 0);

    private final World world;

    public DungeonChunkGenerator(World world) {
        this.world = world;
    }

    @Override public Chunk generateChunk(int x, int z) {
        return new Chunk(this.world, new ChunkPrimer(), x, z);
    }

    @Override public void populate(int x, int z) {
    }

    @Override public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return null;
    }

    @Nullable @Override public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean p_180513_4_) {
        return DUNGEON_POS;
    }

    @Override public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return true;
    }
}
