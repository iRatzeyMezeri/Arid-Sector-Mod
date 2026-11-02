package aridmod.content;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import static mindustry.type.ItemStack.*;
import aridmod.content.*;

public class AridSectorBlocks {
    public static Block
            // Environment
    // Floors
    navystone, flint, gravel, clay,
    // Walls
    navystoneWall, flintWall, clayWall,
    // Boulders
    navystoneBoulder, flintBoulder, clayBoulder,
    // Vents
    navystoneVent, flintVent, clayVent,
    // Ores
    flintSilicon, magnesiumWallOre, groundPalladiumOre, wallPalladiumOre,
            // Buildable
    // Turrets
    // Defense
    // Effect
    coreOccupation;


    public static void load(){
        // Environment
        navystone = new Floor("navystone"){{
            variants = 3;
        }};
        flint = new Floor("flint"){{
            variants = 4;
        }};
        gravel = new Floor("gravel"){{
            variants = 2;
        }};
        clay = new Floor("clay"){{
            variants = 3;
        }};
        navystoneWall = new StaticWall("navystone-wall"){{
            variants = 2;
        }};
        flintWall = new StaticWall("flint-wall"){{
            variants = 2;
        }};
        flintSilicon = new StaticWall("flint-silicon"){{
            variants = 2;
            itemDrop = Items.silicon;
        }};
        magnesiumWallOre = new OreBlock("magnesium-wall-ore",AridSectorItems.magnesium){{
            variants = 3;
            wallOre = true;
        }};
        clayWall = new StaticWall("clay-wall"){{
            variants = 2;
        }};
        navystoneBoulder = new Prop("navystone-boulder"){{
            variants = 2;
            navystone.asFloor().decoration = this;
        }};
        flintBoulder = new Prop("flint-boulder"){{
            variants = 2;
            flint.asFloor().decoration = this;
        }};
        clayBoulder = new Prop("clay-boulder"){{
            variants = 2;
            clay.asFloor().decoration = this;
        }};
        navystoneVent = new SteamVent("navystone-vent"){{
                parent = blendGroup = navystone;
                attributes.set(Attribute.steam, 1f);
        }};
        flintVent = new SteamVent("flint-vent"){{
            parent = blendGroup = flint;
            attributes.set(Attribute.steam, 1f);
        }};
        clayVent = new SteamVent("clay-vent"){{
            parent = blendGroup = clay;
            attributes.set(Attribute.steam, 1f);
        }};
        // Effect
        coreOccupation = new CoreBlock("core-occupation"){{
            requirements(Category.effect, with(AridSectorItems.itemClay, 800, Items.silicon, 800, AridSectorItems.carapace, 360));
            alwaysUnlocked = true;

            isFirstTier = true;
            health = 960;
            size = 4;
            absorbLasers = true;
            itemCapacity = 2000;
            unitCapModifier = 6;
            squareSprite = false;
            unitType = AridSectorUnitTypes.tenant;
        }};

}}