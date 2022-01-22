package com.lalameow.chocolatedungeonworld;

import static com.lalameow.chocolatedungeonworld.DungeonChunkGenerator.DUNGEON_POS;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import team.cqr.cqrepoured.world.structure.generation.DungeonDataManager;
import team.cqr.cqrepoured.world.structure.generation.DungeonRegistry;
import team.cqr.cqrepoured.world.structure.generation.dungeons.DungeonBase;

@Mod(
        modid = ChocolateDungeonWorld.MOD_ID,
        name = ChocolateDungeonWorld.MOD_NAME,
        version = ChocolateDungeonWorld.VERSION
)
public class ChocolateDungeonWorld {

    public static final String MOD_ID = "chocolate-dungeon-world";
    public static final String MOD_NAME = "Chocolate Dungeon World";
    public static final String VERSION = "0.1.0";
    public static final WorldType CHOCOLATE_DUNGEON = new DungeonWorldType();
    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MOD_ID)
    public static ChocolateDungeonWorld INSTANCE;

    private static Logger LOGGER = LogManager.getLogger();

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent public void onWorldLoad(WorldEvent.Load event) {
        World world = event.getWorld();
        if (world.isRemote) {
            return;
        }
        if (world.getWorldType() != CHOCOLATE_DUNGEON) {
            return;
        }
        if (!(world.provider instanceof WorldProviderSurface)) {
            return;
        }
        if (!DungeonDataManager.getSpawnedDungeonNames(world).isEmpty()) {
            return;
        }
        world.getChunkProvider().provideChunk(0, 0);
        world.setSpawnPoint(DUNGEON_POS);
        LOGGER.debug("Trying to generate " + world.getWorldInfo().getGeneratorOptions() + " dungeon");
        DungeonBase dungeon = DungeonRegistry.getInstance().getDungeon(world.getWorldInfo().getGeneratorOptions());
        if (dungeon != null) {
            dungeon.generateWithOffsets(
                    world,
                    DUNGEON_POS.getX(), DUNGEON_POS.getY(), DUNGEON_POS.getZ(),
                    world.rand,
                    DungeonDataManager.DungeonSpawnType.LOCKED_COORDINATE,
                    true
            );
        }
    }
}
